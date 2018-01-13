package com.emily.chronometer_test;

import android.os.SystemClock;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity {
    private Button bt_start,bt_stop,bt_reset,bt_clear;
    private Chronometer chronometer;
    private Vibrator vibrator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_start=this.findViewById(R.id.btn_start);
        bt_stop=this.findViewById(R.id.btn_stop);
        bt_reset=this.findViewById(R.id.btn_reset);
        bt_clear=this.findViewById(R.id.btn_clear);
        chronometer=this.findViewById(R.id.myChronometer);
        vibrator= (Vibrator) this.getSystemService(VIBRATOR_SERVICE);
        chronometer.setOnChronometerTickListener(new OnChronometerTickListnerImpl());


        bt_start.setOnClickListener(new ButtonListner());
        bt_stop.setOnClickListener(new ButtonListner());
        bt_reset.setOnClickListener(new ButtonListner());
        bt_clear.setOnClickListener(new ButtonListner());
    }

    public class ButtonListner implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_start:chronometer.start();break;
                case R.id.btn_stop:chronometer.stop();break;
                case R.id.btn_reset:chronometer.setBase(SystemClock.elapsedRealtime());break;
                case R.id.btn_clear:chronometer.setFormat("显示时间:%s.");break;
                default: break;

            }
        }
    }

    public class OnChronometerTickListnerImpl implements Chronometer.OnChronometerTickListener{
        @Override
        public void onChronometerTick(Chronometer chronometer) {
            String time=chronometer.getText().toString();
            if("00:05".equals(time)){
                vibrator.vibrate(new long[]{1000,10,100,10},-1);
            }
        }
    }


}
