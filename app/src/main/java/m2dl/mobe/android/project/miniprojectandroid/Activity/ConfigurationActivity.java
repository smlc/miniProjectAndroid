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

    TabHost TabHostWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
        Intent startIntent = getIntent();
        final User user = startIntent.getParcelableExtra(FireBaseServices.USER_INTENT);
        TabHostWindow = (TabHost)findViewById(android.R.id.tabhost);

        //Position tab
        TabHost.TabSpec TabMenu1 = TabHostWindow.newTabSpec("First tab");
        TabMenu1.setIndicator("Informations");
        TabMenu1.setContent(new Intent(this,InformationActivity.class));
        TabHostWindow.addTab(TabMenu1);


        TabHost.TabSpec TabMenu2 = TabHostWindow.newTabSpec("Second tab");
        TabMenu2.setIndicator("Paramètres");
        TabMenu2.setContent(new Intent(this,ParametresActivity.class).putExtra(FireBaseServices.USER_INTENT, user));
        TabHostWindow.addTab(TabMenu2);
    }
}
