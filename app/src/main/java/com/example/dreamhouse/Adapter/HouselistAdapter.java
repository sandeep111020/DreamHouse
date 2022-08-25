package com.example.dreamhouse.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.dreamhouse.HomeActivity;
import com.example.dreamhouse.Models.Houseslist;
import com.example.dreamhouse.Models.Newprojectmodel;
import com.example.dreamhouse.Models.Vendoritemmodel;
import com.example.dreamhouse.ProjectProgress;
import com.example.dreamhouse.ProjectUpdate;
import com.example.dreamhouse.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class HouselistAdapter extends FirebaseRecyclerAdapter<Vendoritemmodel, HouselistAdapter.myviewholder> {

    String date,taskid,empid;

    Context context;
    DatabaseReference databaseRef;
    String checj;
    String val;

    public HouselistAdapter(@NonNull FirebaseRecyclerOptions<Vendoritemmodel> options, Context context,String val) {
        super(options);
        this.context = context;
        this.val=val;

    }




    @Override
    protected void onBindViewHolder(@NonNull com.example.dreamhouse.Adapter.HouselistAdapter.myviewholder holder, int position, @NonNull Vendoritemmodel model) {

        String chek;
        String key = getRef(position).getKey();
        if(!val.contains(model.getType())){
            holder.laylay.setLayoutParams(new LinearLayout.LayoutParams(0,0));
            holder.laylay.setVisibility(View.GONE);
        }else{
            Picasso.get().load(model.getImg()).into(holder.img);
            holder.title.setText(" "+model.getTitle());
            holder.name.setText("By: "+model.getName());
            holder.number.setText("Contact: "+model.getNumber());
            holder.desc.setText("Desc: "+model.getDesc());
            holder.address.setText("From: "+model.getAddress());
            Picasso.get().load(model.getImg()).into(holder.img);
            FirebaseDatabase rootNode;
            DatabaseReference reference;
            rootNode = FirebaseDatabase.getInstance();
            reference = rootNode.getReference("Projectsvendor");
            FirebaseDatabase rootNode2;
            DatabaseReference reference2;
            rootNode2 = FirebaseDatabase.getInstance();
            reference2 = rootNode2.getReference("Projects");
            SharedPreferences sh = context.getSharedPreferences("MySharedPref", Context.MODE_MULTI_PROCESS);
            String Uname = sh.getString("name", "");
            String Ulocation = sh.getString("location", "");
            String Udimen = sh.getString("dimension", "");
            String Type = sh.getString("type", "");
            String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();

            holder.submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Newprojectmodel addnewUser = new Newprojectmodel(model.getNumber()+Uname+Udimen,Uname,Ulocation,Udimen,"100000",Type);
                    reference.child(model.getNumber()).child(model.getNumber()+Uname+Udimen).setValue(addnewUser);
                    reference2.child(currentuser).child(currentuser+Uname).setValue(addnewUser);
                    Intent i = new Intent(context, HomeActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);

                }
            });
        }

//        holder.taskdetails.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(!model.getType().contains("Without")){
//                    Intent i = new Intent(context, ProjectUpdate.class);
//                    context.startActivity(i);
//                }else {
//                    Intent i = new Intent(context, ProjectProgress.class);
//                    context.startActivity(i);
//                }
//
//            }
//        });







    }

    @NonNull
    @Override
    public com.example.dreamhouse.Adapter.HouselistAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.houselist_item, parent, false);

        return new com.example.dreamhouse.Adapter.HouselistAdapter.myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder {

        TextView name,number;
        ImageView img;
        LinearLayout laylay;
        TextView address,title,desc;
        Button submit;



        public myviewholder(@NonNull View itemView) {
            super(itemView);



            name = (TextView) itemView.findViewById(R.id.name);
            address = (TextView) itemView.findViewById(R.id.address);
            img=itemView.findViewById(R.id.img1);
            number = (TextView) itemView.findViewById(R.id.number);
            title=itemView.findViewById(R.id.title);
            laylay=itemView.findViewById(R.id.laylay);
            desc=itemView.findViewById(R.id.des);
            submit=itemView.findViewById(R.id.submit);
            TextView txt=itemView.findViewById(R.id.viewmore);
            RatingBar txt1=itemView.findViewById(R.id.rating);
            txt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(txt1.getVisibility()!=View.VISIBLE){
                        txt1.setVisibility(View.VISIBLE);
                        address.setVisibility(View.VISIBLE);
                        number.setVisibility(View.VISIBLE);
                        desc.setVisibility(View.VISIBLE);
                        submit.setVisibility(View.VISIBLE);
                        txt.setText(R.string.view_less);

                    }else {
                        txt1.setVisibility(view.GONE);
                        address.setVisibility(view.GONE);
                        number.setVisibility(view.GONE);
                        desc.setVisibility(view.GONE);
                        submit.setVisibility(View.GONE);
                        txt.setText(R.string.view_more);
                    }
                }
            });







        }
    }



}