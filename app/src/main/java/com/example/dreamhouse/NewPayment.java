package com.example.dreamhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.dreamhouse.Models.PaymentModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewPayment extends AppCompatActivity {

    EditText name,total;
    Button submit;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_payment);
        name=findViewById(R.id.materialname);
        total=findViewById(R.id.totol);
        submit=findViewById(R.id.submit);
        rootNode = FirebaseDatabase.getInstance();
        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();

        reference = rootNode.getReference("Projectswithoutvendor");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PaymentModel addnewUser = new PaymentModel(name.getText().toString(),total.getText().toString());
                reference.child(currentuser).child("Payment").child(name.getText().toString()).setValue(addnewUser);

            }
        });
    }
}