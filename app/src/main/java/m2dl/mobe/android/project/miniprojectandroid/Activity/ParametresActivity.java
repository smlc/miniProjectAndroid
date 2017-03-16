package m2dl.mobe.android.project.miniprojectandroid.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import m2dl.mobe.android.project.miniprojectandroid.Domain.User;
import m2dl.mobe.android.project.miniprojectandroid.R;
import m2dl.mobe.android.project.miniprojectandroid.Services.FireBaseServices;

public class ParametresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres);

        TextView nom = (TextView) findViewById(R.id.nom);
        TextView prenom = (TextView) findViewById(R.id.prenom);
        TextView mail = (TextView) findViewById(R.id.mail);

        Intent startIntent = getIntent();
        User user = startIntent.getParcelableExtra(FireBaseServices.USER_INTENT);

        nom.setText(user.getNomUser());
        prenom.setText(user.getPrenomUser());
        mail.setText(user.getEmailUser());

    }
}
