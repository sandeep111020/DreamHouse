package com.example.dreamhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PMDetailsDisplay extends AppCompatActivity {

    String type;
    int typeint;
    double interest= 0.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pmdetails_display);
        type=getIntent().getStringExtra("val");
        typeint=Integer.parseInt(type);
        if(typeint==1){

        }else if(typeint==2){
          interest=3.00;
        }else if(typeint==3){
           interest=4.00;
        }else if(typeint==4){
            interest=6.40;
        }


    }
}