package com.rainbowocean.hangmanreturns;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Enter_Text extends AppCompatActivity {

    public static String enterText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter__text);


        EditText edittext = (EditText)findViewById(R.id.editText);
        TextView text =(TextView) findViewById(R.id.category);

        String namepl1=Player_Name.getplay1();
        String namepl2=Player_Name.getplay2();
        String playername;


        if(Category_Activity.returnround()%2==0)
        {
            playername=namepl2;

        }
        else
        {
            playername=namepl1;

        }
        text.setText((playername+"\n"+"Enter Puzzle for\n"+(playername.equals(namepl1)?namepl2:namepl1)+"\n"+"in "+Category_Activity.getCategory()).toString());
                edittext.setFilters(new InputFilter[] {
                new InputFilter() {
                    public CharSequence filter(CharSequence src, int start,
                                               int end, Spanned dst, int dstart, int dend) {
                        if(src.equals("")){ // for backspace
                            return src;
                        }
                        if(src.toString().matches("[a-zA-Z]+")){
                            return src;
                        }
                        return "";
                    }
                }
        });
        Button next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText word = (EditText) findViewById(R.id.editText);
                enterText= word.getText().toString();

                if(enterText.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Please put in value for puzzle",Toast.LENGTH_SHORT).show();
                }
                else if(enterText.length()>10)
                {
                    Toast.makeText(getApplicationContext(),"Please keep the puzzle within 10 letter",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent i = new Intent(getApplicationContext(),HangMan_Activity.class);
                    HomeScreen_Activity.stopmusic();
                    Player_Name.stopmusicplayername();
                    Category_Activity.stopmusiccategory();
                    startActivity(i);
                }
            }
        });
    }
    public static String getword()
    {
        return enterText.toUpperCase();
    }

}
