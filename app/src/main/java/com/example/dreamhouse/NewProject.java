package com.example.dreamhouse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.dreamhouse.Models.Newprojectmodel;
import com.example.dreamhouse.helper.GeocodingLocation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import soup.neumorphism.NeumorphButton;

public class NewProject extends AppCompatActivity {

    NeumorphButton submit;
    Spinner spin,state;
    EditText name, location, dimesion,budget,sbc;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    CheckBox check;
    Spinner sptype,landtype;
    String[] dept = { "With Contactor","Without Contractor"};
    String[] type={"sq feet","sq yards","cents","sq meters"};
    String[] slt={"Hilly area", "Plain area"};
    String[] sta={"Andhra Pradesh","Arunachal Pradesh","Assam","Bihar","Chhattisgarh","Goa","Gujarat","Haryana","Himachal Pradesh","Jammu and Kashmir","Jharkhand","Karnataka","Kerala","Madhya Pradesh","Maharashtra","Manipur","Meghalaya","Mizoram","Nagaland","Odisha","Punjab","Rajasthan","Sikkim","Tamil Nadu","Telangana","Tripura","Uttarakhand","Uttar Pradesh","West Bengal","Andaman and Nicobar Islands","Chandigarh","Delhi","Lakshadweep","Puducherry"           };
    private Newprojectmodel newproj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);
        submit=findViewById(R.id.submit);
        spin=findViewById(R.id.Dept);
        name= findViewById(R.id.name);
        state=findViewById(R.id.state);
        sbc=findViewById(R.id.sbc);
        sptype=findViewById(R.id.spintype);
        location=findViewById(R.id.location);
        dimesion=findViewById(R.id.dimensions);
        landtype=findViewById(R.id.landtype);
//        check=findViewById(R.id.budget);
        Switch sw = (Switch) findViewById(R.id.switch1);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                } else {
                    // The toggle is disabled
                }
            }
        });
        ArrayAdapter ab = new ArrayAdapter(this,android.R.layout.simple_spinner_item,sta);
        ab.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        state.setAdapter(ab);
        ArrayAdapter ac = new ArrayAdapter(this,android.R.layout.simple_spinner_item,slt);
        ac.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        landtype.setAdapter(ac);
        ArrayAdapter at = new ArrayAdapter(this,android.R.layout.simple_spinner_item,type);
        at.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sptype.setAdapter(at);


        // initializing our object
        // class variable.
//        newproj = new Newprojectmodel();



        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Projects").child(currentuser);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,dept);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Sname=name.getText().toString();
                String Slocation= location.getText().toString();
                String Sdimension= dimesion.getText().toString();
                String heigh="";
//                if(check.isChecked()){
//                    heigh="1";
//                }else{
//                    heigh="2";
//                }
                String Type= spin.getSelectedItem().toString();
                GeocodingLocation locationAddress = new GeocodingLocation();
                locationAddress.getAddressFromLocation(Slocation,
                        getApplicationContext(), new GeocoderHandler());


                // Storing data into SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);

                SharedPreferences.Editor myEdit = sharedPreferences.edit();

                myEdit.putString("name", Sname);
                myEdit.putString("location", Slocation);
                myEdit.putString("dimension", Sdimension);
                myEdit.putString("type", Type);
                myEdit.commit();

//                Newprojectmodel addnewUser = new Newprojectmodel(currentuser,Sname,Slocation,Sdimension,Sbudget,Type);
//                reference.child(currentuser+Sname).setValue(addnewUser);
               // addDatatoFirebase(currentuser,Sname,Slocation,Sdimension,Sbudget);
                Toast.makeText(NewProject.this, "Submission Completed"+currentuser, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(NewProject.this,Onboarding2.class);
                i.putExtra("state",state.getSelectedItem().toString());
                i.putExtra("heigh",heigh);
                startActivity(i);
//                Intent i = new Intent(NewProject.this,ProjectUpdate.class);
//                startActivity(i);
            }
        });
    }
    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }
            submit.setText(locationAddress+"");
        }
    }
    private void addDatatoFirebase(String id, String name, String location, String dimension, String bdget) {
        // below 3 lines of code is used to set
        // data in our object class.
        newproj.setEmpid(id);
        newproj.setBudget(bdget);
        newproj.setDimension(dimension);
        newproj.setLocation(location);
        newproj.setName(name);

        // we are use add value event listener method
        // which is called with database reference.
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // inside the method of on Data change we are setting
                // our object class to our database reference.
                // data base reference will sends data to firebase.
                reference.setValue(newproj);

                // after adding this data we are showing toast message.
                Toast.makeText(NewProject.this, "data added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                Toast.makeText(NewProject.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}