package com.example.dreamhouse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
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

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import soup.neumorphism.NeumorphButton;

public class NewProject extends AppCompatActivity {

    NeumorphButton submit;
    Spinner spin,state;
    EditText name, location, dimesion,budget,sbc,fstate;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    CheckBox check;
    String finalstate;
    Spinner sptype;
    private static final int REQUEST_LOCATION = 1;
    private static final String TAG = "GeocodingLocation2";
    String[] dept = { "With Contactor","Without Contractor"};
    String[] type={"sq feet","sq yards","cents","sq meters"};
    String[] slt={"Hilly area", "Plain area"};
    String[] sta={"Andhra Pradesh","Arunachal Pradesh","Assam","Bihar","Chhattisgarh","Goa","Gujarat","Haryana","Himachal Pradesh","Jammu and Kashmir","Jharkhand","Karnataka","Kerala","Madhya Pradesh","Maharashtra","Manipur","Meghalaya","Mizoram","Nagaland","Odisha","Punjab","Rajasthan","Sikkim","Tamil Nadu","Telangana","Tripura","Uttarakhand","Uttar Pradesh","West Bengal","Andaman and Nicobar Islands","Chandigarh","Delhi","Lakshadweep","Puducherry"           };
    private Newprojectmodel newproj;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);
        submit=findViewById(R.id.submit);
        spin=findViewById(R.id.Dept);
        name= findViewById(R.id.name);
        fstate=findViewById(R.id.filledstate);
        state=findViewById(R.id.state);
        sbc=findViewById(R.id.sbc);
        sptype=findViewById(R.id.spintype);
        location=findViewById(R.id.location);
        dimesion=findViewById(R.id.dimensions);
//        check=findViewById(R.id.budget);

        ArrayAdapter ab = new ArrayAdapter(this,android.R.layout.simple_spinner_item,sta);
        ab.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        state.setAdapter(ab);

        ArrayAdapter at = new ArrayAdapter(this,android.R.layout.simple_spinner_item,type);
        at.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sptype.setAdapter(at);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            OnGPS();
        } else {
            getLocation();
        }
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
//                i.putExtra("state",state.getSelectedItem().toString());
                i.putExtra("state",finalstate);
                i.putExtra("heigh",heigh);
                startActivity(i);
//                Intent i = new Intent(NewProject.this,ProjectUpdate.class);
//                startActivity(i);
            }
        });
    }
    private void OnGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new  DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(
                NewProject.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                NewProject.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (locationGPS != null) {
                double lat = locationGPS.getLatitude();
                double longi = locationGPS.getLongitude();
                finalstate = getAddress(this,lat,longi);
                location.setText(getAddresscode(this,lat,longi));
                fstate.setText(finalstate.toUpperCase());
//                Toast.makeText(this, finalstate, Toast.LENGTH_SHORT).show();
//                latitude = String.valueOf(lat);
//                longitude = String.valueOf(longi);
//                showLocation.setText("Your Location: " + "\n" + "Latitude: " + latitude + "\n" + "Longitude: " + longitude);
            } else {
                Toast.makeText(this, "Unable to find location.", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public static String getAddress(Context context, double LATITUDE, double LONGITUDE){
        //Set Address
        try {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);

            if (addresses != null && addresses.size() > 0) {
                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
                Log.d(TAG, "getAddress:  address" + address);
                Log.d(TAG, "getAddress:  city" + city);
                Log.d(TAG, "getAddress:  state" + state);
                Log.d(TAG, "getAddress:  postalCode" + postalCode);
                Log.d(TAG, "getAddress:  knownName" + knownName);


                return state;
            }else{
                return  "";
            }

        } catch (IOException e) {
            e.printStackTrace();
            return  e.toString();
        }

    }
    public static String getAddresscode(Context context, double LATITUDE, double LONGITUDE){
        //Set Address
        try {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);

            if (addresses != null && addresses.size() > 0) {
                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
                Log.d(TAG, "getAddress:  address" + address);
                Log.d(TAG, "getAddress:  city" + city);
                Log.d(TAG, "getAddress:  state" + state);
                Log.d(TAG, "getAddress:  postalCode" + postalCode);
                Log.d(TAG, "getAddress:  knownName" + knownName);


                return postalCode;
            }else{
                return  "";
            }

        } catch (IOException e) {
            e.printStackTrace();
            return  e.toString();
        }

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