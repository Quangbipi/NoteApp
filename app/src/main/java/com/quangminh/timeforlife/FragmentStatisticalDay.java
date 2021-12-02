package com.quangminh.timeforlife;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.quangminh.timeforlife.AdapterProject.Adaoter_work_complete;
import com.quangminh.timeforlife.AdapterProject.Adaoter_work_progress;
import com.quangminh.timeforlife.model.Work_model;

import java.util.ArrayList;
import java.util.List;

import static com.github.mikephil.charting.utils.ColorTemplate.rgb;

public class FragmentStatisticalDay extends Fragment implements View.OnClickListener {

    PieChart pieChart;
    ImageView infor;
    FrameLayout infor_table;
    RecyclerView progressRcw, completeRcw;
    List<Work_model> work_modelList;
    Adaoter_work_progress adapterWorkProgress;
    Adaoter_work_complete adapterWorkComplete;
    TextView titleProgress, titleComplete, total_score;


    int count=0;

    public static final int[] VORDIPLOM_COLORS_S = {
            Color.rgb(215, 236, 252), Color.rgb(217, 215, 252), Color.rgb(251, 215, 252),
            Color.rgb(252, 215, 226), Color.rgb(196, 193, 193)
    };

    public static final int[] MATERIAL_COLORS_S = {
            rgb("#D7ECFC"), rgb("#D9D7FC"), rgb("#FBD7FC"),  rgb("#FCD7E2"), rgb("#C4C1C1")
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_staticstical_day, container, false);

        anhXa(view);
        work_modelList = new ArrayList<>();

        setUpPieChart();
        loadChartData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        progressRcw.setLayoutManager(linearLayoutManager);
        completeRcw.setLayoutManager(linearLayoutManager2);

        work_modelList = getWork();
        titleShow(countWorkProgress(work_modelList));
        infor.setOnClickListener(this);
        return  view;
    }


    @Override
    public void onStart() {
        super.onStart();
        adapterWorkProgress = new Adaoter_work_progress(work_modelList, getContext());
        progressRcw.setAdapter(adapterWorkProgress);

        adapterWorkComplete = new Adaoter_work_complete(work_modelList, getContext());
        completeRcw.setAdapter(adapterWorkComplete);
    }

    //Pie chart
    private void setUpPieChart() {
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.getDescription().setEnabled(false);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(false);

    }

    private void loadChartData() {
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(0.1f, ""));
        entries.add(new PieEntry(0.15f, ""));
        entries.add(new PieEntry(0.08f, ""));
        entries.add(new PieEntry(0.25f, ""));
        entries.add(new PieEntry(0.42f, ""));

        ArrayList<Integer> colors = new ArrayList<>();
        for(int color: MATERIAL_COLORS_S){
            colors.add(color);
        }


        for(int color: VORDIPLOM_COLORS_S){
            colors.add(color);
        }

        PieDataSet pieDataSet = new PieDataSet(entries, "");
        pieDataSet.setColors(colors);

        PieData pieData = new PieData(pieDataSet);
        pieData.setDrawValues(true);
        pieData.setValueFormatter(new PercentFormatter(pieChart));
        pieData.setValueTextSize(12f);
        pieData.setValueTextColor(Color.BLACK);

        pieChart.setData(pieData);
        pieChart.invalidate();
    }

    //anh xa
    private void anhXa(View view){
        pieChart = view.findViewById(R.id.pie_chart);
        infor = view.findViewById(R.id.infor_1);
        infor_table = view.findViewById(R.id.infofor_table);
        progressRcw = view.findViewById(R.id.rcw_progress);
        titleComplete = view.findViewById(R.id.title2);
        titleProgress = view.findViewById(R.id.title1);
        completeRcw = view.findViewById(R.id.rcw_complete);
        total_score = view.findViewById(R.id.total_score);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.infor_1:

                if(count==0){
                    infor_table.setVisibility(View.VISIBLE);
                    count=1;
                }else if(count==1){
                    infor_table.setVisibility(View.INVISIBLE);
                    count=0;
                }

        }
    }

    public void titleShow(int n){
        titleProgress.setText("Đang thực hiên: " + n +"/" + work_modelList.size());
        int x = work_modelList.size() - n;
        titleComplete.setText("Đã hoàn thành: " +  x + "/" + work_modelList.size());
    }
    public ArrayList<Work_model> getWork(){
        ArrayList<Work_model> models = new ArrayList<>();
        models.add(new Work_model("Thức dậy", 2, 1));
        models.add(new Work_model("Học bài", 1, 0));
        models.add(new Work_model("Họp team mobile1", 3, 1));
        models.add(new Work_model("Họp team mobile2", 4, 0));
        models.add(new Work_model("Họp team mobile3", 5, 0));
        models.add(new Work_model("Họp team mobile4", 1, 1));
        return  models;
    }


    public int countWorkProgress(List<Work_model> work_models){
        int count1=0;
        for(int i=0; i<work_modelList.size(); i++){
            if(work_modelList.get(i).getWorkProgress()==1){
                count1++;
            }
        }
        return count1;
    }

}
