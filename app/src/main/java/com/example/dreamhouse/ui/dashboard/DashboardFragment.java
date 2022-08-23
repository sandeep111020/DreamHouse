package com.example.dreamhouse.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dreamhouse.Adapter.ProjectAdapter;
import com.example.dreamhouse.Models.Newprojectmodel;
import com.example.dreamhouse.NewProject;
import com.example.dreamhouse.ProjectProgress;
import com.example.dreamhouse.ProjectUpdate;
import com.example.dreamhouse.R;
import com.example.dreamhouse.SoilTestScreen;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    CarouselView carouselView;
    LinearLayout budget,soiltest,bot;
    int[] sampleImages = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img1, R.drawable.img5,R.drawable.img6,R.drawable.img7,R.drawable.img8};
    private RecyclerView recyclerView;
    private ProjectAdapter adapter1;
    FloatingActionButton fab;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        TextView newproj= root.findViewById(R.id.newproject);
        recyclerView=root.findViewById(R.id.recycler_menu);
        fab=root.findViewById(R.id.fab);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();

        FirebaseRecyclerOptions<Newprojectmodel> options =
                new FirebaseRecyclerOptions.Builder<Newprojectmodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Projects").child(currentuser), Newprojectmodel.class)
                        .build();

        adapter1 = new ProjectAdapter(options,getActivity());
        recyclerView.setAdapter(adapter1);
        adapter1.startListening();
        newproj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), NewProject.class);
                startActivity(i);
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), SoilTestScreen.class);
                startActivity(i);
            }
        });
//        demo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getActivity(), ProjectUpdate.class);
//                startActivity(i);
//            }
//        });
//        demo2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getActivity(), ProjectProgress.class);
//                startActivity(i);
//            }
//        });
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        carouselView = root.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);
        return root;
    }
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

    @Override
    public void onStop() {
        super.onStop();
        adapter1.startListening();
    }
}