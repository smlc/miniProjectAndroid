package m2dl.mobe.android.project.miniprojectandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");

        DatabaseReference user = myRef.push();
        user.child("nom").setValue("Marlon");
        user.child("prenom").setValue("Marlon");
        user.child("photo").setValue("Marlon");
        user.child("email").setValue("Marlon");
        user.child("mdp").setValue("Marlon");


        //myRef.setValue("Hello, World!");
        //System.out.println("Write");
    }
}

