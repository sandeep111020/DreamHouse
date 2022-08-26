package com.example.dreamhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import soup.neumorphism.NeumorphButton;

public class SpecificationsScreen extends AppCompatActivity {

    private RadioGroup radioGroup;
    NeumorphButton submit;
    TextInputEditText floors,beedrooms,bathrooms;
    String val;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specifications_screen);
        floors=findViewById(R.id.inputUsername);
        beedrooms=findViewById(R.id.bedrooms);
        bathrooms=findViewById(R.id.bathrooms);
        radioGroup = (RadioGroup)findViewById(R.id.groupradio);
        submit=findViewById(R.id.proceed);
        val=getIntent().getStringExtra("val");
        floors.setText("1");
        beedrooms.setText("1");
        bathrooms.setText("1");


        // Uncheck or reset the radio buttons initially
        radioGroup.clearCheck();

        // Add the Listener to the RadioGroup
        radioGroup.setOnCheckedChangeListener(
                new RadioGroup
                        .OnCheckedChangeListener() {
                    @Override

                    // The flow will come here when
                    // any of the radio buttons in the radioGroup
                    // has been clicked

                    // Check which radio button has been clicked
                    public void onCheckedChanged(RadioGroup group,
                                                 int checkedId)
                    {

                        // Get the selected Radio Button
                        RadioButton
                                radioButton
                                = (RadioButton)group
                                .findViewById(checkedId);
                    }
                });

        // Add the Listener to the Submit Button
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {

                // When submit button is clicked,
                // Ge the Radio Button which is set
                // If no Radio Button is set, -1 will be returned
                int selectedId = radioGroup.getCheckedRadioButtonId();
                if (selectedId == -1) {
                    Toast.makeText(SpecificationsScreen.this,
                            "No answer has been selected",
                            Toast.LENGTH_SHORT)
                            .show();
                }
                else {

                    RadioButton radioButton
                            = (RadioButton)radioGroup
                            .findViewById(selectedId);

                    // Now display the value of selected item
                    // by the Toast message
//                    Toast.makeText(SpecificationsScreen.this,
//                            radioButton.getText(),
//                            Toast.LENGTH_SHORT)
//                            .show();
                }
                if(Integer.parseInt(floors.getText().toString())>3){
                    Toast.makeText(SpecificationsScreen.this,
                            "Your maximum limit is 3 floors",
                            Toast.LENGTH_SHORT)
                            .show();
                }else if(Integer.parseInt(beedrooms.getText().toString())>3){
                    Toast.makeText(SpecificationsScreen.this,
                            "Your maximum limit is 3 bedrooms",
                            Toast.LENGTH_SHORT)
                            .show();
                }else if(Integer.parseInt(bathrooms.getText().toString())>3){
                    Toast.makeText(SpecificationsScreen.this,
                            "Your maximum limit is 3 bathrooms",
                            Toast.LENGTH_SHORT)
                            .show();
                }else {
                    Intent i = new Intent(SpecificationsScreen.this, HousesdisplayScreen.class);
                    i.putExtra("val", val);
                    startActivity(i);
                }
            }
        });

        // Add the Listener to the Submit Button

    }
}