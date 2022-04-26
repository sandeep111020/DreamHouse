package com.example.dreamhouse.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dreamhouse.Adapter.PaymentAdapter;
import com.example.dreamhouse.Models.PaymentModel;
import com.example.dreamhouse.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class PaymentsFragment extends Fragment {


    private PaymentAdapter adapter1;
    RecyclerView recyclerView;

    public PaymentsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_payments, container, false);
        recyclerView=root.findViewById(R.id.recycler_menu);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();


//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getActivity(), NewPayment.class);
//                startActivity(i);
//            }
//        });
        FirebaseRecyclerOptions<PaymentModel> options =
                new FirebaseRecyclerOptions.Builder<PaymentModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Projectsvendor").child("q2QirNJzWZafOdcIhmDKFJgVjHY2").child("q2QirNJzWZafOdcIhmDKFJgVjHY2"+"My Project").child("Payment"), PaymentModel.class)
                        .build();

        // .child("24052021130648")
        adapter1 = new PaymentAdapter(options,getActivity());
        recyclerView.setAdapter(adapter1);
        adapter1.startListening();
        return root;
    }

}