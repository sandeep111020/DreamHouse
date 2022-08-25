package com.example.dreamhouse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphTextView;

public class TopologiesScreen extends AppCompatActivity {
    String state,heigh;
    TextView soil,sestxt,clitxt,windtxt;
    NeumorphButton proc;
    String area="",win="";
    NeumorphTextView sespic,climate,wind,soi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topologies_screen);
        climate=findViewById(R.id.climate);
        wind=findViewById(R.id.wind);
        soil=findViewById(R.id.soiltxt);
        proc=findViewById(R.id.proceed);
        soi=findViewById(R.id.soil);
        sestxt=findViewById(R.id.sespictxt);
        clitxt=findViewById(R.id.climattext);
        windtxt=findViewById(R.id.windtxt);
        state=getIntent().getStringExtra("state");
        heigh=getIntent().getStringExtra("heigh");
        sespic=findViewById(R.id.sespiczone);
        proc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TopologiesScreen.this,HouseTypeSelectionActivity.class);
                i.putExtra("wind",win);
                i.putExtra("area",area);
                startActivity(i);
            }
        });
        sespic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sestxt.getVisibility()==View.VISIBLE){
                sestxt.setVisibility(View.GONE);}
                else{
                    sestxt.setVisibility(View.VISIBLE);
                }
            }
        });
        wind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(windtxt.getVisibility()==View.VISIBLE){
                    windtxt.setVisibility(View.GONE);}
                else{
                    windtxt.setVisibility(View.VISIBLE);
                }
            }
        });
        climate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(clitxt.getVisibility()==View.VISIBLE){
                    clitxt.setVisibility(View.GONE);}
                else{
                    clitxt.setVisibility(View.VISIBLE);
                }
            }
        });
        soi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(soil.getVisibility()==View.VISIBLE){
                    soil.setVisibility(View.GONE);}
                else{
                    soil.setVisibility(View.VISIBLE);
                }
            }
        });
        Query checkuser = FirebaseDatabase.getInstance().getReference("Sespiczones").child(state.toUpperCase());
        checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){



                    String ss = snapshot.child("zone").getValue(String.class);
                    if(Integer.parseInt(ss)==5){
                       sestxt.setText("You are belonging to zone "+ss+" so you don't need to build high buildings");
                    }else if(Integer.parseInt(ss)==4){
                        sestxt.setText("You are belonging to zone "+ss+" so you don't need to build high buildings");

                    }else if(Integer.parseInt(ss)==3){
                        sestxt.setText("You are belonging to zone "+ss+" so you have some chances to build high buildings");

                    }else{
                        sestxt.setText("You are belonging to zone "+ss+" so you have good chances  build high buildings");

                    }
                    sespic.setText("Sesmic zone is:"+ss+"");



                }
                else{
                   // error.setText("There is no Account with this id");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        Query checkuser2 = FirebaseDatabase.getInstance().getReference("Windspeed").child(state.toUpperCase());
        checkuser2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){



                    String ss = snapshot.child("zone").getValue(String.class);
                    if(Integer.parseInt(ss)==55){
                        win="veryhigh";
                        windtxt.setText("You are belonging to zone "+ss+" so you don't need to build high buildings");
                    }else if(Integer.parseInt(ss)==50){
                        win="high";
                        windtxt.setText("You are belonging to zone "+ss+" so you don't need to build high buildings");

                    }else if(Integer.parseInt(ss)==47){
                        win="high";
                        windtxt.setText("You are belonging to zone "+ss+" so you have some chances to build high buildings");

                    }else if (Integer.parseInt(ss)==44){
                        win="low";
                        windtxt.setText("You are belonging to zone "+ss+" so you have some chances to build high buildings");

                    }else if(Integer.parseInt(ss)==39){
                        win="low";
                        windtxt.setText("You are belonging to zone "+ss+" so you have some chances to build high buildings");

                    }
                    else{
                        win="low";
                        windtxt.setText("You are belonging to zone "+ss+" so you have good chances  build high buildings");

                    }
                    wind.setText("Wind Speed is:"+ss+"");



                }
                else{
                    // error.setText("There is no Account with this id");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        Query checkuser3 = FirebaseDatabase.getInstance().getReference("Climatology").child(state.toUpperCase());
        Toast.makeText(this, state.toUpperCase(), Toast.LENGTH_SHORT).show();
        checkuser3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){



                    String ss = snapshot.child("zone").getValue(String.class);
                    climate.setText("Climatology:"+ss+"");
                    if(ss.contains("mountain")){
                        area="snow";
                    }



                }
                else{
                    // error.setText("There is no Account with this id");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });






    }
}