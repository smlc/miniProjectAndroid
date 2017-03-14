package m2dl.mobe.android.project.miniprojectandroid.Activity;

import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

import m2dl.mobe.android.project.miniprojectandroid.R;

@SuppressWarnings("deprecation")
public class MesCoursActivity extends TabActivity {

    TabHost TabHostWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_cours);

        TabHostWindow = (TabHost)findViewById(android.R.id.tabhost);

        //Qrc code reader tab
        TabHost.TabSpec TabMenu1 = TabHostWindow.newTabSpec("First tab");
        TabMenu1.setIndicator("QR Code Reader");
        TabMenu1.setContent(new Intent(this,QRCodeActivity.class));
        TabHostWindow.addTab(TabMenu1);

        //Emplois du temps tab
        TabHost.TabSpec TabMenu2 = TabHostWindow.newTabSpec("Second tab");
        TabMenu2.setIndicator("EDT");
        TabMenu2.setContent(new Intent(this,EmploiDuTempsActivity.class));
        TabHostWindow.addTab(TabMenu2);
    }
}
