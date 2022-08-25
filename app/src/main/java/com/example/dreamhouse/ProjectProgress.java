package com.example.dreamhouse;

import static android.content.ContentValues.TAG;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dreamhouse.ui.home.HomeViewModel;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.synnapps.carouselview.ImageListener;

public class ProjectProgress extends AppCompatActivity {

    private HomeViewModel homeViewModel;
//    CarouselView carouselView;
    String projectname, dimesions,type;
    LinearLayout budget,soiltest,bot,vendor,plan,update,tech,bank,hide1,hide2;
    TextView name,dim;
    private ActionBar actionBar;
    ImageView img;
//    int[] sampleImages = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5,R.drawable.img6,R.drawable.img7,R.drawable.img8};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_progress);
        actionBar = getSupportActionBar();
        actionBar.setElevation(0);
        img=findViewById(R.id.dview);
        budget=findViewById(R.id.budget);
        name=findViewById(R.id.projname);
        update=findViewById(R.id.update);
        dim=findViewById(R.id.projdimen);
        tech=findViewById(R.id.tech);
        hide1=findViewById(R.id.hide1);
        hide2=findViewById(R.id.hide2);
        bank=findViewById(R.id.banks);
        plan=findViewById(R.id.plans);
        vendor=findViewById(R.id.vendor);
        soiltest=findViewById(R.id.soiltest);
        bot=findViewById(R.id.botscreen);
        projectname=getIntent().getStringExtra("name");
        dimesions=getIntent().getStringExtra("dimen");
        type=getIntent().getStringExtra("type");
        if(!type.contains("without")){
           hide2.setVisibility(View.GONE);
           hide1.setVisibility(View.GONE);
        }
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(ProjectProgress.this,ProjectProfileScreen.class);
                startActivity(i);
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = getPackageManager().getLaunchIntentForPackage("com.example.threedviewapp");
                startActivity(i);

            }
        });
        plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TapTargetView.showFor(ProjectProgress.this,                 // `this` is an Activity
                        TapTarget.forView(findViewById(R.id.plans), "This is a floor plan", "We have the best floor plans, believe me")
                                // All options below are optional
                                .outerCircleColor(R.color.teal_700)      // Specify a color for the outer circle
                                .outerCircleAlpha(0.96f)            // Specify the alpha amount for the outer circle
                                .targetCircleColor(R.color.white)   // Specify a color for the target circle
                                .titleTextSize(20)                  // Specify the size (in sp) of the title text
                                .titleTextColor(R.color.white)      // Specify the color of the title text
                                .descriptionTextSize(10)            // Specify the size (in sp) of the description text
                                .descriptionTextColor(R.color.teal_700)  // Specify the color of the description text
                                .textColor(R.color.teal_200)            // Specify a color for both the title and description text
                                .textTypeface(Typeface.SANS_SERIF)  // Specify a typeface for the text
                                .dimColor(R.color.black)            // If set, will dim behind the view with 30% opacity of the given color
                                .drawShadow(true)                   // Whether to draw a drop shadow or not
                                .cancelable(false)                  // Whether tapping outside the outer circle dismisses the view
                                .tintTarget(true)                   // Whether to tint the target view's color
                                .transparentTarget(false)           // Specify whether the target is transparent (displays the content underneath)
                                .icon(getDrawable(R.drawable.floorplan))                     // Specify a custom drawable to draw as the target
                                .targetRadius(60),                  // Specify the target radius (in dp)
                        new TapTargetView.Listener() {          // The listener can listen for regular clicks, long clicks or cancels
                            @Override
                            public void onTargetClick(TapTargetView view) {
                                super.onTargetClick(view);
                                // This call is optional
                                
                                Intent i= new Intent(ProjectProgress.this,FloorPlansScreen.class);
                                startActivity(i);
                            }
                        });

            }
        });
        bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(ProjectProgress.this,BankLIstScreen.class);
                startActivity(i);
            }
        });
        tech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(ProjectProgress.this,TechisScreen.class);
                startActivity(i);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProjectProgress.this,ProjectUpdate.class);

                startActivity(i);
            }
        });

        dim.setText("Land: "+dimesions+" sqr feet");
        name.setText(projectname+"");


        budget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProjectProgress.this, BudgetAnalysis.class);
                i.putExtra("dimen",dimesions);
                startActivity(i);
            }
        });
        vendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProjectProgress.this, VendorsActivity.class);
                startActivity(i);
            }
        });
        bot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProjectProgress.this, BotScreen.class);
                startActivity(i);
            }
        });
        soiltest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProjectProgress.this, VasthuScreen.class);
                startActivity(i);
            }
        });
//        carouselView = findViewById(R.id.carouselView);
//        carouselView.setPageCount(sampleImages.length);
//        carouselView.setImageListener(imageListener);



    }
//    ImageListener imageListener = new ImageListener() {
//        @Override
//        public void setImageForPosition(int position, ImageView imageView) {
//            imageView.setImageResource(sampleImages[position]);
//        }
//    };
}