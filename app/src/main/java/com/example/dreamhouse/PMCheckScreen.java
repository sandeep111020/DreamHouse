package com.example.dreamhouse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class PMCheckScreen extends AppCompatActivity {

    TextView age,income,inteest,mar;
    Button proceed,banks;
    private String currentuser,cost,inc="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pmcheck_screen);
        age=findViewById(R.id.age);
        income=findViewById(R.id.income);
        proceed=findViewById(R.id.proceed);
        inteest=findViewById(R.id.interest);
        mar=findViewById(R.id.marrige);
        banks=findViewById(R.id.banks);

        cost=getIntent().getStringExtra("cost");
        currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();

        banks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PMCheckScreen.this,BankLIstScreen.class);
                startActivity(i);
            }
        });
        Query checkuser3 = FirebaseDatabase.getInstance().getReference("UserProfile").child(currentuser);
        checkuser3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){



                    String ss = snapshot.child("age").getValue(String.class);
                    age.setText(""+ss);
                    String sm = snapshot.child("marr").getValue(String.class);
                    mar.setText(""+sm);
                     inc = snapshot.child("income").getValue(String.class);
                    income.setText(""+inc);
                    int incomeval= Integer.parseInt(inc);
                    double interest;
                    int ammount=0;
                if(incomeval>1800000){
                    interest=0.00;
                }else if(incomeval<1800000 && incomeval>1200000){
                    interest=3.00;
                    ammount=1200000;
                }else if(incomeval<1200000 && incomeval>600000){
                    interest=4.00;
                    ammount=900000;
                }else{
                   interest=6.40;
                   ammount=600000;
                }
                if(Integer.parseInt(ss)<18){
                    inteest.setText("You doesn't have any Interest Subsidy & Bank Loans with this yojana beacause of your Age");

                }else if(sm.contains("Un")){
                    inteest.setText("You doesn't have any Interest Subsidy with this yojana beacause you are unmarried.");
                    banks.setVisibility(View.VISIBLE);
                    proceed.setVisibility(View.GONE);
                }
                else if(interest==0.00){
                    inteest.setText("You doesn't have any Interest Subsidy with this yojana beacause of your Income");
                    banks.setVisibility(View.VISIBLE);
                   proceed.setVisibility(View.GONE);
                }else{
                    inteest.setText("Interest Subsidy is "+interest+"% \nMaximum Loan Amount is "+ammount );
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
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int incomeval= Integer.parseInt(inc);
//                if(incomeval>1800000){
//                    Intent  i = new Intent(PMCheckScreen.this,PMDetailsDisplay.class);
//                    i.putExtra("val","1");
//                    startActivity(i);
//                }else if(incomeval<1800000 && incomeval>1200000){
//                    Intent  i = new Intent(PMCheckScreen.this,PMDetailsDisplay.class);
//                    i.putExtra("val","2");
//                    startActivity(i);
//                }else if(incomeval<1200000 && incomeval>600000){
//                    Intent  i = new Intent(PMCheckScreen.this,PMDetailsDisplay.class);
//                    i.putExtra("val","3");
//                    startActivity(i);
//                }else{
//                    Intent  i = new Intent(PMCheckScreen.this,PMDetailsDisplay.class);
//                    i.putExtra("val","4");
//                    startActivity(i);
//                }

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://pmaymis.gov.in/"));
                startActivity(browserIntent);

            }
        });
    }
}