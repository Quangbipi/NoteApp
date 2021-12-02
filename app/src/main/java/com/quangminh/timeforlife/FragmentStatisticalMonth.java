package com.quangminh.timeforlife;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.quangminh.timeforlife.AdapterProject.AdapterStatisWeek;
import com.quangminh.timeforlife.model.ModelWeek;
import com.quangminh.timeforlife.model.Work_model;

import java.util.ArrayList;
import java.util.List;

public class FragmentStatisticalMonth extends Fragment {

    LineChart lineChart;
    RecyclerView statisticalWeek;
    AdapterStatisWeek adapterStatisWeek;
    List<ModelWeek> weekList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_statistical_month, container, false);
        anhXa(view);

        setUpChart();
        setChart();

        weekList = new ArrayList<>();
        weekList = getWorkWeek();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        statisticalWeek.setLayoutManager(linearLayoutManager);
        return view;
    }

    private void anhXa(View view) {
        lineChart = view.findViewById(R.id.line_chart);
        statisticalWeek = view.findViewById(R.id.statis_month);

    }

    @Override
    public void onStart() {
        super.onStart();
        adapterStatisWeek = new AdapterStatisWeek(weekList, getContext());
        statisticalWeek.setAdapter(adapterStatisWeek);
    }

    private void setUpChart(){
        LineDataSet lineDataSet = new LineDataSet(DataVl(),"");
        lineDataSet.setLineWidth(2f); // set độ dày line
        lineDataSet.setCircleRadius(0f); //set độ dày chấm
        lineDataSet.setCircleHoleRadius(0.01f);// set chấm
        lineDataSet.setCircleColor(Color.WHITE);
        lineDataSet.setHighlightEnabled(false);
        lineDataSet.setColor(getResources().getColor(R.color.color_main, null)); // set màu line
        lineDataSet.setHighLightColor(Color.BLACK); //set màu đường highlight
        lineDataSet.setDrawValues(false); //set chỉ số ở chấm
        lineDataSet.setDrawCircles(false);//set chấm

        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);//set mode
        lineDataSet.setCubicIntensity(0.2f);// độ smooth
        lineDataSet.setDrawFilled(true);// màu ở dưới line
        lineDataSet.setFillColor(getResources().getColor(R.color.fill_color, null));// set màu ở dưới line
        lineDataSet.setFillAlpha(20);// set độ mờ



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
        return dataVal;
    }

    private void setChart(){
        //set đường trái
        lineChart.getAxisLeft().setDrawGridLines(false);// line ngang
        lineChart.getAxisLeft().setDrawLabels(false);//set chỉ số trái
        lineChart.getAxisLeft().setDrawAxisLine(false);//cột trái cuối

        //set đường dưới
        lineChart.getXAxis().setDrawGridLines(true);// line dọc
        lineChart.getXAxis().setDrawLabels(true);//chỉ số ngang
        lineChart.getXAxis().setDrawAxisLine(true);// line ngang cuối
        lineChart.getXAxis().setAxisLineColor(getResources().getColor(R.color.color_main, null));
        lineChart.getXAxis().setAxisLineWidth(3f);
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChart.getXAxis().setDrawGridLinesBehindData(false);
        lineChart.getXAxis().setGridColor(Color.WHITE);
        lineChart.getXAxis().setGridLineWidth(0.5f);

        //set đường phải
        lineChart.getAxisRight().setDrawGridLines(false);//line ngang
        lineChart.getAxisRight().setDrawLabels(false);//chỉ số phải
        lineChart.getAxisRight().setDrawAxisLine(false);//line phải cuối

        lineChart.getDescription().setEnabled(false);
        lineChart.animateX(500, Easing.EaseInBounce);
        lineChart.getXAxis().setValueFormatter(new MyAxisValueFormatter());
        lineChart.getXAxis().setTextSize(10f);

    }

    private class MyAxisValueFormatter extends ValueFormatter {

        @Override
        public String getAxisLabel(float value, AxisBase axis) {
            axis.setLabelCount(5, true);
            return "Tuần " + (int)value;
        }
    }
    private ArrayList<ModelWeek> getWorkWeek(){
        ArrayList<ModelWeek> modelWeeks = new ArrayList<>();
        modelWeeks.add(new ModelWeek(1, 1, 1, 2, 3));
        modelWeeks.add(new ModelWeek(2, 2, 2, 2, 4));
        modelWeeks.add(new ModelWeek(3, 1, 1, 1, 2));
        modelWeeks.add(new ModelWeek(4, 4, 3, 2, 5));
        modelWeeks.add(new ModelWeek(5, 2, 2, 1, 3));
        return  modelWeeks;
    }

}
