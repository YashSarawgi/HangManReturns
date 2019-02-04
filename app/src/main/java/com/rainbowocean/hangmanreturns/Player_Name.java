package com.rainbowocean.hangmanreturns;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Player_Name extends AppCompatActivity {
    static MediaPlayer a= new MediaPlayer();
    static int flagmusicplayername;
    public static int rounds;
    static int flag;
    static int flagpoints=0;
    public static String pl1name;
    public static String pl2name;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(Player_Name.this);
        builder.setTitle("Really Exit?")
                .setMessage("Are You Sure?")
                .setPositiveButton("ok",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Player_Name.super.onBackPressed();

                    }
                })
                .setNegativeButton("Cancel",null);
        AlertDialog alertDialog= builder.create();
        alertDialog.show();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player__name);
        a = MediaPlayer.create(this,R.raw.soothingforhomepage);
        a.setLooping(true);
        changeflagintentplayername();
        changeflagpointplayername();

        setflagmusicplayername(0);
        if(HomeScreen_Activity.getmusicstatus()==0) {
            if (((int) HomeScreen_Activity.getflagmusichomepage()) == 1) {
                a.start();
            }
        }

        Log.v("creating ","playername");

        final Button playGame =(Button) findViewById(R.id.playgame);
        final Button single =(Button) findViewById(R.id.singleround);
        final Button round2 =(Button) findViewById(R.id.round2);
        final Button user =(Button) findViewById(R.id.definerounds);

        final TextView head = (TextView) findViewById(R.id.textView) ;

        EditText pl1 = (EditText) findViewById(R.id.player1);
        EditText pl2 = (EditText) findViewById(R.id.player2);

        InputFilter[] filterArray = new InputFilter[1];
        filterArray[0]= new InputFilter.LengthFilter(10);
        pl1.setFilters(filterArray);
        pl2.setFilters(filterArray);

        playGame.setEnabled(false);
        pl1.setEnabled(false);
        pl2.setEnabled(false);

        playGame.setBackgroundColor(getResources().getColor(R.color.GREY));
        playGame.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                pl1name=getcontent(1);
                pl2name=getcontent(2);
                Intent i = new Intent(getApplicationContext(),Round_Display.class);
                startActivity(i);
            }
        });

        single.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                rounds= 1;
                playGame.setBackgroundColor(getResources().getColor(R.color.BLACK));
                enableThem();
                single.setBackgroundColor(getResources().getColor(R.color.HORROR_GREEN));
                round2.setBackgroundColor(getResources().getColor(R.color.GREY));
                user.setBackgroundColor(getResources().getColor(R.color.GREY));

            }
        });

        round2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                rounds= 2;
                playGame.setBackgroundColor(getResources().getColor(R.color.BLACK));
                enableThem();
                single.setBackgroundColor(getResources().getColor(R.color.GREY));
                round2.setBackgroundColor(getResources().getColor(R.color.HORROR_GREEN));
                user.setBackgroundColor(getResources().getColor(R.color.GREY));

            }
        });

        user.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                single.setEnabled(false);
                round2.setEnabled(false);
                user.setEnabled(false);
                head.setEnabled(false);

                single.setBackgroundColor(getResources().getColor(R.color.GREY));
                round2.setBackgroundColor(getResources().getColor(R.color.GREY));
                user.setBackgroundColor(getResources().getColor(R.color.HORROR_GREEN));


                LinearLayout l = (LinearLayout)findViewById(R.id.ask);
                final Button round5 = new Button(getApplicationContext());
                final Button round10 = new Button(getApplicationContext());
                final Button round15 = new Button(getApplicationContext());

                round5.setBackgroundColor(getResources().getColor(R.color.BLACK));
                round10.setBackgroundColor(getResources().getColor(R.color.BLACK));
                round15.setBackgroundColor(getResources().getColor(R.color.BLACK));

                round5.setTextColor(getResources().getColor(R.color.WHITE));
                round10.setTextColor(getResources().getColor(R.color.WHITE));
                round15.setTextColor(getResources().getColor(R.color.WHITE));


                DisplayMetrics dm = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(dm);

                int width = (dm.widthPixels/3);

                round5.setWidth(width);
                round10.setWidth(width);
                round15.setWidth(width);

                round5.setText("5 Rounds");
                round10.setText("10 Rounds");
                round15.setText("15 Rounds");

                l.addView(round5);
                l.addView(round10);
                l.addView(round15);

                round5.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        rounds=5;
                        round5.setEnabled(false);
                        round10.setEnabled(false);
                        round15.setEnabled(false);
                        enableThem();
                        playGame.setBackgroundColor(getResources().getColor(R.color.BLACK));
                        user.setBackgroundColor(getResources().getColor(R.color.GREY));

                        round5.setBackgroundColor(getResources().getColor(R.color.HORROR_GREEN));
                        round10.setBackgroundColor(getResources().getColor(R.color.GREY));
                        round15.setBackgroundColor(getResources().getColor(R.color.GREY));

                    }
                });
                round10.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        rounds=10;
                        round5.setEnabled(false);
                        round10.setEnabled(false);
                        round15.setEnabled(false);
                        enableThem();
                        playGame.setBackgroundColor(getResources().getColor(R.color.BLACK));
                        user.setBackgroundColor(getResources().getColor(R.color.GREY));
                        round5.setBackgroundColor(getResources().getColor(R.color.GREY));
                        round10.setBackgroundColor(getResources().getColor(R.color.HORROR_GREEN));
                        round15.setBackgroundColor(getResources().getColor(R.color.GREY));
                    }
                });
                round15.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        rounds=15;
                        round5.setEnabled(false);
                        round10.setEnabled(false);
                        round15.setEnabled(false);
                        enableThem();
                        playGame.setBackgroundColor(getResources().getColor(R.color.BLACK));
                        user.setBackgroundColor(getResources().getColor(R.color.GREY));
                        round5.setBackgroundColor(getResources().getColor(R.color.GREY));
                        round10.setBackgroundColor(getResources().getColor(R.color.GREY));
                        round15.setBackgroundColor(getResources().getColor(R.color.HORROR_GREEN));
                    }
                });
            }
        });


    }

    public void enableThem()
    {
        final Button playGame =(Button) findViewById(R.id.playgame);
        final Button single =(Button) findViewById(R.id.singleround);
        final Button round2 =(Button) findViewById(R.id.round2);
        final Button user =(Button) findViewById(R.id.definerounds);

        final TextView head = (TextView) findViewById(R.id.textView) ;

        EditText pl1 = (EditText) findViewById(R.id.player1);
        EditText pl2 = (EditText) findViewById(R.id.player2);

        playGame.setEnabled(true);
        pl1.setEnabled(true);
        pl2.setEnabled(true);

        single.setEnabled(false);
        round2.setEnabled(false);
        user.setEnabled(false);
        head.setEnabled(false);
    }
    public String getcontent(int i)
    {
        EditText pl1 = (EditText) findViewById(R.id.player1);
        EditText pl2 = (EditText) findViewById(R.id.player2);
        if(i==1)
        {
            if(pl1.getText().toString().isEmpty())
            {
                return "Player 1";
            }
            else
            {
                return pl1.getText().toString();
            }

        }
        else
        {
            if(pl2.getText().toString().isEmpty())
            {
                return "Player 2";
            }
            else
            {
                return pl2.getText().toString();
            }

        }
    }
    public static String getplay1()
    {
        return pl1name;
    }
    public static String getplay2()
    {
        return pl2name;
    }
    public static int getround()
    {
        return rounds;
    }
    public static void changeflagintentresultpop()
    {
        flag=1;
    }
    public void changeflagintentplayername()
    {
        flag=0;
    }

    public static int getflag()
    {
        return flag;
    }

    public void changeflagpointplayername()
    {
        flagpoints=0;
    }
    public static void changeflagpointresultpopup()
    {
        flagpoints=1;
    }
    public static int getflagpoint()
    {
        return flagpoints;
    }
    public static void stopmusicplayername()
    {
        a.release();
    }
    public static void setflagmusicplayername(int i)
    {
        flagmusicplayername=i;
    }
    public static int getflagmusicplayername()
    {
        return flagmusicplayername;
    }

}
