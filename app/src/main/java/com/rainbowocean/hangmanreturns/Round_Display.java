package com.rainbowocean.hangmanreturns;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;

public class Round_Display extends AppCompatActivity {

    MediaPlayer med = new MediaPlayer();
    MediaPlayer ghanta = new MediaPlayer();
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ghanta.release();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ghanta = MediaPlayer.create(this,R.raw.gahnta);
        if(HomeScreen_Activity.getmusicstatus()==0) {
            playmusic();
        }
        setContentView(R.layout.activity_round__display);
        int r= Player_Name.getround();
        ImageView img = (ImageView) findViewById(R.id.roundimage);
        if(r==1)
        {
            img.setImageResource(R.drawable.herewego);
        }
        else {
            switch (((Category_Activity.returnround()-1)/2)+1)
            {
                case 1:    img.setImageResource(R.drawable.roundonebegins);break;
                case 2:    img.setImageResource(R.drawable.two);break;
                case 3:    img.setImageResource(R.drawable.three);break;
                case 4:    img.setImageResource(R.drawable.four);break;
                case 5:    img.setImageResource(R.drawable.five);break;
                case 6:    img.setImageResource(R.drawable.six);break;
                case 7:    img.setImageResource(R.drawable.seven);break;
                case 8:    img.setImageResource(R.drawable.eight);break;
                case 9:    img.setImageResource(R.drawable.nine);break;
                case 10:   img.setImageResource(R.drawable.ten);break;
                case 11:   img.setImageResource(R.drawable.eleven);break;
                case 12:   img.setImageResource(R.drawable.twelve);break;
                case 13:   img.setImageResource(R.drawable.thirteen);break;
                case 14:   img.setImageResource(R.drawable.fourteen);break;
                case 15:   img.setImageResource(R.drawable.fifteen);break;
                default:   img.setImageResource(R.drawable.herewego);break;
            }
        }
        DisplayMetrics dm= new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        getWindow().setLayout((int)((dm.widthPixels) * 0.8),(int) ((dm.heightPixels) * 0.6));

        Thread mythread = new Thread(){
            @Override
            public void run() {
                try{

                    sleep(2000);
                    Intent i= new Intent(getApplicationContext(), Category_Activity.class);
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
    public void playmusic()
    {
        ghanta.start();
    }
}
