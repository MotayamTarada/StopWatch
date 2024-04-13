package com.example.stopwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private boolean status=false;
    private int seconds = 0 ;

    private Button start ;
    private Button pause ;
    private Button rest ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = findViewById(R.id.start);
        pause = findViewById(R.id.pause);
        rest = findViewById(R.id.rest);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = true;
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = false;
            }
        });

        rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = false;
                seconds = 0;

            }
        });

        Timer_Run();
        checkInstance(savedInstanceState);
    }

    public void Timer_Run() {
        final TextView clock = findViewById(R.id.clock);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600) / 60;
                int secs = seconds%60;
                String time = hours +" : " + minutes + " : " + secs;
                clock.setText(time);

                if(status==true){
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }

        });
    }

    // very immprtant same session in php save temprory data 
    private void checkInstance(Bundle savedInstanceState) {
        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("SECONDS");
            status = savedInstanceState.getBoolean("RUNNING");
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("SECONDS",seconds);
        outState.putBoolean("status",status);
    }
}