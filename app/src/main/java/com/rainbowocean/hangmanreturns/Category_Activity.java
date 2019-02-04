package com.rainbowocean.hangmanreturns;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Category_Activity extends AppCompatActivity {
    static String category;
    static int roundhere=1;
    static MediaPlayer a= new MediaPlayer();
    static int flagrounddisplay=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_);
        if(flagrounddisplay==0) {
            if (roundhere > 1 && (roundhere % 2 == 1)) {
                Intent i = new Intent(getApplicationContext(), Round_Display.class);
                flagrounddisplay=1;
                startActivity(i);

            }

            a = MediaPlayer.create(this,R.raw.soothingforhomepage);
            a.setLooping(true);
            if(HomeScreen_Activity.getmusicstatus()==0) {
                if (HomeScreen_Activity.getflagmusichomepage() == 1) {
                    if (((int) Player_Name.getflagmusicplayername()) == 1) {
                        a.start();
                    }
                }
            }
        }
        else
        {
            flagrounddisplay=0;
        }


        String namepl1=Player_Name.getplay1();
        String namepl2=Player_Name.getplay2();
        String playername;

        if(Player_Name.getflag()==0) {
            oncreaterounds();
        }
        Button movie= (Button) findViewById(R.id.movie);
        Button country=(Button) findViewById(R.id.country);
        Button fruit=(Button) findViewById(R.id.fruit);
        Button animal=(Button) findViewById(R.id.animal);
        Button other= (Button) findViewById(R.id.others);
        TextView categorytext=(TextView) findViewById(R.id.textView2);
        if(roundhere%2==0)
        {
            playername=namepl2;

        }
        else
        {
            playername=namepl1;

        }
       categorytext.setText((playername+"\n"+"Select Your Category").toString());

        movie.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                category="Movie";
                changeround();
                getintent();

            }
        });
        country.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                category="Country/City";
                changeround();
                getintent();

            }
        });
        animal.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                category="Animal";
                changeround();
                getintent();

            }
        });
        fruit.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                category="Fruits";
                changeround();
                getintent();

            }
        });
        other.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                category="Other";
                changeround();
                otherintent();
            }
        });


    }
    public static void setcategory(String s){
        category=s;
    }
    public static String getCategory()
    {
        return category;
    }
    public void getintent()
    {
        Intent i =new Intent(this,Enter_Text.class);
        startActivity(i);
    }
    public void otherintent(){
        Intent j= new Intent(this,CategoryOtherInput.class);
        startActivity(j);
    }
    public void changeround()
    {
        roundhere++;
    }
    public static void setroundhere(int i)
    {
        roundhere=i;
    }
    public static int returnround(){
        return roundhere;
    }


    public void oncreaterounds()
    {
        roundhere=1;
    }
    public static void stopmusiccategory()
    {
        a.release();
    }
}
