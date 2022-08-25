package com.example.dreamhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dreamhouse.Models.TaskModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddTask extends AppCompatActivity {

    EditText name,total, balance,progress;
    Button submit;
    FirebaseDatabase rootNode;
    DatabaseReference reference;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        name=findViewById(R.id.materialname);
        total=findViewById(R.id.totol);
        progress=findViewById(R.id.progress);
        balance=findViewById(R.id.balance);
        submit=findViewById(R.id.submit);
        rootNode = FirebaseDatabase.getInstance();
        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();

        reference = rootNode.getReference("Projectswithoutvendor");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TaskModel addnewUser = new TaskModel(name.getText().toString(),total.getText().toString(),balance.getText().toString(),progress.getText().toString());
                reference.child(currentuser).child("Task").child(total.getText().toString()).setValue(addnewUser);

            }
        });
    }
}