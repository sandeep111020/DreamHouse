package com.example.dreamhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import soup.neumorphism.NeumorphButton;

public class SpecificationsScreen extends AppCompatActivity {

    private RadioGroup radioGroup;
    NeumorphButton submit;
    String val;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specifications_screen);

        radioGroup = (RadioGroup)findViewById(R.id.groupradio);
        submit=findViewById(R.id.proceed);
        val=getIntent().getStringExtra("val");


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
                    Toast.makeText(SpecificationsScreen.this,
                            radioButton.getText(),
                            Toast.LENGTH_SHORT)
                            .show();
                }
                Intent i = new Intent(SpecificationsScreen.this,HousesdisplayScreen.class);
                i.putExtra("val",val);
                startActivity(i);
            }
        });

        // Add the Listener to the Submit Button

    }
}