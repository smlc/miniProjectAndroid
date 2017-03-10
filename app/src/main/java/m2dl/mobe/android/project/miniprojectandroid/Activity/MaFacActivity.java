package m2dl.mobe.android.project.miniprojectandroid.Activity;


import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import m2dl.mobe.android.project.miniprojectandroid.R;

@SuppressWarnings("deprecation")
public class MaFacActivity extends TabActivity {


    TabHost TabHostWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ma_fac);


        TabHostWindow = (TabHost)findViewById(android.R.id.tabhost);

        //Position tab
        TabHost.TabSpec TabMenu1 = TabHostWindow.newTabSpec("First tab");
        TabMenu1.setIndicator("Position batiment");
        TabMenu1.setContent(new Intent(this,PositionActivity.class));
        TabHostWindow.addTab(TabMenu1);


        /*TabHost.TabSpec TabMenu2 = TabHostWindow.newTabSpec("Second tab");
        TabMenu2.setIndicator("Anomalie");
        TabMenu2.setContent(new Intent(this,AnomalieActivity.class));
        TabHostWindow.addTab(TabMenu1);*/


    }
}
