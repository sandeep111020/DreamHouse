package com.example.dreamhouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.math.MathUtils;

import android.content.Context;
import android.content.SharedPreferences;
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
    ListView l,l2,l3,l4;
    ArrayList tutorials= new ArrayList<>();
//    String tutorials[]
//            = { "Cement", "Sand" ,
//            "Aggregate",
//            "Steel", "Finisher",
//            "Fitting"};
    ArrayList vals= new ArrayList<>();
    ArrayList quantity= new ArrayList<>();
    ArrayList units= new ArrayList<>();
    int value = 1000;
//    String vals[]
//            = { "160000", "20000" ,
//            "98132",
//            "129111", "191083",
//            "12983"};
//    String quantity[] = {"400"};
    ArrayAdapter<String> arr,arr2,arr4;
    private ArrayAdapter<String> arr3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_analysis);
        sum=findViewById(R.id.sum);
        tutorials.add("Product");
        vals.add("Price");
        quantity.add("Quantity");
        units.add("Units");

        value=Integer.parseInt(getIntent().getStringExtra("dimen"));
//        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_MULTI_PROCESS);
//        String Uname = sh.getString("name", "");
//        String Ulocation = sh.getString("location", "");
//        String Udimen = sh.getString("dimension", "");
//        value=Integer.parseInt(Udimen);
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
        l3=findViewById(R.id.listq);
        l4=findViewById(R.id.listunit);
        pushdata();
        int summ = 0;
        for(int i=1;i<vals.size();i++){
            String temp=vals.get(i)+"";
            summ=summ+Integer.parseInt(temp);
        }
        sum.setText("Total: "+summ+"");
        arr = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, tutorials);
        l.setAdapter(arr);
        arr4 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, units);
        l4.setAdapter(arr4);


        arr2 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,vals);
        l2.setAdapter(arr2);

        arr3 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,quantity);
        l3.setAdapter(arr3);


    }
    private  void pushdata(){

            tutorials.add("Cement");
            quantity.add(  (value/2) +"");
            units.add("Bag");
            vals.add(Integer.parseInt(quantity.get(1).toString()) * 382);
            pieEntries.add(new PieEntry(Float.parseFloat(vals.get((1)).toString()), tutorials.get(1).toString()));

            tutorials.add("Steel");
            quantity.add((value*3)+"");
        units.add("KG");
            vals.add(Integer.parseInt(quantity.get(2).toString()) * 45);
            pieEntries.add(new PieEntry(Float.parseFloat(vals.get((2)).toString()), tutorials.get(2).toString()));

        tutorials.add("Bricks");
        quantity.add(19 * value);
        units.add("Piece");
        vals.add(Integer.parseInt(quantity.get(3).toString()) * 7);
        pieEntries.add(new PieEntry(Float.parseFloat(vals.get((3)).toString()), tutorials.get(3).toString()));

        tutorials.add("Aggregate");
        quantity.add(2* value);
        units.add("Cube feet");
        vals.add(Integer.parseInt(quantity.get(4).toString()) * 31);
        pieEntries.add(new PieEntry(Float.parseFloat(vals.get((4)).toString()), tutorials.get(4).toString()));

        tutorials.add("Sand");
        quantity.add( value*2);
        units.add("Cube feet");
        vals.add(Integer.parseInt(quantity.get(5).toString()) * 39);
        pieEntries.add(new PieEntry(Float.parseFloat(vals.get((5)).toString()), tutorials.get(5).toString()));

        tutorials.add("Flooring");
        quantity.add( value);
        units.add("Sq feet");
        vals.add(Integer.parseInt(quantity.get(6).toString()) * 89);
        pieEntries.add(new PieEntry(Float.parseFloat(vals.get((6)).toString()), tutorials.get(6).toString()));

        tutorials.add("Windows");
        quantity.add( value/6);
        units.add("Sq feet");
        vals.add(Integer.parseInt(quantity.get(7).toString()) * 221);
        pieEntries.add(new PieEntry(Float.parseFloat(vals.get((7)).toString()), tutorials.get(7).toString()));

        tutorials.add("Doors");
        quantity.add( value/6);
        units.add("Sq feet");
        vals.add(Integer.parseInt(quantity.get(8).toString()) * 318);
        pieEntries.add(new PieEntry(Float.parseFloat(vals.get((8)).toString()), tutorials.get(8).toString()));

        tutorials.add("Electrical Fitting");
        quantity.add( value/6);
        units.add("Sq feet");
        vals.add(Integer.parseInt(quantity.get(9).toString()) * 58);
        pieEntries.add(new PieEntry(Float.parseFloat(vals.get((9)).toString()), tutorials.get(9).toString()));

        tutorials.add("Painting");
        quantity.add( value*6);
        units.add("Sq feet");
        vals.add(Integer.parseInt(quantity.get(10).toString()) * 24);
        pieEntries.add(new PieEntry(Float.parseFloat(vals.get((10)).toString()), tutorials.get(10).toString()));


        tutorials.add("Sanitary fitting");
        quantity.add( value);
        units.add("Sq feet");
        vals.add(Integer.parseInt(quantity.get(11).toString()) * 62);
        pieEntries.add(new PieEntry(Float.parseFloat(vals.get((11)).toString()), tutorials.get(11).toString()));

        tutorials.add("Kitchen Work");
        quantity.add( value/18);
        units.add("Sq feet");
        vals.add(Integer.parseInt(quantity.get(12).toString()) * 950);
        pieEntries.add(new PieEntry(Float.parseFloat(vals.get((12)).toString()), tutorials.get(12).toString()));


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