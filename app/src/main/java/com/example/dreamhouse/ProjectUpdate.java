package com.example.dreamhouse;

import android.os.Bundle;

import com.example.dreamhouse.Adapter.MyAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.ActionBar;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.dreamhouse.ui.main.SectionsPagerAdapter;
import com.example.dreamhouse.databinding.ActivityProjectUpdateBinding;

public class ProjectUpdate extends AppCompatActivity {

    private ActivityProjectUpdateBinding binding;
    TabLayout tabLayout;
    ViewPager viewPager;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar = getSupportActionBar();
       // actionBar.setElevation(0);

        binding = ActivityProjectUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        tabLayout=(TabLayout)findViewById(R.id.tabs);
        viewPager=(ViewPager)findViewById(R.id.view_pager);

        tabLayout.addTab(tabLayout.newTab().setText("Material"));
        tabLayout.addTab(tabLayout.newTab().setText("Payments"));
        tabLayout.addTab(tabLayout.newTab().setText("Tasks"));
        tabLayout.addTab(tabLayout.newTab().setText("Photos"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final MyAdapter adapter = new MyAdapter(this,getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        FloatingActionButton fab = binding.fab;

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}

