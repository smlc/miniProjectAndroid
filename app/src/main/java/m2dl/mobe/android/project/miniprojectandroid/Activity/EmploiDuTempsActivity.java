package m2dl.mobe.android.project.miniprojectandroid.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import m2dl.mobe.android.project.miniprojectandroid.R;

/**
 * Created by rottanaly on 3/13/17.
 */

public class EmploiDuTempsActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emploi_du_temps);
        webView = (WebView) findViewById(R.id.webviewEDT);
        webView.loadUrl("https://edt.univ-tlse3.fr");

    }
}
