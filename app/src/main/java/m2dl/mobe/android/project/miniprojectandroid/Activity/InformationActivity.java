package m2dl.mobe.android.project.miniprojectandroid.Activity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;

import m2dl.mobe.android.project.miniprojectandroid.R;

/**
 * Created by rottanaly on 3/14/17.
 */

public class InformationActivity extends AppCompatActivity implements SensorEventListener {

    private SeekBar brightnessControl;
    private SeekBar volumeControl;
    private float brightnessValue = 0.5f;
    private TextView tvDev1, tvDev2, tvDev3;

    private SensorManager mSensorManager;
    private Sensor mLight;
    private TextView tvLightSensor;
    private ProgressBar pbLight;

    /*private AudioRecord audio;
    private Thread thread;
    private int bufferSize;
    private double soundLevel;
    private int sampleRate = 8000;*/
    private TextView tvSoundSensor;
    private ProgressBar pbSound;

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

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        tvLightSensor = (TextView) findViewById(R.id.tvLightSensor);
        tvLightSensor.setText("Capteur de lumiere: 0lx");
        pbLight = (ProgressBar) findViewById(R.id.progressBarLight);
        pbLight.setMax((int) mLight.getMaximumRange());

        tvSoundSensor = (TextView) findViewById(R.id.tvSoundSensor);
        tvSoundSensor.setText("Capteur de son: 0" );
        pbSound = (ProgressBar) findViewById(R.id.progressBarSound);
        pbSound.setMax(200);

        /*try {
            bufferSize = AudioRecord.getMinBufferSize(sampleRate, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
        } catch (Exception e) {
            Log.e("TrackingFlow", "Exception", e);
        }*/

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


    @Override
    public void onSensorChanged(SensorEvent event) {
        float value_light = event.values[0];
        tvLightSensor.setText("Capteur de lumiere: " + value_light + "lx");
        System.out.println("Capteur de lumiere: " + value_light + "lx");
        pbLight.setProgress((int) value_light);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);

        /*audio = new AudioRecord(MediaRecorder.AudioSource.MIC, sampleRate,
                AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_PCM_16BIT, 5000000);
        audio.startRecording();
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (thread!=null && !thread.isInterrupted()) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    readAudioBuffer();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvSoundSensor.setText("Capteur de son: " + soundLevel);
                            pbSound.setProgress((int) soundLevel);
                        }
                    });
                }
            }
        });
        thread.run();*/
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);

        /*thread.interrupt();
        thread = null;
        try {
            if (audio != null) {
                audio.stop();
                audio.release();
                audio = null;
            }
        } catch (Exception e) {e.printStackTrace();}*/
    }

    /*private void readAudioBuffer() {

        try {
            short[] buffer = new short[bufferSize];
            int bufferReadResult = 1;
            if (audio != null) {
                bufferReadResult = audio.read(buffer, 0, bufferSize);
                double sumLevel = 0;
                for (int i = 0; i < bufferReadResult; i++) {
                    sumLevel += buffer[i];
                }
                soundLevel = Math.abs((sumLevel / bufferReadResult)); }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

}
