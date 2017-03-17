package m2dl.mobe.android.project.miniprojectandroid.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.Date;

import m2dl.mobe.android.project.miniprojectandroid.Domain.Anomalie;
import m2dl.mobe.android.project.miniprojectandroid.Domain.Criticite;
import m2dl.mobe.android.project.miniprojectandroid.R;

public class CreateAnomalieActivity extends AppCompatActivity implements android.location.LocationListener,SensorEventListener {


    private Uri imageUri;
    private static final int CAPTURE_IMAGE = 400;
    private StorageReference mStorageRef;
    FirebaseDatabase database;
    EditText anomaliteTitre;
    Spinner criticite;
    private LocationManager mLocationManager = null;

    private Location location;
    private String direction = "inconue";
    public static SensorManager mSensorManager;
    public static Sensor accelerometer;
    public static Sensor magnetometer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_anomalie);
        Button button = (Button) findViewById(R.id.buttonPhotoAnomalie);
        anomaliteTitre = (EditText) findViewById(R.id.titreAnomalie);
        criticite = (Spinner) findViewById(R.id.criticiteAnomalie);
        //Init
        database = FirebaseDatabase.getInstance();
        mStorageRef = FirebaseStorage.getInstance().getReference();
        button.setEnabled(false);

        initManager();

        if(!anomaliteTitre.getText().toString().equals(null)
           ){
            button.setEnabled(true);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");

                File photo = new File(Environment.getExternalStorageDirectory(),  "anomalie"+new Date().toString()+".jpg");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
                imageUri = Uri.fromFile(photo);

                startActivityForResult(intent, CAPTURE_IMAGE);
            }
        });

        requestLocation();
    }

    private void requestLocation() {
        try {
            mLocationManager.requestSingleUpdate(
                    LocationManager.NETWORK_PROVIDER,
                    this,null);
        } catch (SecurityException ex) {
            Log.i("CreateAnomalieActivity", "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            Log.d("CreateAnomalieActivity", "network provider does not exist, " + ex.getMessage());
        }
    }

    private void initManager() {
        if (mLocationManager == null) {
            mLocationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        }
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }


    @Override
    protected void onResume() {
        super.onResume();

        mSensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
        mSensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_GAME);
    }


    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this, accelerometer);
        mSensorManager.unregisterListener(this, magnetometer);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAPTURE_IMAGE && resultCode == Activity.RESULT_OK){

            Uri imageUris = imageUri;
            //getContentResolver().notifyChange(selectedImage, null);

            //ContentResolver cr = getContentResolver();
            //Bitmap bitmap;
            //  bitmap = android.provider.MediaStore.Images.Media
            //        .getBitmap(cr, selectedImage);

            uploadImage(imageUris);
            //Affichage de l'infobulle
            //Toast.makeText(this, selectedImage.toString(),
            //    Toast.LENGTH_LONG).show();
            mLocationManager.removeUpdates(this);
        }
    }

    private void uploadImage(Uri imageUri) {
        StorageReference storageReference = mStorageRef.child("AnomaliesPhotos").
                child(imageUri.getLastPathSegment());


        storageReference.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content

                        double lat = 0;
                        double longi = 0;
                        String urlLingk = taskSnapshot.getDownloadUrl().getPath();
                        if(location != null){
                            lat = location.getLatitude();
                            longi = location.getLongitude();
                        }
                        storeAnomalie(new Anomalie(anomaliteTitre.getText().toString(),new Date(),urlLingk,
                                Criticite.valueOf(criticite.getSelectedItem().toString()),direction, lat,
                                longi
                                ));
                        setResult(Activity.RESULT_OK);
                        finish();
                        Toast.makeText(CreateAnomalieActivity.this,"Fichier envoyer sur Firebase",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        Toast.makeText(CreateAnomalieActivity.this,"Echec de l'envoie du fichier",Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void storeAnomalie(Anomalie anomalie){

        DatabaseReference myRef = database.getReference("anomalies");

        DatabaseReference anomalies = myRef.push();
        anomalies.setValue(anomalie);

    }

    @Override
    public void onLocationChanged(Location location) {
        this.location = location;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        float[] mGravity = null;
        float[] mGeomagnetic = null ;


        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
            mGravity = event.values.clone();

        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
            mGeomagnetic = event.values.clone();

        if (mGravity != null && mGeomagnetic != null) {
            float R[] = new float[9];
            float I[] = new float[9];
            System.out.println("INNNNNN");

            if (SensorManager.getRotationMatrix(R, I, mGravity, mGeomagnetic)) {

                // orientation contains azimut, pitch and roll
                float orientation[] = new float[3];
                SensorManager.getOrientation(R, orientation);

                float azimut = orientation[0];
                float directionInt = azimut*360/(2*3.14159f);
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println(directionInt);
                if(directionInt >=0 && directionInt <= 45.0){
                    direction = "NORD";
                }else if(directionInt >=46 && directionInt <= 112.0){
                    direction = "EST";
                }else if (directionInt >=135 && directionInt <= 225){
                    direction = "SUD";
                }else if (directionInt >=270 && directionInt <= 337){
                    direction = "SUD";
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
