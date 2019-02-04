package com.rainbowocean.hangmanreturns;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);


        Thread mythread = new Thread(){
            @Override
            public void run() {
                try{

                    sleep(2000);
                    Intent i= new Intent(getApplicationContext(), HomeScreen_Activity.class);
                    startActivity(i);
                    finish();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();;
                }
            }
        };
        mythread.start();

    }
}
