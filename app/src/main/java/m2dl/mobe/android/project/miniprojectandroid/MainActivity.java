package m2dl.mobe.android.project.miniprojectandroid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton();

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
    
     public void addListenerOnButton() {

        final Context context = this;
        button = (Button) findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AuthenficationActivity.class);
                startActivity(intent);
            }
        });

    }

}
