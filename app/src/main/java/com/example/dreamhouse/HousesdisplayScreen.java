package com.example.dreamhouse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

//import com.example.dreamhouse.Adapter.HouselistAdapter;
import com.example.dreamhouse.Adapter.HouselistAdapter;
import com.example.dreamhouse.Adapter.MyListAdapter;
import com.example.dreamhouse.Adapter.ProjectsAdapter;
import com.example.dreamhouse.Models.Houseslist;
import com.example.dreamhouse.Models.Vendoritemmodel;
import com.example.dreamhouse.Models.projectmodel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HousesdisplayScreen extends AppCompatActivity {

    RecyclerView recyclerView;
//    private HouselistAdapter adapter1;
    String val;
    Boolean check;
    ArrayList<Vendoritemmodel> dataModels;
    private HouselistAdapter adapter1;
    ProjectsAdapter adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housesdisplay_screen);
        recyclerView=findViewById(R.id.recycler_menu);
        val=getIntent().getStringExtra("val");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        Query checkuser = FirebaseDatabase.getInstance().getReference("ConstructorHouses");
//        checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//
//                dataModels= new ArrayList<>();
//                for(DataSnapshot snapshot1 : snapshot.getChildren()) {
//                    Vendoritemmodel md=new Vendoritemmodel("d","wd","aw","wc","wc","cww","C");
////                    String des = snapshot1.child("desc").getValue().toString();
////                    String name = snapshot1.child("desc").getValue().toString();
////                    String des = snapshot1.child("desc").getValue().toString();
////                    String des = snapshot1.child("desc").getValue().toString();
////                    String des = snapshot1.child("desc").getValue().toString();
////                    String des = snapshot1.child("desc").getValue().toString();
//                    dataModels.add(md);
//                }
//                  Toast.makeText(HousesdisplayScreen.this, dataModels.get(1) + "" , Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//
//        });
//        MyListAdapter adapter = new MyListAdapter(dataModels);
//        recyclerView.setAdapter(adapter);
        // Retrieving the value using its keys the file name
// must be same in both saving and retrieving the data
        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_MULTI_PROCESS);

// The value will be default as empty string because for
// the very first time when the app is opened, there is nothing to show
        String Type = sh.getString("type", "");
Toast.makeText(HousesdisplayScreen.this,Type,Toast.LENGTH_SHORT).show();
// We can then use the data
       if(!Type.contains("Without")) {
           check=true;
           FirebaseRecyclerOptions<Vendoritemmodel> options =
                   new FirebaseRecyclerOptions.Builder<Vendoritemmodel>()
                           .setQuery(FirebaseDatabase.getInstance().getReference().child("ConstructorHouses"), Vendoritemmodel.class)
                           .build();

           adapter1 = new HouselistAdapter(options, getApplicationContext(), val);
           recyclerView.setAdapter(adapter1);
       }else{
           check=false;
           FirebaseRecyclerOptions<projectmodel> options =
                   new FirebaseRecyclerOptions.Builder<projectmodel>()
                           .setQuery(FirebaseDatabase.getInstance().getReference().child("ProjectsWithout"), projectmodel.class)
                           .build();

           // .child("24052021130648")
           adapter2 = new ProjectsAdapter(options,getApplicationContext());
           recyclerView.setAdapter(adapter2);
       }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(check){
            adapter1.startListening();
        }else{
            adapter2.startListening();
        }


    }

    @Override
    protected void onStop() {
        super.onStop();
        if(check){
            adapter1.stopListening();
        }else{
            adapter2.stopListening();
        }


    }
}