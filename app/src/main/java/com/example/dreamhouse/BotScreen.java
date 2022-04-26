package com.example.dreamhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BotScreen extends AppCompatActivity {
    Button bot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bot_screen);

        TextView txt=findViewById(R.id.text1);
        TextView txt12=findViewById(R.id.text12);

        bot=findViewById(R.id.bot);
        bot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(BotScreen.this,ChatBot.class);
                startActivity(i);
            }
        });

        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(txt12.getVisibility()!=View.VISIBLE) {
                    txt12.setVisibility(View.VISIBLE);
                } else{
                    txt12.setVisibility(View.GONE);
                }
            }
        });

        TextView txt2=findViewById(R.id.text2);
        TextView txt21=findViewById(R.id.text21);


        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txt21.getVisibility()!=View.VISIBLE) {
                    txt21.setVisibility(View.VISIBLE);
                }else {
                    txt21.setVisibility(View.GONE);
                }
            }
        });

        TextView txt3=findViewById(R.id.text3);
        TextView txt31=findViewById(R.id.text31);

        txt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txt31.getVisibility()!=View.VISIBLE) {
                    txt31.setVisibility(View.VISIBLE);
                }else {
                    txt31.setVisibility(View.GONE);
                }
            }
        });

        TextView txt4=findViewById(R.id.text4);
        TextView txt41=findViewById(R.id.text41);
        txt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txt41.getVisibility() != View.VISIBLE){
                    txt41.setVisibility(View.VISIBLE);
                }else {
                    txt41.setVisibility(View.GONE);
                }
            }
        });

        TextView txt5=findViewById(R.id.text5);
        TextView txt51=findViewById(R.id.text51);

        txt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txt51.getVisibility() != View.VISIBLE){
                    txt51.setVisibility(View.VISIBLE);
                }else {
                    txt51.setVisibility(View.GONE);
                }
            }
        });
        TextView txt6=findViewById(R.id.text6);
        TextView txt61=findViewById(R.id.text61);

        txt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txt61.getVisibility()!= View.VISIBLE){
                    txt61.setVisibility(View.VISIBLE);
                }else {
                    txt61.setVisibility(View.GONE);
                }
            }
        });
        TextView txt7=findViewById(R.id.text7);
        TextView txt71=findViewById(R.id.text71);

        txt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txt71.getVisibility()!= View.VISIBLE){
                    txt71.setVisibility(View.VISIBLE);
                }else {
                    txt71.setVisibility(View.GONE);
                }
            }
        });

    }
}