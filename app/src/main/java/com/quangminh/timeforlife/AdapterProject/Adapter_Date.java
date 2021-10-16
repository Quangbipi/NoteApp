package com.quangminh.timeforlife.AdapterProject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.collection.LLRBNode;
import com.quangminh.timeforlife.Interface.DateOnClick;
import com.quangminh.timeforlife.R;

import java.util.Calendar;
import java.util.List;


public class Adapter_Date extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    List<String> dateList;
    Context context;
    List<Integer> weekDayList;

    Calendar cal;

    //set Data
    public Adapter_Date(List<String> dateList, Context context, List<Integer> weekDayList) {
        this.dateList = dateList;
        this.context = context;
        this.weekDayList = weekDayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       if(viewType ==0){
           View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layouut_week, parent, false);
           return new Date2VH(view);
       }else if(viewType == 1){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_date, parent, false);
            return new DateVH(view);
       }
       return null;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        String day1 = dateList.get(position);
        String weekday = String.valueOf(weekDayList.get(position));


        if(holder.getItemViewType() == 0){
            Date2VH date2VH = (Date2VH) holder;
            date2VH.day.setText(day1);
            if(day1.equals("T7")||day1.equals("CN")){
                date2VH.day.setTextColor(Color.parseColor("#EC0312"));
            }

        }else if(holder.getItemViewType() ==1){

            DateVH dateVH = (DateVH) holder;
            dateVH.dayOfMonth.setText(day1);
            if(weekday.equals("1")){
                weekday="CN";
            }
            if(weekday.equals("CN")){
                dateVH.weekDay.setText(weekday);
            }   else {
                dateVH.weekDay.setText("T" +weekday);
            }

            setDateNow(day1, dateVH);
            ((DateVH) holder).setDateOnClick(new DateOnClick() {

                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View view, int position, boolean isClick) {
                    if(isClick){

                        dateVH.cardDate.setBackground(ContextCompat.getDrawable(context, R.drawable.custom_button));
                        dateVH.weekDay.setTextColor(R.color.text_checked);
                        dateVH.dayOfMonth.setTextColor(R.color.text_checked);
                    }
                }
            });

        }
    }

    @SuppressLint("ResourceAsColor")
    private void setDateNow(String dateVH, DateVH vh) {
        cal = Calendar.getInstance();
        int dayNow = cal.get(Calendar.DATE);
        int monthNow = cal.get(Calendar.MONTH);
        int yearNow = cal.get(Calendar.YEAR);

        if(dayNow ==Integer.parseInt(dateVH)){
            vh.cardDate.setBackground(ContextCompat.getDrawable(context, R.drawable.custom_button));
            vh.weekDay.setTextColor(R.color.text_checked);
            vh.dayOfMonth.setTextColor(R.color.text_checked);
        }
    }

    @Override
    public int getItemViewType(int position) {

        if (dateList.size()>7) {
            return 1;
        }
        return 0;
    }

//    @Override
//    public void onBindViewHolder(@NonNull DateVH holder, int position) {
//
//
//        String day = dateList.get(position);
//        String weekday = String.valueOf(weekDayList.get(position));
//        holder.dayOfMonth.setText(day);
//        if(weekday.equals("1")){
//            weekday="CN";
//        }
//        if(weekday.equals("CN")){
//            holder.weekDay.setText(weekday);
//        }else {
//            holder.weekDay.setText("T" +weekday);
//        }
//
//    }

    @Override
    public int getItemCount() {
        if(dateList!=null){
            return dateList.size();
        }
        return 0;
    }

    public class DateVH extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView weekDay, dayOfMonth;
        CardView cardDate;
        LinearLayout layout;
        private DateOnClick dateOnClick;
        @SuppressLint("ResourceAsColor")
        public DateVH(@NonNull View itemView) {
            super(itemView);

            weekDay = itemView.findViewById(R.id.week_day);
            dayOfMonth = itemView.findViewById(R.id.day_of_month);
            cardDate = itemView.findViewById(R.id.cardDate);
            layout = itemView.findViewById(R.id.linearDate);
            cardDate.setCardBackgroundColor(R.color.cardColor);
            itemView.setOnClickListener(this);
        }

        public void setDateOnClick(DateOnClick dateOnClick){
            this.dateOnClick = dateOnClick;
        }

        @Override
        public void onClick(View v) {
            dateOnClick.onClick(v, getLayoutPosition(), true);

        }
    }

    public class Date2VH extends RecyclerView.ViewHolder{

        CardView dayOfWeek;
        TextView day;
        public Date2VH(@NonNull View itemView) {
            super(itemView);

            day = itemView.findViewById(R.id.day_crw);
            dayOfWeek = itemView.findViewById(R.id.day_of_month);

        }
    }

}
