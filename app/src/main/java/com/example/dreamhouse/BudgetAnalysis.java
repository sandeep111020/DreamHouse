package com.example.dreamhouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.math.MathUtils;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class BudgetAnalysis extends AppCompatActivity {
    PieChart pieChart;
    PieData pieData;
    PieDataSet pieDataSet;
    TextView sum;
    ArrayList pieEntries=new ArrayList<>();
    ArrayList PieEntryLabels;
    ListView l,l2,l3;
    ArrayList tutorials= new ArrayList<>();
//    String tutorials[]
//            = { "Cement", "Sand" ,
//            "Aggregate",
//            "Steel", "Finisher",
//            "Fitting"};
    ArrayList vals= new ArrayList<>();
    ArrayList quantity= new ArrayList<>();
    int value = 1000;
//    String vals[]
//            = { "160000", "20000" ,
//            "98132",
//            "129111", "191083",
//            "12983"};
//    String quantity[] = {"400"};
    ArrayAdapter<String> arr,arr2;
    private ArrayAdapter<String> arr3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_analysis);
        sum=findViewById(R.id.sum);
        tutorials.add("Product");
        vals.add("Price");
        quantity.add("Quantity");
        value=Integer.parseInt(getIntent().getStringExtra("dimen"));
        pieChart = findViewById(R.id.pieChart);
        getEntries();
        pieDataSet = new PieDataSet(pieEntries, "label");
        pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        pieDataSet.setSliceSpace(1f);
        pieDataSet.setValueTextColor(Color.WHITE);
        pieDataSet.setValueTextSize(15f);
        pieDataSet.setSliceSpace(2f);
        l = findViewById(R.id.list);
        l2 = findViewById(R.id.listval);
//        l3=findViewById(R.id.listquan);
        pushdata();
        int summ = 0;
        for(int i=1;i<vals.size();i++){
            String temp=vals.get(i)+"";
            summ=summ+Integer.parseInt(temp);
        }
        sum.setText("Total: "+summ+"");
        arr = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, tutorials);
        l.setAdapter(arr);


        arr2 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,vals);
        l2.setAdapter(arr2);

//        arr3 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,quantity);
//        l3.setAdapter(arr3);


    }
    private  void pushdata(){

            tutorials.add("Slab");
            quantity.add(4 * value+"");
            vals.add(Integer.parseInt(quantity.get(1).toString()) * 50);
            pieEntries.add(new PieEntry(Float.parseFloat(vals.get((1)).toString()), tutorials.get(1).toString()));

            tutorials.add("Foundation");
            quantity.add((value/30)+"");
            vals.add(Integer.parseInt(quantity.get(2).toString()) * 5000);
            pieEntries.add(new PieEntry(Float.parseFloat(vals.get((2)).toString()), tutorials.get(2).toString()));

        tutorials.add("Walls");
        quantity.add(4 * value);
        vals.add(Integer.parseInt(quantity.get(3).toString()) * 45);
        pieEntries.add(new PieEntry(Float.parseFloat(vals.get((3)).toString()), tutorials.get(3).toString()));

        tutorials.add("painting");
        quantity.add(8* value);
        vals.add(Integer.parseInt(quantity.get(4).toString()) * 10);
        pieEntries.add(new PieEntry(Float.parseFloat(vals.get((4)).toString()), tutorials.get(4).toString()));

        tutorials.add("flooring");
        quantity.add( value/5);
        vals.add(Integer.parseInt(quantity.get(5).toString()) * 150);
        pieEntries.add(new PieEntry(Float.parseFloat(vals.get((5)).toString()), tutorials.get(5).toString()));

        tutorials.add("Extra" +
                "");
        quantity.add( 2* value);
        vals.add(Integer.parseInt(quantity.get(6).toString()) * 20);
        pieEntries.add(new PieEntry(Float.parseFloat(vals.get((6)).toString()), tutorials.get(6).toString()));


    }
    private void getEntries() {
//        pieEntries =
//        pieEntries.add(new PieEntry(2, 0));
//        pieEntries.add(new PieEntry(4, 1));
//        pieEntries.add(new PieEntry(6, 2));
//        pieEntries.add(new PieEntry(8, 3));
//        pieEntries.add(new PieEntry(7, 4));
//        pieEntries.add(new PieEntry(3, 5));
    }

}