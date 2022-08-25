package com.example.dreamhouse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.airbnb.lottie.LottieAnimationView;
import com.example.dreamhouse.Adapter.OnBoarding2;
import com.example.dreamhouse.Models.OnBoarditem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Onboarding2 extends AppCompatActivity {



    private LinearLayout pager_indicator;
    private int dotsCount;
    private ImageView[] dots;
    LottieAnimationView lav;

    String area="",win="";
    String state,heigh;


    private ViewPager onboard_pager;



    private Button btn_get_started;

    int previous_pos=0;


    ArrayList<OnBoarditem> onBoardItems=new ArrayList<>();
    private OnBoarding2 mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding2);

        lav=findViewById(R.id.lav_actionBar);
        btn_get_started = (Button) findViewById(R.id.btn_get_started);
        onboard_pager = (ViewPager) findViewById(R.id.pager_introduction);
        pager_indicator = (LinearLayout) findViewById(R.id.viewPagerCountDots);
        state=getIntent().getStringExtra("state");
        heigh=getIntent().getStringExtra("heigh");
        loadData();
        Query checkuser2 = FirebaseDatabase.getInstance().getReference("Windspeed").child(state.toUpperCase());
        checkuser2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){



                    String ss = snapshot.child("zone").getValue(String.class);
                    if(Integer.parseInt(ss)==55){
                        win="veryhigh";
//                        windtxt.setText("You are belonging to zone "+ss+" so you don't need to build high buildings");
                    }else if(Integer.parseInt(ss)==50){
                        win="high";
//                        windtxt.setText("You are belonging to zone "+ss+" so you don't need to build high buildings");

                    }else if(Integer.parseInt(ss)==47){
                        win="high";
//                        windtxt.setText("You are belonging to zone "+ss+" so you have some chances to build high buildings");

                    }else if (Integer.parseInt(ss)==44){
                        win="low";
//                        windtxt.setText("You are belonging to zone "+ss+" so you have some chances to build high buildings");

                    }else if(Integer.parseInt(ss)==39){
                        win="low";
//                        windtxt.setText("You are belonging to zone "+ss+" so you have some chances to build high buildings");

                    }
                    else{
                        win="low";
//                        windtxt.setText("You are belonging to zone "+ss+" so you have good chances  build high buildings");

                    }
//                    wind.setText("Wind Speed is:"+ss+"");



                }
                else{
                    // error.setText("There is no Account with this id");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        Query checkuser3 = FirebaseDatabase.getInstance().getReference("Climatology").child(state.toUpperCase());
        checkuser3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){



                    String ss = snapshot.child("zone").getValue(String.class);
//                    climate.setText("Climatology:"+ss+"");
                    if(ss.contains("mountain")){
                        area="snow";
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
        mAdapter = new OnBoarding2(this,onBoardItems);
        onboard_pager.setAdapter(mAdapter);
        onboard_pager.setCurrentItem(0);
        onboard_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                // Change the current position intimation

                for (int i = 0; i < dotsCount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(Onboarding2.this, R.drawable.non_selected_item_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(Onboarding2.this, R.drawable.selected_item_dot));


                int pos=position+1;

                if(pos==dotsCount&&previous_pos==(dotsCount-1))
                    show_animation();
                else if(pos==(dotsCount-1)&&previous_pos==dotsCount)
                    hide_animation();

                previous_pos=pos;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        btn_get_started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Onboarding2.this,HouseTypeSelectionActivity.class);
                i.putExtra("wind",win);
                i.putExtra("area",area);
                startActivity(i);
            }
        });

        setUiPageViewController();

    }

    // Load data into the viewpager

    public void loadData()
    {

        int[] header = {R.string.ob2_header1, R.string.ob2_header2, R.string.ob2_header3,R.string.ob2_header4,R.string.ob2_header5};
        int[] desc = {R.string.ob2_desc1, R.string.ob2_desc2, R.string.ob2_desc3,R.string.ob2_desc4,R.string.ob2_desc5 };
        int[] imageId = {R.drawable.ear, R.drawable.wind, R.drawable.clim,R.drawable.land,R.drawable.sbc};

        for(int i=0;i<imageId.length;i++)
        {
            OnBoarditem item=new OnBoarditem();
            item.setImageID(imageId[i]);
            item.setTitle(getResources().getString(header[i]));
            item.setDescription(getResources().getString(desc[i]));

            onBoardItems.add(item);
        }
    }

    // Button bottomUp animation

    public void show_animation()
    {
        Animation show = AnimationUtils.loadAnimation(this, R.anim.slide_up_anim);

        btn_get_started.startAnimation(show);
lav.setVisibility(View.GONE);
        show.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                btn_get_started.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                btn_get_started.clearAnimation();

            }

        });


    }

    // Button Topdown animation

    public void hide_animation()
    {
        Animation hide = AnimationUtils.loadAnimation(this, R.anim.slide_down_anim);

        btn_get_started.startAnimation(hide);

        hide.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                btn_get_started.clearAnimation();
                btn_get_started.setVisibility(View.GONE);

            }

        });


    }

    // setup the
    private void setUiPageViewController() {

        dotsCount = mAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(Onboarding2.this, R.drawable.non_selected_item_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(6, 0, 6, 0);

            pager_indicator.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(Onboarding2.this, R.drawable.selected_item_dot));
    }


}