package m2dl.mobe.android.project.miniprojectandroid.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import m2dl.mobe.android.project.miniprojectandroid.R;

public class BrowserActivity extends AppCompatActivity {

    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        Intent startIntent = getIntent();


        String  urlSite = startIntent.getStringExtra(QRCodeActivity.NOMS);
        webView = (WebView) findViewById(R.id.webviewQrcode);

        webView.loadUrl(urlSite);

    }
}
