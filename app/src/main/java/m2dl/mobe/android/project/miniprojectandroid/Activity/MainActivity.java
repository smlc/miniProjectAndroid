package m2dl.mobe.android.project.miniprojectandroid.Activity;

import android.content.Context;
import android.content.Intent;
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

import m2dl.mobe.android.project.miniprojectandroid.Domain.OccupationJour;
import m2dl.mobe.android.project.miniprojectandroid.R;



public class MainActivity extends AppCompatActivity {

    Button button;
    private FirebaseDatabase database;
    public static List<OccupationJour> occupationJourList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getDataFromFireBase();
        addListenerOnButton();
    }
    
     public void addListenerOnButton() {

        button = (Button) findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AccueilActivity.class);

                startActivity(intent);
            }
        });

    }

    private void getDataFromFireBase() {
        database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("occupations");
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                OccupationJour occupationJour = dataSnapshot.getValue(OccupationJour.class);
                occupationJourList.add(occupationJour);

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

}



