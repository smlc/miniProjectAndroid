package m2dl.mobe.android.project.miniprojectandroid.Activity;

import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

import m2dl.mobe.android.project.miniprojectandroid.Domain.User;
import m2dl.mobe.android.project.miniprojectandroid.R;
import m2dl.mobe.android.project.miniprojectandroid.Services.FireBaseServices;

@SuppressWarnings("deprecation")
public class ConfigurationActivity extends TabActivity {

    private TabHost TabHostWindow;
    private User user;
    private String userKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
        Intent startIntent = getIntent();

        Bundle extras = startIntent.getExtras();
        user    = extras.getParcelable(FireBaseServices.USER_INTENT);
        userKey  = extras.getString(FireBaseServices.USER_KEY);

        //user = startIntent.getParcelableExtra(FireBaseServices.USER_INTENT);
        //userKey = startIntent.getStringArrayExtra(FireBaseServices.USER_KEY)[1];
        TabHostWindow = (TabHost)findViewById(android.R.id.tabhost);

        //Position tab
        TabHost.TabSpec TabMenu1 = TabHostWindow.newTabSpec("First tab");
        TabMenu1.setIndicator("Informations");
        TabMenu1.setContent(new Intent(this,InformationActivity.class));
        TabHostWindow.addTab(TabMenu1);


        TabHost.TabSpec TabMenu2 = TabHostWindow.newTabSpec("Second tab");
        TabMenu2.setIndicator("Paramètres");
        Intent activityParametres = new Intent(this,ParametresActivity.class);


        extras.putParcelable(FireBaseServices.USER_INTENT,user);
        extras.putString(FireBaseServices.USER_KEY,userKey);
        activityParametres.putExtras(extras);
        //activityParametres.putExtra(FireBaseServices.USER_KEY, userKey);
        //activityParametres.putExtra(FireBaseServices.USER_INTENT, user);

        TabMenu2.setContent(activityParametres);
        TabHostWindow.addTab(TabMenu2);
    }
}
