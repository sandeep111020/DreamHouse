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

import com.example.dreamhouse.Adapter.TaskAdapter;
import com.example.dreamhouse.AddTask;
import com.example.dreamhouse.Models.MaterialModel;
import com.example.dreamhouse.Models.TaskModel;
import com.example.dreamhouse.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.Random;

import okhttp3.internal.Util;

public class TaskFragment extends Fragment {


    public TaskFragment() {
        // Required empty public constructor
    }
    RecyclerView recyclerView;
    TaskAdapter adapter1;
    Button add;
    private static final int MAX_X_VALUE = 7;
    private static final int MAX_Y_VALUE = 40;
    private static final int MIN_Y_VALUE = 5;
    private static final String SET_LABEL = "Estimated Cost";
    private static final String[] DAYS = { "Foundation", "Walls", "slab", "paints", "plastering", "flooring", "extra" };
    private HorizontalBarChart chart;
    private BarChart barChart;
    int[] valsss = {100000,20000,120000,80000,50000,80000,70000};
    private static Random sRandom = new Random();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_task, container, false);
        recyclerView=root.findViewById(R.id.recycler_menu);
        add= root.findViewById(R.id.add);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        SharedPreferences sh = getContext().getSharedPreferences("MySharedPref", Context.MODE_MULTI_PROCESS);
        String s1 = sh.getString("id", "");
        String num = sh.getString("num", "");
        FirebaseRecyclerOptions<TaskModel> options;
        if(!Character.isDigit(num.charAt(0))){
            Toast.makeText(getContext(), " null", Toast.LENGTH_SHORT).show();
            options =
                    new FirebaseRecyclerOptions.Builder<TaskModel>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("Projectswithoutvendor").child(currentuser).child("Task"), TaskModel.class)
                            .build();

        }else{
            Toast.makeText(getContext(), "Non null", Toast.LENGTH_SHORT).show();
            add.setVisibility(View.GONE);
             options =
                    new FirebaseRecyclerOptions.Builder<TaskModel>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("Projectsvendor").child(num).child(s1).child("Task"), TaskModel.class)
                            .build();
        }
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AddTask.class);
                startActivity(i);
            }
        });
// The value will be default as empty string because for
// the very first time when the app is opened, there is nothing to show



        // .child("24052021130648")
        adapter1 = new TaskAdapter(options,getActivity());
        recyclerView.setAdapter(adapter1);
        adapter1.startListening();
        chart = root.findViewById(R.id.fragment_horizontalbarchart_chart);
        barChart=root.findViewById(R.id.fragment_verticalbarchart_chart);

        BarData data1 = createChartData();
        configureChartAppearance1();
        prepareChartData1(data1);

        BarData data = createChartData();
        configureChartAppearance();
        prepareChartData(data);
//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getActivity(), AddTask.class);
//                startActivity(i);
//            }
//        });
        return root;
    }
    private void configureChartAppearance1() {
        barChart.getDescription().setEnabled(false);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return DAYS[(int) value];
            }
        });
    }
    private void configureChartAppearance() {
        chart.getDescription().setEnabled(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return DAYS[(int) value];
            }
        });
    }
    private BarData createChartData() {
        ArrayList<BarEntry> values = new ArrayList<>();
        for (int i = 0; i < MAX_X_VALUE; i++) {
            float x = i;
            float y = valsss[i];
            values.add(new BarEntry(x, y));
        }
        BarDataSet set1 = new BarDataSet(values, SET_LABEL);

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        BarData data = new BarData(dataSets);

        return data;
    }

    private void prepareChartData(BarData data) {
        data.setValueTextSize(12f);
        chart.setData(data);
        chart.invalidate();
    }
    private void prepareChartData1(BarData data) {
        data.setValueTextSize(12f);
        barChart.setData(data);
        barChart.invalidate();
    }

}
