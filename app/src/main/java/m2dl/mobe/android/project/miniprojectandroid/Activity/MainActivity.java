package m2dl.mobe.android.project.miniprojectandroid.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import m2dl.mobe.android.project.miniprojectandroid.Domain.Batiment;
import m2dl.mobe.android.project.miniprojectandroid.Domain.OccupationJour;
import m2dl.mobe.android.project.miniprojectandroid.R;



public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST = 100;
    private static final int MY_PERMISSIONS_REQUEST_INTERNET = 200;
    private static final int MY_PERMISSIONS_REQUEST_WRITE = 300;
    Button button;
    private FirebaseDatabase database;
    public static List<Batiment> batimentList = new ArrayList<>();
    String[] permissions= new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermissions();

        getDataFromFireBase();
        addListenerOnButton();
    }
    
     public void addListenerOnButton() {

        button = (Button) findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AuthenficationActivity.class);

                startActivity(intent);
            }
        });

    }

    private void getDataFromFireBase() {
        database = FirebaseDatabase.getInstance();

        //TODO remove
       /* DatabaseReference myRef2 = database.getReference("batimentOccuper");

        DatabaseReference userChild = myRef2.push();
        userChild.setValue(new Batiment("RU1",  "Service", 43.562252, 1.463187));*/
        DatabaseReference myRef = database.getReference("batimentOccuper");
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Batiment occupationJourBatiment = dataSnapshot.getValue(Batiment.class);
                batimentList.add(occupationJourBatiment);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

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


    private  boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p:permissions) {
            result = ContextCompat.checkSelfPermission(this,p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),MY_PERMISSIONS_REQUEST );
            return false;
        }
        return true;
    }
}



