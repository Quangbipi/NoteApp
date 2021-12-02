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

public class Adaoter_work_complete extends RecyclerView.Adapter<Adaoter_work_complete.WorkCompleteVH> {

    List<Work_model> work_modelList;
    Context context;

    public Adaoter_work_complete(List<Work_model> work_modelList, Context context) {
        this.work_modelList = work_modelList;
        this.context = context;
    }

    @NonNull
    @Override
    public WorkCompleteVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_work_progress, parent, false);
        return new WorkCompleteVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkCompleteVH holder, int position) {

        Work_model workModel = work_modelList.get(position);
        int n = workModel.getWorkProgress();

        if(n==0){
            holder.workComplete.setText(workModel.getNote()+ workModel.getWorkProgress());
        }else {
            holder.workComplete.setVisibility(View.GONE);
        }


        if(workModel.getTypeNote()==4 ){

            Drawable drawable1 = context.getResources().getDrawable(R.drawable.ic_ellipse_91, null);
            holder.workComplete.setCompoundDrawablesWithIntrinsicBounds(drawable1,null,null,null);
        }else if(workModel.getTypeNote()==1){
            Drawable drawable1 = context.getResources().getDrawable(R.drawable.ic_ellipse_71, null);
            holder.workComplete.setCompoundDrawablesWithIntrinsicBounds(drawable1,null,null,null);
        }
        else if(workModel.getTypeNote()==3 ){
            Drawable drawable1 = context.getResources().getDrawable(R.drawable.ic_ellipse_61, null);
            holder.workComplete.setCompoundDrawablesWithIntrinsicBounds(drawable1,null,null,null);
        }else if(workModel.getTypeNote()==2){
            Drawable drawable1 = context.getResources().getDrawable(R.drawable.ic_ellipse_51, null);
            holder.workComplete.setCompoundDrawablesWithIntrinsicBounds(drawable1,null,null,null);
        }
    }

    @Override
    public int getItemCount() {
        if(work_modelList!=null){
            return work_modelList.size();
        }
        return 0;
    }

    public class WorkCompleteVH extends RecyclerView.ViewHolder{

        TextView workComplete;
        CardView cardViewPr;
        public WorkCompleteVH(@NonNull View itemView) {
            super(itemView);
            workComplete = itemView.findViewById(R.id.workkk);
            cardViewPr = itemView.findViewById(R.id.cardViewPr);
            cardViewPr.setBackground(null);
        }
    }
}
