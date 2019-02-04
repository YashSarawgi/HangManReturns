package com.rainbowocean.hangmanreturns;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CategoryOtherInput extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_other_input);

        DisplayMetrics dm= new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        getWindow().setLayout(((int)(dm.widthPixels*0.8)),((int)(dm.heightPixels*0.6)));

        Button confirm = (Button) findViewById(R.id.categoryconfirm);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText category11 = (EditText) findViewById(R.id.categoryinput);
                if(category11.getText().toString().isEmpty()==true)
                {
                    Toast.makeText(getApplicationContext(),"Please Enter a category",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Category_Activity.setcategory(category11.getText().toString());
                    Intent i = new Intent(getApplicationContext(),Enter_Text.class);
                    startActivity(i);
                }
            }
        });

    }
}
