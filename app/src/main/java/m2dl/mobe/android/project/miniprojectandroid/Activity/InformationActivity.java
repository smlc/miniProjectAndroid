package m2dl.mobe.android.project.miniprojectandroid.Activity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import m2dl.mobe.android.project.miniprojectandroid.R;

/**
 * Created by rottanaly on 3/14/17.
 */

public class InformationActivity extends AppCompatActivity {

    private SeekBar brightnessControl;
    private SeekBar volumeControl;
    private float brightnessValue = 0.5f;
    private TextView tvDev1, tvDev2, tvDev3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(210, 300);
        (findViewById(R.id.ivDev1)).setLayoutParams(layoutParams);
        (findViewById(R.id.ivDev2)).setLayoutParams(layoutParams);
        (findViewById(R.id.ivDev3)).setLayoutParams(layoutParams);
        tvDev1 = (TextView) findViewById(R.id.tvDev1);
        tvDev2 = (TextView) findViewById(R.id.tvDev2);
        tvDev3 = (TextView) findViewById(R.id.tvDev3);
        tvDev1.setText("Jaafar DIAMI");
        tvDev2.setText("Marlon SIESA");
        tvDev3.setText("Rottana LY");

        brightnessControl = (SeekBar) findViewById(R.id.brightnessControl);
        brightnessControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                brightnessValue = (float) progress/100;
                WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
                layoutParams.screenBrightness = brightnessValue;
                getWindow().setAttributes(layoutParams);
                checkPermission((int)(brightnessValue * 255));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        volumeControl = (SeekBar) findViewById(R.id.volumeControl);
        final AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        volumeControl.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        volumeControl.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void checkPermission(int val) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Settings.System.canWrite(this)) {
                android.provider.Settings.System.putInt(getContentResolver(),
                        Settings.System.SCREEN_BRIGHTNESS, val);
            }
            else {
                Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package:" + getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }
    }

}
