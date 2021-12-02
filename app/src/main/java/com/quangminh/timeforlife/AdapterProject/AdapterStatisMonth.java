package com.quangminh.timeforlife.AdapterProject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.quangminh.timeforlife.R;
import com.quangminh.timeforlife.model.ModelWeek;

import java.util.List;

public class AdapterStatisMonth extends RecyclerView.Adapter<AdapterStatisMonth.StatisWeekVH>{

    List<ModelWeek> modelWeekList;
    Context context;

    public AdapterStatisMonth(List<ModelWeek> modelWeekList, Context context) {
        this.modelWeekList = modelWeekList;
        this.context = context;
    }

    @NonNull
    @Override
    public StatisWeekVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.statistical_year, parent, false);

        return new StatisWeekVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StatisWeekVH holder, int position) {

        ModelWeek  modelWeek = modelWeekList.get(position);

        holder.titleMonth.setText("Tháng " + modelWeek.getWeek());
        holder.tv_total2.setText("Công việc: " + modelWeek.getTotal_cv());
        holder.tv_failed2.setText("Thất bại: " + modelWeek.getFailed());
        holder.tv_complete2.setText("Hoàn thành: " + modelWeek.getComplete());
        holder.tv_score2.setText("Điểm: " + modelWeek.getScore());

    }

    @Override
    public int getItemCount() {
        if(modelWeekList!=null){
            return modelWeekList.size();
        }
        return 0;
    }

    public class StatisWeekVH extends RecyclerView.ViewHolder{

        TextView titleMonth, tv_score2, tv_complete2, tv_failed2, tv_total2;
        public StatisWeekVH(@NonNull View itemView) {
            super(itemView);
            titleMonth = itemView.findViewById(R.id.tv_month);
            tv_score2 = itemView.findViewById(R.id.tv_score2);
            tv_complete2 = itemView.findViewById(R.id.tv_complete2);
            tv_failed2 = itemView.findViewById(R.id.tv_failed2);
            tv_total2 = itemView.findViewById(R.id.tv_total2);
        }
    }
}
