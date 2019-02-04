package com.rainbowocean.hangmanreturns;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeScreen_Activity extends AppCompatActivity {
    static MediaPlayer med = new MediaPlayer();
    static int homescreenmusicflag;

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        med.release();
    }

    static int speakerflag=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen_);
        setflagmusichomepage(0);


        final ImageView speaker = (ImageView) findViewById(R.id.speakeronoff);
        if(speakerflag==1)
        {
            speaker.setImageResource(R.drawable.speakeroff);
        }
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(speakerflag==1)
                {
                    med = MediaPlayer.create(getApplicationContext(),R.raw.soothingforhomepage);
                    med.setLooping(true);
                    speakerflag=0;
                    speaker.setImageResource(R.drawable.speakeron);
                    med.start();
                }
                else
                {
                    stopmusic();
                    speakerflag=1;
                    speaker.setImageResource(R.drawable.speakeroff);
                }

            }
        });

        med = MediaPlayer.create(this,R.raw.soothingforhomepage);
        med.setLooping(true);
        if(speakerflag==0) {
            playmusic();
        }
        TextView textView = (TextView)findViewById(R.id.support);
        textView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_EMAIL,"rainbowocean150@gmail.com");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });


        ImageView imageView = (ImageView)findViewById(R.id.play);
        imageView.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Player_Name.class);
                startActivity(i);
            }
        });




    }
    public static void playmusic()
    {
        med.start();
    }
    public static void stopmusic()
    {
        med.release();
    }

    public static void setflagmusichomepage(int i)
    {
        homescreenmusicflag=i;
    }
    public static int getflagmusichomepage()
    {
        return homescreenmusicflag;
    }
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        AlertDialog.Builder builder = new AlertDialog.Builder(HomeScreen_Activity.this);
//        builder.setTitle("Really Exit?").setMessage("Are You Sure?")
//                .setPositiveButton("ok",new DialogInterface.OnClickListener(){
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        HomeScreen_Activity.super.onBackPressed();
//                    }
//                }).setNegativeButton("Cancel",null);
//        AlertDialog alert= builder.create();
//        alert.show();
//
//
//
//    }

    public static int getmusicstatus(){
        return speakerflag;
    }
}
