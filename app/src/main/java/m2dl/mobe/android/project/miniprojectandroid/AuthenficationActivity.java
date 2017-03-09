package m2dl.mobe.android.project.miniprojectandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

public class AuthenficationActivity extends AppCompatActivity {

    Button btnLogin, btnInscire;
    EditText username, password;
    EditText email, lastname, firstname, pwd1, pwd2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);

        btnLogin = (Button) findViewById(R.id.btn1);
        btnInscire = (Button) findViewById(R.id.btn2);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        email = (EditText) findViewById(R.id.email);
        lastname = (EditText) findViewById(R.id.lastname);
        firstname = (EditText) findViewById(R.id.firstname);
    }
}

