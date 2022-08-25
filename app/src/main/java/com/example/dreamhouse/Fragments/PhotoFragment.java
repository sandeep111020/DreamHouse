package com.example.dreamhouse.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dreamhouse.Adapter.PhotoAdapter;
import com.example.dreamhouse.AddImageScreen;
import com.example.dreamhouse.Models.ImageuploadModel;
import com.example.dreamhouse.Models.TaskModel;
import com.example.dreamhouse.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class PhotoFragment extends Fragment {


    private PhotoAdapter adapter1;
    RecyclerView recyclerView;
    Button add;
    public PhotoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_photo, container, false);
        recyclerView=root.findViewById(R.id.recycler_menuuu);
        add= root.findViewById(R.id.add);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        SharedPreferences sh = getContext().getSharedPreferences("MySharedPref", Context.MODE_MULTI_PROCESS);
        String s1 = sh.getString("id", "");
        String num = sh.getString("num", "");
        FirebaseRecyclerOptions<ImageuploadModel> options;
        if(num.charAt(0)=='&'){
            Toast.makeText(getContext(), " null", Toast.LENGTH_SHORT).show();
            options =
                    new FirebaseRecyclerOptions.Builder<ImageuploadModel>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("Projectswithoutvendor").child(currentuser).child("Images"), ImageuploadModel.class)
                            .build();

        }else{
            Toast.makeText(getContext(), "Non null", Toast.LENGTH_SHORT).show();
            add.setVisibility(View.GONE);
             options =
                    new FirebaseRecyclerOptions.Builder<ImageuploadModel>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("Projectsvendor").child(num).child(s1).child("Images"), ImageuploadModel.class)
                            .build();
        }
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AddImageScreen.class);
                startActivity(i);
            }
        });


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
