package m2dl.mobe.android.project.miniprojectandroid.Activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import m2dl.mobe.android.project.miniprojectandroid.Domain.Anomalie;
import m2dl.mobe.android.project.miniprojectandroid.Domain.Criticite;
import m2dl.mobe.android.project.miniprojectandroid.Domain.User;
import m2dl.mobe.android.project.miniprojectandroid.R;
import m2dl.mobe.android.project.miniprojectandroid.Services.FireBaseServices;

@SuppressWarnings("deprecation")
public class AnomalieActivity extends AppCompatActivity {


    private static final int CREATE_ANOMALIE = 150;
    private ListView mListeView;
    private  ArrayList<Anomalie> userAnomalies = new ArrayList<>();
    ArrayAdapter<Anomalie> arrayAdapter = null;
    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_anomalie);

        //Init firebase
        database = FirebaseDatabase.getInstance();

        Intent startIntent = getIntent();
        final User user = startIntent.getParcelableExtra(FireBaseServices.USER_INTENT);
        mListeView = (ListView) findViewById(R.id.anomaliesListView);
        FloatingActionButton addAnomalie = (FloatingActionButton) findViewById(R.id.fab);

         arrayAdapter = new ArrayAdapter<Anomalie>(this,android.R.layout.simple_list_item_1, userAnomalies);

        mListeView.setAdapter(arrayAdapter);

        //userAnomalies = user.getAnomalies();
        //arrayAdapter.notifyDataSetChanged();

        addAnomalie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AnomalieActivity.this,CreateAnomalieActivity.class);

                startActivityForResult(intent, CREATE_ANOMALIE);
                /*Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");

               File photo = new File(Environment.getExternalStorageDirectory(),  "anomalie"+new Date().toString()+".jpg");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
                imageUri = Uri.fromFile(photo);

                startActivityForResult(intent, CAPTURE_IMAGE);*/


            }
        });


        fireBaseListener();


    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CREATE_ANOMALIE && resultCode == Activity.RESULT_OK){

            arrayAdapter.notifyDataSetChanged();
        }
    }

    private void fireBaseListener() {
        DatabaseReference myRef = database.getReference("anomalies");

        //myRef.push().setValue(new Anomalie("Porte casser",new Date(),"http:photo",Criticite.DANGER, "EST", 12.2,12.8));
        myRef.addChildEventListener(new ChildEventListener() {
            private void update(DataSnapshot dataSnapshot){
                Anomalie anomalie = dataSnapshot.getValue(Anomalie.class);
                System.out.println(anomalie);
                userAnomalies.add(anomalie);
                arrayAdapter.notifyDataSetChanged();
            }
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                update(dataSnapshot);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                update(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }









}
