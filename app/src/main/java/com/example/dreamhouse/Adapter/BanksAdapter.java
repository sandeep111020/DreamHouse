package com.example.dreamhouse.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.dreamhouse.Models.BankListModel;
import com.example.dreamhouse.Models.MaterialModel;
import com.example.dreamhouse.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class BanksAdapter extends FirebaseRecyclerAdapter<BankListModel, BanksAdapter.myviewholder> {

    String date,taskid,empid;

    Context context;
    DatabaseReference databaseRef;
    String checj;

    public BanksAdapter(@NonNull FirebaseRecyclerOptions<BankListModel> options, Context context) {
        super(options);
        this.context = context;

    }



    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull BankListModel model) {

        String chek;

        holder.inter.setText("Interest Rate: "+model.getRate());
        holder.name.setText(""+model.getName());
        Picasso.get().load(model.getUrl()).into(holder.img);








    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.banklist_layout, parent, false);

        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder {

        TextView name,inter;
        CircleImageView img;




        public myviewholder(@NonNull View itemView) {
            super(itemView);



            name=itemView.findViewById(R.id.name);
            inter=itemView.findViewById(R.id.number);
            img=itemView.findViewById(R.id.img);








        }
    }



}