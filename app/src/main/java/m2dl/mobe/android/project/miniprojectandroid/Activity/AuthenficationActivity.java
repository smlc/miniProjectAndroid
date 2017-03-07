package m2dl.mobe.android.project.miniprojectandroid.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import m2dl.mobe.android.project.miniprojectandroid.Domain.User;
import m2dl.mobe.android.project.miniprojectandroid.R;
import m2dl.mobe.android.project.miniprojectandroid.Services.FireBaseServices;

public class AuthenficationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);

        addListenerOnButtonLogin();
        addListenerOnButtonSignUp();

    }

    public void addListenerOnButtonLogin() {


        final EditText username = (EditText) findViewById(R.id.username);
        final EditText passsword = (EditText) findViewById(R.id.password);

        Button passswordBtn = (Button) findViewById(R.id.login);

        final FireBaseServices fireBaseServices = new FireBaseServices();
        final Context context = this;

        passswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fireBaseServices.connectionAndStartAcceuilActivity(username.getText().toString(),passsword.getText().toString(), context);
            }
        });

    }

    public void addListenerOnButtonSignUp() {


        final EditText email = (EditText) findViewById(R.id.email);
        final EditText lastName = (EditText) findViewById(R.id.lastname);
        final EditText firstname = (EditText) findViewById(R.id.firstname);
        final EditText password1 = (EditText) findViewById(R.id.password1);
        final EditText password2 = (EditText) findViewById(R.id.password2);



        Button passswordBtn = (Button) findViewById(R.id.signUp);

        final FireBaseServices fireBaseServices = new FireBaseServices();
        final Context context = this;

        passswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fireBaseServices.storeUser(new User(password1.getText().toString(), email.getText().toString()
                                ,firstname.getText().toString(),lastName.getText().toString(),null));
            }
        });

    }
}

