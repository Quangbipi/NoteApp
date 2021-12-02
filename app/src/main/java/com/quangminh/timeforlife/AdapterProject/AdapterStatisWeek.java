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

public class AdapterStatisWeek extends RecyclerView.Adapter<AdapterStatisWeek.StatisWeekVH>{

    List<ModelWeek> modelWeekList;
    Context context;

    public AdapterStatisWeek(List<ModelWeek> modelWeekList, Context context) {
        this.modelWeekList = modelWeekList;
        this.context = context;
    }

    @NonNull
    @Override
    public StatisWeekVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.statistical_month, parent, false);

        return new StatisWeekVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StatisWeekVH holder, int position) {

        ModelWeek  modelWeek = modelWeekList.get(position);

        holder.titleWeek.setText("Tuần " + modelWeek.getWeek());
        holder.tv_total.setText("Công việc: " + modelWeek.getTotal_cv());
        holder.tv_failed.setText("Thất bại: " + modelWeek.getFailed());
        holder.tv_complete.setText("Hoàn thành: " + modelWeek.getComplete());
        holder.tv_score.setText("Điểm: " + modelWeek.getScore());

    }

    @Override
    public int getItemCount() {
        if(modelWeekList!=null){
            return modelWeekList.size();
        }
        return 0;
    }

    public class StatisWeekVH extends RecyclerView.ViewHolder{

        TextView titleWeek, tv_score, tv_complete, tv_failed, tv_total;
        public StatisWeekVH(@NonNull View itemView) {
            super(itemView);
            titleWeek = itemView.findViewById(R.id.tv_week);
            tv_score = itemView.findViewById(R.id.tv_score);
            tv_complete = itemView.findViewById(R.id.tv_complete);
            tv_failed = itemView.findViewById(R.id.tv_failed);
            tv_total = itemView.findViewById(R.id.tv_total);
        }
    }
}
