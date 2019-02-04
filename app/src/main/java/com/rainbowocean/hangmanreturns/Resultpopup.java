package com.rainbowocean.hangmanreturns;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Resultpopup extends AppCompatActivity {

    MediaPlayer winning = new MediaPlayer();
    MediaPlayer losing = new MediaPlayer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int ro;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultpopup);

        winning = MediaPlayer.create(this,R.raw.applause1);
        losing = MediaPlayer.create(this,R.raw.losingsound);

        Player_Name.changeflagintentresultpop();
        Player_Name.changeflagpointresultpopup();

        int musicCheck=HangMan_Activity.sendflagmusic();
        if(musicCheck==1)
        {
           if(HomeScreen_Activity.getmusicstatus()==0) {
               playloser();
           }
        }
        else
        {
            if(HomeScreen_Activity.getmusicstatus()==0) {
                playmusic();
            }
        }
        DisplayMetrics dm =new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width= dm.widthPixels;
        int height= dm.heightPixels;
        getWindow().setLayout((int)(width*0.8),(int)(height*0.6));


    Button nextbutton = (Button)findViewById(R.id.nextround);
        Button mainmenu = (Button)findViewById(R.id.mainmenu);
      ro=Category_Activity.returnround();
        TextView txt = (TextView) findViewById(R.id.result);


        TextView points1txt  = (TextView) findViewById(R.id.poi1);
        TextView points2txt  = (TextView) findViewById(R.id.poi2);

        points1txt.setText("" + HangMan_Activity.returnpoint1());
        points2txt.setText("" + HangMan_Activity.returnpoint2());

        TextView answerdisplay= (TextView) findViewById(R.id.answertopuzzle);
        answerdisplay.setText(answerdisplay.getText().toString()+" "+Enter_Text.getword());
        if(ro<=(Player_Name.getround()*2))
        {
            if(ro%2==0)
            {
                txt.setText("Time for "+Player_Name.getplay2()+" to guess");
                nextbutton.setText("Next");
            }
            else {

                    txt.setText("results till round " + ((ro-1)/2));
                    nextbutton.setText("NEXT ROUND");
            }
                nextbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(),Category_Activity.class);
                    HomeScreen_Activity.setflagmusichomepage(1);
                    Player_Name.setflagmusicplayername(1);
                    call();
                    startActivity(i);

                }
            });
            mainmenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(),HomeScreen_Activity.class);
                    HomeScreen_Activity.setflagmusichomepage(0);
                    call();
                    Category_Activity.setroundhere(1);
                    startActivity(i);
                }
            });
        }
        else
        {
            txt.setText("final result after " + ((ro-1)/2)+" rounds is:");
            TextView winnertxt= (TextView) findViewById(R.id.winner);
            if(HangMan_Activity.returnpoint1()>HangMan_Activity.returnpoint2()) {
                winnertxt.setText(Player_Name.pl1name + "WINS");
            }
            else if(HangMan_Activity.returnpoint1()<HangMan_Activity.returnpoint2()) {
                winnertxt.setText(Player_Name.pl2name + "WINS");
            }
            else
            {
                winnertxt.setText("Well Played! it's a TIE");
            }
            nextbutton.setText("PLAY AGAIN");
            nextbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(),Player_Name.class);
                    HomeScreen_Activity.setflagmusichomepage(1);
                    call();
                    Category_Activity.setroundhere(1);
                    startActivity(i);

                }
            });
            mainmenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(),HomeScreen_Activity.class);
                    HomeScreen_Activity.setflagmusichomepage(0);
                    call();
                    Category_Activity.setroundhere(1);
                    startActivity(i);

                }
            });
        }


    }

    public void playmusic()
    {
        winning.start();
    }
    public void call()
    {
        if(HangMan_Activity.sendflagmusic()==1)
        {
            stoloser();
        }
        else
        {
            stopmusic();
        }
    }
    public void stopmusic()
    {
        winning.release();
    }
    public void playloser()
    {
        losing.start();
    }
    public void stoloser()
    {
        losing.release();
    }
}
