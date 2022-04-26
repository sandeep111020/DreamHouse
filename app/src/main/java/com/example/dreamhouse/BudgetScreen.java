package com.example.dreamhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class BudgetScreen extends AppCompatActivity {

    EditText dimen;
    Button submit;
    int dimval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_screen);
        dimen= findViewById(R.id.homedim);
        submit=findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                dimval=Integer.parseInt(dimen.getText().toString());
                Intent i = new Intent(BudgetScreen.this,BudgetAnalysis.class);
                startActivity(i);

            }
        });

    }
}