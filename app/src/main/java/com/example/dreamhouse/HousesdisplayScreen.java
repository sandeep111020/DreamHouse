package com.example.dreamhouse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

//import com.example.dreamhouse.Adapter.HouselistAdapter;
import com.example.dreamhouse.Adapter.HouselistAdapter;
import com.example.dreamhouse.Adapter.MyListAdapter;
import com.example.dreamhouse.Models.Houseslist;
import com.example.dreamhouse.Models.Vendoritemmodel;
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
    ArrayList<Vendoritemmodel> dataModels;
    private HouselistAdapter adapter1;

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
        FirebaseRecyclerOptions<Vendoritemmodel> options =
                new FirebaseRecyclerOptions.Builder<Vendoritemmodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("ConstructorHouses"), Vendoritemmodel.class)
                        .build();

        adapter1 = new HouselistAdapter(options,getApplicationContext(),val);
        recyclerView.setAdapter(adapter1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter1.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter1.stopListening();
    }
}