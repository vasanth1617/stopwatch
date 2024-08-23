package com.vasanth.stopwatch;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    
    
    TextView timer;
    Button s ,p , r;
    long millsecondtime,stime,timebuff,update=01;
    Handler handler;
    int sec,min,millsec;
            
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        setContentView(R.layout.activity_main);
       
        
        timer=(TextView) findViewById(R.id.t);
        s=(Button) findViewById(R.id.s);
        p=(Button) findViewById(R.id.p);
        r=(Button) findViewById(R.id.r);
        
        
        handler=new Handler();
        
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stime= SystemClock.uptimeMillis();


                handler.postAtTime(runnable,0);
                r.setEnabled(false);
            }
        });
        p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timebuff += millsecondtime;

                handler.removeCallbacks(runnable);

                r.setEnabled(true);
            }
        });


        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                millsecondtime = 0l;
                stime = 0l;
                timebuff = 0l;
                update = 0l;

                sec = 0;
                min = 0;
                millsec = 0;
                timer.setText("00.00.00");
            }
        });
    }
        public Runnable  runnable= new Runnable() {

            public void run() {
                millsecondtime=SystemClock.uptimeMillis()-stime;
                update=timebuff+millsecondtime;
                sec=(int) (update/1000);
                min=sec/60;
                sec=sec %60;
                millsec=(int) (update%1000);
                timer.setText(""+min+":" +String.format("%02d",sec)+":" +String.format("%03d",millsec)) ;
                handler.postDelayed(this,0);

            }
        };

        
        
        
        
        
        

}