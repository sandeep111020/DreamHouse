package com.example.dreamhouse.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.dreamhouse.Models.Newprojectmodel;
import com.example.dreamhouse.ProjectProgress;
import com.example.dreamhouse.ProjectUpdate;
import com.example.dreamhouse.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import soup.neumorphism.NeumorphTextView;


public class ProjectAdapter extends FirebaseRecyclerAdapter<Newprojectmodel, ProjectAdapter.myviewholder> {

    String date,taskid,empid;

    Context context;
    DatabaseReference databaseRef;
    String checj;

    public ProjectAdapter(@NonNull FirebaseRecyclerOptions<Newprojectmodel> options, Context context) {
        super(options);
        this.context = context;

    }



    @Override
    protected void onBindViewHolder(@NonNull com.example.dreamhouse.Adapter.ProjectAdapter.myviewholder holder, int position, @NonNull Newprojectmodel model) {

        String chek;

        holder.taskdetails.setText(""+model.getName());
        holder.type.setText(""+model.getType());
        holder.taskdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!model.getType().contains("Without")){
                    Intent i = new Intent(context, ProjectProgress.class);
                    i.putExtra("name",model.getName());
                    i.putExtra("dimen",model.getDimension());
                    i.putExtra("type","with");
                    context.startActivity(i);
                }else {
                    Intent i = new Intent(context, ProjectProgress.class);
                    i.putExtra("name",model.getName());
                    i.putExtra("dimen",model.getDimension());
                    i.putExtra("type","without");
                    context.startActivity(i);
                }

            }
        });







    }

    @NonNull
    @Override
    public com.example.dreamhouse.Adapter.ProjectAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_titlelayout, parent, false);

        return new com.example.dreamhouse.Adapter.ProjectAdapter.myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder {

        NeumorphTextView taskdetails,type;




        public myviewholder(@NonNull View itemView) {
            super(itemView);



            taskdetails = itemView.findViewById(R.id.demoproj);
            type=itemView.findViewById(R.id.demoprojtype);







        }
    }



}