package com.example.dreamhouse.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dreamhouse.Adapter.PhotoAdapter;
import com.example.dreamhouse.Models.ImageuploadModel;
import com.example.dreamhouse.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class PhotoFragment extends Fragment {


    private PhotoAdapter adapter1;
    RecyclerView recyclerView;

    public PhotoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_photo, container, false);
        recyclerView=root.findViewById(R.id.recycler_menuuu);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();

        FirebaseRecyclerOptions<ImageuploadModel> options =
                new FirebaseRecyclerOptions.Builder<ImageuploadModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Projectsvendor").child("q2QirNJzWZafOdcIhmDKFJgVjHY2").child("q2QirNJzWZafOdcIhmDKFJgVjHY2"+"My Project").child("Images"), ImageuploadModel.class)
                        .build();

        // .child("24052021130648")
        adapter1 = new PhotoAdapter(options,getActivity());
        recyclerView.setAdapter(adapter1);
        adapter1.startListening();
//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getActivity(), AddImageScreen.class);
//                startActivity(i);
//            }
//        });
        return root;    }

}
