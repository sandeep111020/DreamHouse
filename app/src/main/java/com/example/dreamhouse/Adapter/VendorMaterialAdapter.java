package com.example.dreamhouse.Adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.PixelCopy;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.dreamhouse.Models.MaterialModel;
import com.example.dreamhouse.Models.Vendoritemmodel;
import com.example.dreamhouse.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;


public class VendorMaterialAdapter extends FirebaseRecyclerAdapter<Vendoritemmodel, VendorMaterialAdapter.myviewholder> {

    String date,taskid,empid;

    Context context;
    DatabaseReference databaseRef;
    String checj;

    public VendorMaterialAdapter(@NonNull FirebaseRecyclerOptions<Vendoritemmodel> options, Context context) {
        super(options);
        this.context = context;

    }



    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull Vendoritemmodel model) {


        holder.title.setText(" "+model.getTitle());
        holder.name.setText("By: "+model.getName());
        holder.number.setText("Contact: "+model.getNumber());
        holder.desc.setText("Desc: "+model.getDesc());
        holder.address.setText("From: "+model.getAddress());
        Picasso.get().load(model.getImg()).into(holder.img);





    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vendor_item, parent, false);

        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder {

        TextView name,address,number,title,desc;
        ImageView img;
        Button submit;




        public myviewholder(@NonNull View itemView) {
            super(itemView);



            name = (TextView) itemView.findViewById(R.id.name);
            address = (TextView) itemView.findViewById(R.id.address);
            img=itemView.findViewById(R.id.img1);
            number = (TextView) itemView.findViewById(R.id.number);
            title=itemView.findViewById(R.id.title);
            desc=itemView.findViewById(R.id.des);
            submit=itemView.findViewById(R.id.submit);
            TextView txt=itemView.findViewById(R.id.viewmore);
            RatingBar txt1=itemView.findViewById(R.id.rating);
            txt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(address.getVisibility()!=View.VISIBLE){
                        address.setVisibility(View.VISIBLE);
                        number.setVisibility(View.VISIBLE);
                        desc.setVisibility(View.VISIBLE);
                        submit.setVisibility(View.VISIBLE);

                        txt.setText("View less");

                        txt.setText(Html.fromHtml("<p><u>View less</u></p>").toString());

                    }else {
                        address.setVisibility(view.GONE);
                        number.setVisibility(view.GONE);
                        desc.setVisibility(view.GONE);
                        submit.setVisibility(View.GONE);

                        txt.setText(Html.fromHtml("<p><u>View more</u></p>").toString());
                    }
                }
            });







        }
    }



}