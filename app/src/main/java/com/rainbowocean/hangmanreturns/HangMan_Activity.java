package com.rainbowocean.hangmanreturns;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class HangMan_Activity extends AppCompatActivity {
    String character;
    int gloi=0;
    int butid;
    int i,imagei;
    static int flagmusic=0;
    static int winstatus;

//
    static int points1;
    static int points2;

    MediaPlayer homepage = new MediaPlayer();


    int round=Player_Name.getround();


    ArrayList<TextView> textList = new ArrayList<>();
    ArrayList<Button> buttonList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hang_man_);

        winstatus=0;

        homepage = MediaPlayer.create(this,R.raw.hangmanpagesound);
        homepage.setLooping(true);
        if(HomeScreen_Activity.getmusicstatus()==0) {
            playmusic();
        }
        String pl1= Player_Name.getplay1();
        String pl2= Player_Name.getplay2();

        TextView hangmanscreenplayername1=(TextView)findViewById(R.id.pl1);
        TextView hangmanscreenplayername2=(TextView)findViewById(R.id.pl2);
        hangmanscreenplayername1.setText(pl1);
        hangmanscreenplayername2.setText(pl2);

        TextView po2= (TextView) findViewById(R.id.points2);
        TextView po1= (TextView) findViewById(R.id.points1);


        TextView hint = (TextView) findViewById(R.id.hint);
        hint.setText(hint.getText() + Category_Activity.getCategory());

        Log.v("Hello","Pos1");
        if(Player_Name.getflagpoint()==0) {
         points1=0;
            points2=0;
        }
        else
        {
            po1.setText(points1+"");
            po2.setText(points2+"");
        }

        LinearLayout answer = (LinearLayout) findViewById(R.id.text);
        //this part of the code is to get the max pixels in the width of the device
        WindowManager mWinMgr = (WindowManager) getApplicationContext().getSystemService(this.WINDOW_SERVICE);
        int displayWidth = mWinMgr.getDefaultDisplay().getWidth();


        //this for loop is to set the EditText in the Linear Layout specified for it and also fills textList ArrayList
        InputFilter[] filterArray = new InputFilter[1];
        filterArray[0]= new InputFilter.LengthFilter(1);
        for (i = 0; i < Enter_Text.getword().length(); i++) {
            EditText text = new EditText(this);
            text.setFilters(filterArray);
            text.setText(" ");
            text.setCursorVisible(false);
            text.clearFocus();
            text.setWidth(displayWidth / 10);
            text.setId(i);
//            text.setTextColor(getResources().getColor(R.color.HORROR_GREEN));
            text.setGravity(Gravity.CENTER_HORIZONTAL);
            answer.addView(text);
            textList.add(text);
        }

        Log.v("Hello","Pos2");


        LinearLayout l1 = (LinearLayout) findViewById(R.id.atoi);
        final int childCount = l1.getChildCount();

        for (i=0;i<childCount;i++)
        {
            Button but=(Button) l1.getChildAt(i);
            but.setText(((Button) l1.getChildAt(i)).getText().toString().toUpperCase());
            but.setOnClickListener(new View.OnClickListener()
            {
                int j;
                @Override
                public void onClick(View v) {
                    for(j=0;j<childCount;j++)
                    {
                        if(v.getId()==buttonList.get(j).getId())
                        {

                            int flag=checkChar(setChar(j));

                            if(flag==0){
                                imageUpdate();

                                buttonList.get(j).setBackgroundColor(Color.RED);
                            }
                            else{
                                buttonList.get(j).setBackgroundColor(Color.GREEN);
                            }
                            buttonList.get(j).setEnabled(false);
                        }
                    }

                }
            });
            buttonList.add(but);
        }

   Log.v("Hello","Pos3");
        LinearLayout l2 = (LinearLayout) findViewById(R.id.jtor);
        final int childCount2 = l2.getChildCount();

        for (i=0;i<childCount2;i++)
        {
            Button but=(Button) l2.getChildAt(i);
            but.setText(((Button) l2.getChildAt(i)).getText().toString().toUpperCase());
            but.setOnClickListener(new View.OnClickListener()
            {
                int j;
                @Override
                public void onClick(View v) {
                    for(j=9;j<childCount2+9;j++)
                    {
                        if(v.getId()==buttonList.get(j).getId())
                        {
                            int flag=checkChar(setChar(j));

                            if(flag==0){
                                imageUpdate();

                                buttonList.get(j).setBackgroundColor(Color.RED);
                            }
                            else{
                                buttonList.get(j).setBackgroundColor(Color.GREEN);
                            }
                            buttonList.get(j).setEnabled(false);
                        }
                    }

                }
            });
            buttonList.add(but);
        }

        //LINEAR LAYOUT 3
        LinearLayout l3 = (LinearLayout) findViewById(R.id.stoz);
        final int childCount3 = l3.getChildCount();

        for (i=0;i<childCount3;i++)
        {
            Button but=(Button) l3.getChildAt(i);
            but.setText(((Button) l3.getChildAt(i)).getText().toString().toUpperCase());
            but.setOnClickListener(new View.OnClickListener()
            {
                int j;
                @Override
                public void onClick(View v) {
                    for(j=18;j<childCount3+18;j++)
                    {
                        if(v.getId()==buttonList.get(j).getId())
                        {
                            int flag=checkChar(setChar(j));

                            if(flag==0){
                                imageUpdate();

                                buttonList.get(j).setBackgroundColor(Color.RED);
                            }
                            else{
                                buttonList.get(j).setBackgroundColor(Color.GREEN);
                            }
                            buttonList.get(j).setEnabled(false);
                        }
                    }

                }
            });
            buttonList.add(but);
        }

        printHint();
        verifywin();

    }



    public String setChar(int index)
    {
        Log.v("Hello","Pos4");
        char ch= (char)(65+index);
        return Character.toString(ch);
    }

    public int  checkChar(String s)
    {
        Log.v("Hello","Pos5");
        int flag=0;
        String ch=Enter_Text.getword();
        String[] temp = Enter_Text.getword().split("");
        for(int i=1;i<=Enter_Text.getword().length();i++)
        {

            if(temp[i].equals(s))
            {
                textList.get(i-1).setText(s);
                verifywin();
                flag=1;
            }

        }
        return flag;
    }


    public void printHint()
    {
        Log.v("Hello","Pos6");
        Random x = new Random();
        if(Enter_Text.getword().length()<=4) {
            int a;
            a = x.nextInt(Enter_Text.getword().length())+1;
            String[] temp = Enter_Text.getword().split("");
            String hint = temp[a];
            for (i = 1; i <=Enter_Text.getword().length(); i++) {
                if ((i == a)||temp[a].equals(temp[i])) {
                    textList.get(i-1).setText(temp[a]);
                    for(int j=0;j<26;j++)
                    {
                        if(buttonList.get(j).getText().toString().equals(temp[a]))
                        {
                           buttonList.get(j).setBackgroundColor(Color.GREEN);
                            buttonList.get(j).setEnabled(false);
                        }
                    }
                }

            }

        }
        else {
            int a=1, b=1;
            while (a == b) {
                a =x.nextInt(Enter_Text.getword().length())+1;
                b= x.nextInt(Enter_Text.getword().length())+1;
            }
            String[] temp = Enter_Text.getword().split("");
            for (i = 1; i <=Enter_Text.getword().length(); i++) {
                if ((i == a)||temp[a].equals(temp[i])) {
                    textList.get(i-1).setText(temp[a]);
                    for(int j=0;j<26;j++)
                    {
                        if(buttonList.get(j).getText().toString().equals(temp[a]))
                        {
                            buttonList.get(j).setBackgroundColor(Color.GREEN);
                            buttonList.get(j).setEnabled(false);
                        }
                    }
                }
                if ((i == b)||temp[b].equals(temp[i])) {
                    textList.get(i-1).setText(temp[b]);
                    for(int j=0;j<26;j++)
                    {
                        if(buttonList.get(j).getText().toString().equals(temp[b]))
                        {
                            buttonList.get(j).setBackgroundColor(Color.GREEN);
                            buttonList.get(j).setEnabled(false);
                        }
                    }
                }
            }
        }
    }


    public void imageUpdate()

    {
        Log.v("Hello","Pos7");
        ImageView image = (ImageView) findViewById(R.id.imageView);
        imagei++;
        switch(imagei)
        {
            case 1: image.setImageResource(R.drawable.without_help);
                break;
            case 2:image.setImageResource(R.drawable.without_body);
                break;
            case 3:image.setImageResource(R.drawable.without_hands);
                break;
            case 4:image.setImageResource(R.drawable.without_left_hand);
                break;
            case 5:image.setImageResource(R.drawable.without_legs);
                break;
            case 6:image.setImageResource(R.drawable.without_left_leg);
                break;
            default:image.setImageResource(R.drawable.complete_hang);
                winstatus=1;
                Intent i = new Intent(this,Resultpopup.class);
                setFlagmusc(1);
                Log.v("Hello","Pos21");
                if(Category_Activity.returnround()%2==0)
                {
                   points2++;
                    Log.v("Hello","Pos22");
                    TextView po2= (TextView) findViewById(R.id.points2);
                    po2.setText(""+points2);
                }
                else
                {
                   points1++;
                    TextView po1= (TextView) findViewById(R.id.points1);
                    po1.setText(""+points1);

                }
                stopmusic();
                startActivity(i);
                break;
        }
    }

    public void verifywin(){

        Log.v("Hello","Pos8");
        String[] tempword = Enter_Text.getword().split("");
        String[] tempedittext = new String[Enter_Text.getword().length()];
      int i,flag=0;
        for(i=0;i<Enter_Text.getword().length();i++){
            tempedittext[i]=textList.get(i).getText().toString();
            if(!tempedittext[i].equals(tempword[i+1])){
                flag=1;
                break;
            }

        }
        if(flag==0){
            flagmusic=0;
            Intent intent = new Intent(this,Resultpopup.class);
            Log.v("Hello","Pos9");
            if(Category_Activity.returnround()%2==1)
            {
                points2++;
                Log.v("Hello","Pos10");
                TextView po2= (TextView) findViewById(R.id.points2);
                po2.setText(""+points2);
            }
            else
            {
                points1++;
                Log.v("Hello","Pos11");
                TextView po1= (TextView) findViewById(R.id.points1);
                po1.setText(""+points1);

            }
            stopmusic();
            startActivity(intent);
        }

    }

    public static int returnpoint1()
    {
        return points1;
    }
    public static int returnpoint2()
    {
        return points2;
    }

    public static int sendflagmusic()
    {
        return flagmusic;
    }

    public static void setFlagmusc(int i){
    flagmusic=i;
    }

    public void playmusic()
    {
        homepage.start();
    }
    public void stopmusic()
    {
        homepage.release();
    }
    public static int getwinstatus()
    {
        return winstatus;
    }

}

