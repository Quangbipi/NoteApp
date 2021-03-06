package com.quangminh.timeforlife;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.quangminh.timeforlife.AdapterProject.AdapterStatisMonth;
import com.quangminh.timeforlife.model.ModelWeek;

import java.util.ArrayList;
import java.util.List;

public class FragmentStatisticalYear extends Fragment {

    LineChart lineChart;
    RecyclerView statisticalMonth;
    AdapterStatisMonth adapterStatisMonth;
    List<ModelWeek> weekList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_statistical_year, container, false);
        anhXa(view);

        setUpChart();
        setChart();

        weekList = new ArrayList<>();
        weekList = getWorkWeek();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        statisticalMonth.setLayoutManager(linearLayoutManager);
        return view;
    }

    private void anhXa(View view) {
        lineChart = view.findViewById(R.id.line_chart);
        statisticalMonth = view.findViewById(R.id.statis_month);

    }

    @Override
    public void onStart() {
        super.onStart();
        adapterStatisMonth = new AdapterStatisMonth(weekList, getContext());
        statisticalMonth.setAdapter(adapterStatisMonth);
    }

    private void setUpChart(){
        LineDataSet lineDataSet = new LineDataSet(DataVl(),"");
        lineDataSet.setLineWidth(2f); // set ????? d??y line
        lineDataSet.setCircleRadius(0f); //set ????? d??y ch???m
        lineDataSet.setCircleHoleRadius(0.01f);// set ch???m
        lineDataSet.setCircleColor(Color.WHITE);
        lineDataSet.setHighlightEnabled(false);
        lineDataSet.setColor(getResources().getColor(R.color.color_main, null)); // set m??u line
        lineDataSet.setHighLightColor(Color.BLACK); //set m??u ???????ng highlight
        lineDataSet.setDrawValues(false); //set ch??? s??? ??? ch???m
        lineDataSet.setDrawCircles(false);//set ch???m

        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);//set mode
        lineDataSet.setCubicIntensity(0.2f);// ????? smooth
        lineDataSet.setDrawFilled(true);// m??u ??? d?????i line
        lineDataSet.setFillColor(getResources().getColor(R.color.fill_color, null));// set m??u ??? d?????i line
        lineDataSet.setFillAlpha(20);// set ????? m???



        Legend legend = lineChart.getLegend();
        legend.setEnabled(false);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setTextColor(Color.RED);


        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);


        LineData data = new LineData(dataSets);
        lineChart.setData(data);
        lineChart.invalidate();
    }
    private ArrayList<Entry> DataVl(){
        ArrayList<Entry> dataVal = new ArrayList<Entry>();
        dataVal.add(new Entry(1, 2));
        dataVal.add(new Entry(2, 4));
        dataVal.add(new Entry(3, 3));
        dataVal.add(new Entry(4, 6));
        dataVal.add(new Entry(5, 7));
        dataVal.add(new Entry(6, 5));
        dataVal.add(new Entry(7, 3));
        dataVal.add(new Entry(8, 6));
        dataVal.add(new Entry(9, 2));
        dataVal.add(new Entry(10, 1));
        dataVal.add(new Entry(11, 3));
        dataVal.add(new Entry(12, 5));

        return dataVal;
    }

    private void setChart(){
        //set ???????ng tr??i
        lineChart.getAxisLeft().setDrawGridLines(false);// line ngang
        lineChart.getAxisLeft().setDrawLabels(false);//set ch??? s??? tr??i
        lineChart.getAxisLeft().setDrawAxisLine(false);//c???t tr??i cu???i

        //set ???????ng d?????i
        lineChart.getXAxis().setDrawGridLines(true);// line d???c
        lineChart.getXAxis().setDrawLabels(true);//ch??? s??? ngang
        lineChart.getXAxis().setDrawAxisLine(true);// line ngang cu???i
        lineChart.getXAxis().setAxisLineColor(getResources().getColor(R.color.color_main, null));
        lineChart.getXAxis().setAxisLineWidth(3f);
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChart.getXAxis().setDrawGridLinesBehindData(false);
        lineChart.getXAxis().setGridColor(getResources().getColor(R.color.white, null));
        lineChart.getXAxis().setGridLineWidth(0.5f);

        //set ???????ng ph???i
        lineChart.getAxisRight().setDrawGridLines(false);//line ngang
        lineChart.getAxisRight().setDrawLabels(false);//ch??? s??? ph???i
        lineChart.getAxisRight().setDrawAxisLine(false);//line ph???i cu???i

        lineChart.getDescription().setEnabled(false);
        lineChart.animateX(500, Easing.EaseInBounce);
        lineChart.getXAxis().setValueFormatter(new MyAxisValueFormatter());
        lineChart.getXAxis().setTextSize(8f);

    }

    private class MyAxisValueFormatter extends ValueFormatter {

        @Override
        public String getAxisLabel(float value, AxisBase axis) {
            axis.setLabelCount(12, true);
            return "Th??ng " + (int)value;
        }
    }
    private ArrayList<ModelWeek> getWorkWeek(){
        ArrayList<ModelWeek> modelWeeks = new ArrayList<>();
        modelWeeks.add(new ModelWeek(1, 1, 1, 2, 3));
        modelWeeks.add(new ModelWeek(2, 2, 2, 2, 4));
        modelWeeks.add(new ModelWeek(3, 1, 1, 1, 2));
        modelWeeks.add(new ModelWeek(4, 4, 3, 2, 5));
        modelWeeks.add(new ModelWeek(5, 2, 2, 1, 3));
        modelWeeks.add(new ModelWeek(6, 2, 2, 1, 3));
        modelWeeks.add(new ModelWeek(7, 2, 2, 1, 3));
        modelWeeks.add(new ModelWeek(8, 2, 2, 1, 3));
        modelWeeks.add(new ModelWeek(9, 2, 2, 1, 3));
        modelWeeks.add(new ModelWeek(10, 2, 2, 1, 3));
        modelWeeks.add(new ModelWeek(11, 2, 2, 1, 3));
        modelWeeks.add(new ModelWeek(12, 2, 2, 1, 3));


        return  modelWeeks;
    }
}
