package m2dl.mobe.android.project.miniprojectandroid.Activity;


import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

import m2dl.mobe.android.project.miniprojectandroid.Domain.User;
import m2dl.mobe.android.project.miniprojectandroid.R;
import m2dl.mobe.android.project.miniprojectandroid.Services.FireBaseServices;

@SuppressWarnings("deprecation")
public class MaFacActivity extends TabActivity {


    TabHost TabHostWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ma_fac);


        Intent startIntent = getIntent();
        final User user = startIntent.getParcelableExtra(FireBaseServices.USER_INTENT);

        TabHostWindow = (TabHost)findViewById(android.R.id.tabhost);

        //Position tab
        TabHost.TabSpec TabMenu1 = TabHostWindow.newTabSpec("First tab");
        TabMenu1.setIndicator("Position batiment");
        TabMenu1.setContent(new Intent(this,PositionActivity.class));
        TabHostWindow.addTab(TabMenu1);


        TabHost.TabSpec TabMenu2 = TabHostWindow.newTabSpec("Second tab");
        TabMenu2.setIndicator("Occupation");
        TabMenu2.setContent(new Intent(this,OccupationActivity.class));
        TabHostWindow.addTab(TabMenu2);

        TabHost.TabSpec TabMenu3 = TabHostWindow.newTabSpec("Third tab");
        TabMenu3.setIndicator("Annomalie");
        TabMenu3.setContent(new Intent(this,OccupationActivity.class));
        TabHostWindow.addTab(TabMenu3);


    }
}
