package com.example.dreamhouse.Adapter;

import android.content.Context;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;


import com.example.dreamhouse.BudgetAnalysis;
import com.example.dreamhouse.HomeActivity;
import com.example.dreamhouse.Models.Newprojectmodel;
import com.example.dreamhouse.Models.projectmodel;
import com.example.dreamhouse.R;
import com.example.dreamhouse.ViewPdfActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProjectsAdapter extends FirebaseRecyclerAdapter<projectmodel, com.example.dreamhouse.Adapter.ProjectsAdapter.myviewholder>{


    Context context;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    String Sdate;
    private DatabaseReference databaseRef,databaseRef4;
    private DatabaseReference database;
    private String message;
    String Udimen;

    public ProjectsAdapter(@NonNull FirebaseRecyclerOptions<projectmodel> options, Context context) {
        super(options);
        this.context = context;


    }





    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.projectlayout, parent, false);

        return new myviewholder(view);
    }



    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull projectmodel model) {
        holder.title.setText("Name: "+model.getProjecttitle());
        holder.link.setText("Cost: "+model.getProjectlink());
        holder.desc.setText("Description: "+model.getProjectdesc());
        message=model.getProjectimgurl();
        FirebaseDatabase rootNode2;
        DatabaseReference reference2;
        rootNode2 = FirebaseDatabase.getInstance();
        reference2 = rootNode2.getReference("Projects");
        SharedPreferences sh = context.getSharedPreferences("MySharedPref", Context.MODE_MULTI_PROCESS);
        String Uname = sh.getString("name", "");
        String Ulocation = sh.getString("location", "");
        Udimen = sh.getString("dimension", "");
        String Type = sh.getString("type", "");
        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        holder.book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Newprojectmodel addnewUser = new Newprojectmodel("",Uname,Ulocation,Udimen,"100000",Type);
                reference2.child(currentuser).child(currentuser+Uname).setValue(addnewUser);
                Intent i = new Intent(context, HomeActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
        holder.img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ViewPdfActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("url", message);
                context.startActivity(intent);
               /* CharSequence options[] = new CharSequence[]{
                        "Download",
                        "View",
                        "Cancel"
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Choose One");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // we will be downloading the pdf
                        if (which == 0) {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(message));
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        }
                        // We will view the pdf
                        if (which == 1) {
                            Intent intent = new Intent(context, ViewPdfActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtra("url", message);
                            context.startActivity(intent);
                        }
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();*/
            }
        });
        // Picasso.get().load(model.getProjectimgurl()).into(holder.img);


        // After clicking here alert box will come

    }

    class myviewholder extends RecyclerView.ViewHolder {

        TextView title,link,desc;
        TextView img;
        Button book;
        ImageView budget;


        public myviewholder(@NonNull View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.proj_title);
            link = (TextView) itemView.findViewById(R.id.proj_link);

            desc = (TextView) itemView.findViewById(R.id.proj_desc);

            img=(TextView) itemView.findViewById(R.id.proj_image);
            book=(Button) itemView.findViewById(R.id.book);
            budget=itemView.findViewById(R.id.budgetim);

            budget.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, BudgetAnalysis.class);
                    i.putExtra("dimen",Udimen);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
            });



        }
    }



}