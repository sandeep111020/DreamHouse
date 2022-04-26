package com.example.dreamhouse.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.dreamhouse.Models.Vendoritemmodel;
import com.example.dreamhouse.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{
    private ArrayList<Vendoritemmodel> listdata;

    // RecyclerView recyclerView;
    public MyListAdapter(ArrayList<Vendoritemmodel> listdata) {
        this.listdata = listdata;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.houselist_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
      //  final Vendoritemmodel myListData = listdata.get(position);
//        holder.textView.setText(listdata[position].getDes);
//        holder.imageView.setImageResource(listdata[position].getImgId());
//        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(view.getContext(),"click on item: "+myListData.getDescription(),Toast.LENGTH_LONG).show();
//            }
//        });
//       // String key = getRef(position).getKey();
//        if(!val.contains(model.getType())){
//            holder.laylay.setVisibility(View.GONE);
//        }
        Picasso.get().load(listdata.get(position).getImg()).into(holder.img);
        holder.title.setText(" "+ listdata.get(position).getTitle());
        holder.name.setText("By: "+ listdata.get(position).getName());
        holder.number.setText("Contact: "+ listdata.get(position).getNumber());
        holder.desc.setText("Desc: "+ listdata.get(position).getDesc());
        holder.address.setText("From: "+ listdata.get(position).getAddress());
        Picasso.get().load(listdata.get(position).getImg()).into(holder.img);
        FirebaseDatabase rootNode;
        DatabaseReference reference;
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Projectsvendor");

//        holder.submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Newprojectmodel addnewUser = new Newprojectmodel(key,"My Project","Vizag","1000","100000","With Contractor");
//                reference.child(key).child(key+"My Project").setValue(addnewUser);
//
//            }
//        });
    }


    @Override
    public int getItemCount() {
        return 20;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,number;
        ImageView img;
        LinearLayout laylay;
        TextView address,title,desc;
        Button submit;

        public ViewHolder(View itemView) {
            super(itemView);
//            this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
//            this.textView = (TextView) itemView.findViewById(R.id.textView);
//            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);
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