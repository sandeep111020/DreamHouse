package com.example.dreamhouse.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.dreamhouse.Fragments.BottomSheetFragment;
import com.example.dreamhouse.MyHistory;
import com.example.dreamhouse.NewProject;
import com.example.dreamhouse.R;
import com.example.dreamhouse.UserProfile;


public class NotificationsFragment extends Fragment {

    public static final String TAG = "bottom_sheet";


    private NotificationsViewModel notificationsViewModel;
    TextView myprojects,myhistory,privacypolicy,logout,profile;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        TextView lang = root.findViewById(R.id.language);
        myhistory=root.findViewById(R.id.myhistory);
        myprojects=root.findViewById(R.id.myproject);
        profile=root.findViewById(R.id.edit);
        privacypolicy=root.findViewById(R.id.privacypolicy);
        logout=root.findViewById(R.id.logout);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), UserProfile.class);
                startActivity(i);
            }
        });

        myprojects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getActivity(), NewProject.class);
                startActivity(i);
            }
        });
        myhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getActivity(), MyHistory.class);
                startActivity(i);
            }
        });
        privacypolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getActivity(), NewProject.class);
                startActivity(i);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getActivity(), NewProject.class);
                startActivity(i);
            }
        });
        lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetFragment fragment = new BottomSheetFragment();
                fragment.show(getActivity().getSupportFragmentManager(), TAG);
            }
        });
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;

    }

}