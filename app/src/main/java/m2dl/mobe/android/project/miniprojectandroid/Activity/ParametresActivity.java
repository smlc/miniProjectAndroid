package m2dl.mobe.android.project.miniprojectandroid.Activity;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

import m2dl.mobe.android.project.miniprojectandroid.Domain.Batiment;
import m2dl.mobe.android.project.miniprojectandroid.Domain.User;
import m2dl.mobe.android.project.miniprojectandroid.R;
import m2dl.mobe.android.project.miniprojectandroid.Services.FireBaseServices;

public class ParametresActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private TextView nom;
    private TextView prenom;
    private TextView mail;
    private TextView lienAfficher;
    private EditText linkEdt;
    private EditText login;
    private EditText passWord;
    private Button buttonUpdate;
    private  User user;
    private String userKey;
    private  DatabaseReference mUserReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres);

        Intent startIntent = getIntent();

        Bundle extras = startIntent.getExtras();
        user    = extras.getParcelable(FireBaseServices.USER_INTENT);
        userKey  = extras.getString(FireBaseServices.USER_KEY);
        //user = startIntent.getParcelableExtra(FireBaseServices.USER_INTENT);
        //userKey = startIntent.getStringArrayExtra(FireBaseServices.USER_KEY)[1];

         nom = (TextView) findViewById(R.id.nom);
         prenom = (TextView) findViewById(R.id.prenom);
         mail = (TextView) findViewById(R.id.mail);
         buttonUpdate = (Button) findViewById(R.id.buttonUpdate);

         linkEdt = (EditText) findViewById(R.id.linkEdt);
         login = (EditText) findViewById(R.id.loginParam);
         passWord = (EditText) findViewById(R.id.passwordParam);
        lienAfficher = (EditText) findViewById(R.id.passwordParam);

        mUserReference = FirebaseDatabase.getInstance().getReference()
                .child("users");

        //Init text
        nom.setText(user.getNomUser());
        prenom.setText(user.getPrenomUser());
        mail.setText(user.getEmailUser());



        updateUserListener();
        updateUser();







    }

    private void updateUserListener() {
        mUserReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                if(dataSnapshot.getKey().equals(userKey)){

                    User userReceive = dataSnapshot.getValue(User.class);
                    nom.setText(userReceive.getNomUser());
                    prenom.setText(userReceive.getPrenomUser());
                    mail.setText(userReceive.getEmailUser());
                }
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

    private void updateUser() {
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setLienEmploisTemps(linkEdt.getText().toString());
                user.setPswUser(passWord.getText().toString());
                user.setNomUser(login.getText().toString());

                mUserReference.child(userKey).setValue(user);


            }
        });



    }
}
