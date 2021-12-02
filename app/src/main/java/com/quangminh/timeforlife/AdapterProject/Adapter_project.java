package com.quangminh.timeforlife.AdapterProject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.quangminh.timeforlife.R;

import java.util.List;

public class Adapter_project extends RecyclerView.Adapter<Adapter_project.ProjectVH>{

    List<String> workList;
    Context context;

    public Adapter_project(List<String> workList, Context context) {
        this.workList = workList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProjectVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_project, parent, false);

        return new ProjectVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectVH holder, int position) {

        String work1 = workList.get(position);
        holder.project.setText(work1);

        holder.constraintLayout.setBackground(null);
    }

    @Override
    public int getItemCount() {
        if(workList!=null){
            return workList.size();
        }
        return 0;
    }

    public class ProjectVH extends RecyclerView.ViewHolder{

        RelativeLayout bgkProject;
        TextView timer, project;

        CardView cardView;
        ConstraintLayout constraintLayout;
        public ProjectVH(@NonNull View itemView) {
            super(itemView);
            bgkProject = itemView.findViewById(R.id.bgk_project);
            timer  =itemView.findViewById(R.id.timer);
            project = itemView.findViewById(R.id.work);
            constraintLayout = itemView.findViewById(R.id.constraintLayout);
            cardView = itemView.findViewById(R.id.cardView);
            cardView.setBackground(null);
            
        }
    }
}
