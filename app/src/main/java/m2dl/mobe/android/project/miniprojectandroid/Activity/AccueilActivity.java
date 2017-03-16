package m2dl.mobe.android.project.miniprojectandroid.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import m2dl.mobe.android.project.miniprojectandroid.Domain.User;
import m2dl.mobe.android.project.miniprojectandroid.R;
import m2dl.mobe.android.project.miniprojectandroid.Services.FireBaseServices;

public class AccueilActivity extends AppCompatActivity {

    private User user;
    private String userKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        Intent startIntent = getIntent();

        Bundle extras = startIntent.getExtras();
        user    = extras.getParcelable(FireBaseServices.USER_INTENT);
        userKey  = extras.getString(FireBaseServices.USER_KEY);

      //  final User user = startIntent.getParcelableExtra(FireBaseServices.USER_INTENT);
       // final String userKey = startIntent.getStringArrayExtra(FireBaseServices.USER_KEY)[0];
        Button buttonMaFac = (Button) findViewById(R.id.buttonMaFac);


        buttonMaFac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activiteMaFac = new Intent(AccueilActivity.this, MaFacActivity.class);
                startActivity(activiteMaFac);
            }
        });

        Button buttonMesCours = (Button) findViewById(R.id.buttonMesCours);


        buttonMesCours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activiteMesCours = new Intent(AccueilActivity.this, MesCoursActivity.class);
                startActivity(activiteMesCours);
            }
        });


        Button buttonConfiguration = (Button) findViewById(R.id.buttonConfiguration);


        buttonConfiguration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activiteConfiguration = new Intent(AccueilActivity.this, ConfigurationActivity.class);

                Bundle extras = new Bundle();
                extras.putParcelable(FireBaseServices.USER_INTENT,user);
                extras.putString(FireBaseServices.USER_KEY,userKey);
                activiteConfiguration.putExtras(extras);
               // activiteConfiguration.putExtra(FireBaseServices.USER_KEY, userKey);
               // activiteConfiguration.putExtra(FireBaseServices.USER_INTENT, user);
                startActivity(activiteConfiguration);
            }
        });
    }



}
