package com.quangminh.timeforlife.AdapterProject;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.quangminh.timeforlife.R;
import com.quangminh.timeforlife.model.Work_model;

import java.util.List;

public class Adaoter_work_progress extends RecyclerView.Adapter<Adaoter_work_progress.WorkProgressVH> {

    List<Work_model> work_modelList;
    Context context;

    public Adaoter_work_progress(List<Work_model> work_modelList, Context context) {
        this.work_modelList = work_modelList;
        this.context = context;
    }

    @NonNull
    @Override
    public WorkProgressVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_work_progress, parent, false);
        return new WorkProgressVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkProgressVH holder, int position) {

        Work_model workModel = work_modelList.get(position);

        if(workModel.getWorkProgress()==1){
            holder.workProgress.setText(workModel.getNote());
        }else {
            holder.workProgress.setVisibility(View.GONE);
        }


        if(workModel.getTypeNote()==4 ){

            Drawable drawable1 = context.getResources().getDrawable(R.drawable.ic_ellipse_91, null);
            holder.workProgress.setCompoundDrawablesWithIntrinsicBounds(drawable1,null,null,null);
        }else if(workModel.getTypeNote()==1){
            Drawable drawable1 = context.getResources().getDrawable(R.drawable.ic_ellipse_71, null);
            holder.workProgress.setCompoundDrawablesWithIntrinsicBounds(drawable1,null,null,null);
        }
        else if(workModel.getTypeNote()==3 ){
            Drawable drawable1 = context.getResources().getDrawable(R.drawable.ic_ellipse_61, null);
            holder.workProgress.setCompoundDrawablesWithIntrinsicBounds(drawable1,null,null,null);
        }else if(workModel.getTypeNote()==2){
            Drawable drawable1 = context.getResources().getDrawable(R.drawable.ic_ellipse_51, null);
            holder.workProgress.setCompoundDrawablesWithIntrinsicBounds(drawable1,null,null,null);
        }
    }

    @Override
    public int getItemCount() {
        if(work_modelList!=null){
            return work_modelList.size();
        }
        return 0;
    }

    public class WorkProgressVH extends RecyclerView.ViewHolder{

        TextView workProgress, titleProgress;
        CardView cardViewPr;
        public WorkProgressVH(@NonNull View itemView) {
            super(itemView);
            workProgress = itemView.findViewById(R.id.workkk);
            titleProgress = itemView.findViewById(R.id.title1);
            cardViewPr = itemView.findViewById(R.id.cardViewPr);
            cardViewPr.setBackground(null);
        }
    }
}
