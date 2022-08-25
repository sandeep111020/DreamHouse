package com.example.dreamhouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dreamhouse.Adapter.BanksAdapter;
import com.example.dreamhouse.Adapter.VendorMaterialAdapter;
import com.example.dreamhouse.Models.BankListModel;
import com.example.dreamhouse.Models.Vendoritemmodel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class BankLIstScreen extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BanksAdapter adapter1;
    Button schemes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_list_screen);
        recyclerView=findViewById(R.id.recycler_menu);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        schemes=findViewById(R.id.schemes);
        schemes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BankLIstScreen.this,SchemesScreen.class);
                startActivity(i);
            }
        });
        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();

        FirebaseRecyclerOptions<BankListModel> options =
                new FirebaseRecyclerOptions.Builder<BankListModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("BankLoans"), BankListModel.class)
                        .build();

        // .child("24052021130648")
        adapter1 = new BanksAdapter(options,getApplicationContext());
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