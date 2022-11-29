package com.example.cachorro;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private SensorManager mSensorManager;
    private Sensor mProx;
    private MediaPlayer mp;
    private boolean latindo, rosnando;
    private ImageView ivFigura;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mProx = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        mp = MediaPlayer.create(getApplicationContext(),R.raw.rosnado);
        latindo = false;
        rosnando = false;
        ivFigura = (ImageView) findViewById(R.id.ivFigura);
        mSensorManager.registerListener(new ProxSensor(), mProx,
                SensorManager.SENSOR_DELAY_UI);
    }

    class ProxSensor implements SensorEventListener {
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
        public void onSensorChanged(SensorEvent event) {
            float vl = event.values[0];
            if (vl <= 1) {
                latir();
            }
            if(vl >1 && vl<=3) {
                rosnar();
            }
        }
    }
    public void latir(View view) {
        latir();
    }
    public void rosnar(View view) {
        rosnar();
    }

    public void latir2() {
        mp = MediaPlayer.create(getApplicationContext(), R.raw.latido);
        mp.start();
    }
    public void rosnar2() {
        mp = MediaPlayer.create(getApplicationContext(), R.raw.rosnado);
        mp.start();
    }
    public void latir() {
        if(!latindo) {
            latindo = true;
            ivFigura.setImageResource(R.drawable.latindo);
            mp = MediaPlayer.create(getApplicationContext(), R.raw.latido);
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    latindo = false;
                    mp.release();
                    ivFigura.setImageResource(R.drawable.calmo);
                }
            });
            mp.start();
        }
    }

    private void rosnar() {
        if(!rosnando) {
            rosnando = true;
            ivFigura.setImageResource(R.drawable.rosnando);
            mp = MediaPlayer.create(getApplicationContext(), R.raw.rosnado);
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    rosnando = false;
                    mp.release();
                    ivFigura.setImageResource(R.drawable.calmo);
                }
            });
            mp.start();
        }
    }

    public void parar() {
        if(mp!=null && mp.isPlaying()) {
            mp.stop();
            mp.release();
        }
        latindo = false;
        rosnando = false;
        ivFigura.setImageResource(R.drawable.calmo);
    }
    public void parar(View view) {
        parar();
    }
}