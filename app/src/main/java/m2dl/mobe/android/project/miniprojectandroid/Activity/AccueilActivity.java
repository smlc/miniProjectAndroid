package m2dl.mobe.android.project.miniprojectandroid.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import m2dl.mobe.android.project.miniprojectandroid.R;
import m2dl.mobe.android.project.miniprojectandroid.Services.FireBaseServices;

public class AccueilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);


        Button buttonMaFac = (Button) findViewById(R.id.buttonMaFac);


        buttonMaFac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activiteMaFac = new Intent(AccueilActivity.this, MaFacActivity.class);
                startActivity(activiteMaFac);
            }
        });
    }



}
