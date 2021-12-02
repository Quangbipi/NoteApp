package com.quangminh.timeforlife;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.quangminh.timeforlife.AdapterProject.Adapter_Date;
import com.quangminh.timeforlife.AdapterProject.Adapter_project;
import com.quangminh.timeforlife.FireBaseManager.FireBaseManager;
import com.quangminh.timeforlife.model.Schedule;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FragmentProject extends Fragment implements View.OnClickListener, Adapter_Date.AdapterCallback {

    BottomNavigationView bottomNavigationView;
    ImageButton add_btn;

    Calendar cal;
    TextView date;
    List<String> dateList;
    List<Integer> weekDayList;
    List<String> listProject;

    MainActivity mainActivity;
    Adapter_project adapter_project;
    Adapter_Date adapter_date;
    RecyclerView recyclerView, workRecycle;

    LinearLayoutManager linearLayoutManager, linearLayoutManager2;

    int monthBook, yearBook, dayBook;

    List<Schedule> mListSchedile;
    DatePickerDialog.OnDateSetListener dateSetListener;
    FireBaseManager fireBaseManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_project, container, false);


        mainActivity = (MainActivity) getActivity();


        anhxa(view);


        mListSchedile = new ArrayList<>();
        dateList = new ArrayList<>();
        weekDayList = new ArrayList<>();
        listProject = new ArrayList<>();

        cal = Calendar.getInstance();

        getDateOfMonth(cal.get(Calendar.MONTH));
        linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        linearLayoutManager2 = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        workRecycle.setLayoutManager(linearLayoutManager2);

        addProject();

        int month = cal.get(Calendar.MONTH)+1;
        if(month<10){
            date.setText("Tháng 0" + month +", " + cal.get(Calendar.YEAR));
        }else {
            date.setText("Tháng " + month +", " + cal.get(Calendar.YEAR));
        }

        date.setOnClickListener(this);
        add_btn.setOnClickListener(this);

        getData();
        Toast.makeText(mainActivity.getApplicationContext(),mListSchedile.toString(),Toast.LENGTH_SHORT).show();
        return view;
    }

    //anh xa view
    private void anhxa(View view) {
        date = view.findViewById(R.id.textView11);
        recyclerView = view.findViewById(R.id.recyclerView);

        workRecycle = view.findViewById(R.id.work_bgk11);
        add_btn = view.findViewById(R.id.add_project);

    }

    @Override
    public void onResume() {
        super.onResume();
        adapter_date = new Adapter_Date(dateList, getContext(), weekDayList );
        recyclerView.setAdapter(adapter_date);
        adapter_project = new Adapter_project(listProject, getContext());
        workRecycle.setAdapter(adapter_project);
        cal = Calendar.getInstance();
        scrollItem(cal.get(Calendar.DATE));
        getData();
        Toast.makeText(mainActivity.getApplicationContext(),mListSchedile.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    // get Date and add to array
    private void getDateOfMonth(int month) {
        dateList.clear();
        weekDayList.clear();
        cal = Calendar.getInstance();
        //set thang
        cal.set(Calendar.MONTH, month);
        //?
        cal.set(Calendar.DAY_OF_MONTH, month);
        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //format cal
        SimpleDateFormat df = new SimpleDateFormat("dd");
        // add date to array
        for (int i = 0; i < maxDay; i++) {
            cal.set(Calendar.DAY_OF_MONTH, i + 1);
            Log.d("date", "," + cal.get(Calendar.DAY_OF_WEEK));
            dateList.add(df.format(cal.getTime()));
            weekDayList.add(cal.get(Calendar.DAY_OF_WEEK));
        }
    }

    // show dialogPicker
    private void showDialogPicker() {

        cal = Calendar.getInstance();
        final int year= cal.get(Calendar.YEAR);
        final int month = cal.get(Calendar.MONTH);
        final int day = cal.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;

                if(month<10){
                    date.setText("Tháng 0" + month+", "+year);
                }else {
                    date.setText("Tháng " + month+", "+year);
                }


                getDateOfMonth(month-1);
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    //test rcw
    private void addProject(){
        listProject.add("họp team mobie 1");
        listProject.add("họp team mobie 2");
        listProject.add("họp team mobie 3");
        listProject.add("họp team mobie 4");
        listProject.add("họp team mobie 5");
        listProject.add("họp team mobie 6");
        listProject.add("họp team mobie 7");
        listProject.add("họp team mobie 8");
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.textView11:
                showDialogPicker();
                break;
            case R.id.add_project:
                startActivityAdd();
                break;

        }
    }

    private void startActivityAdd() {
        Intent i = new Intent(getContext(), AddProject.class);
        i.putExtra("ID", mainActivity.sendID());
        startActivity(i);
    }

    @Override
    public void onMethodCallback(String date) {

        dayBook=Integer.parseInt(date);
    }

    private void scrollItem(int index){
        if(linearLayoutManager==null){
            return;
        }
        cal = Calendar.getInstance();
        linearLayoutManager.scrollToPositionWithOffset(index-4,0);
    }

    private void getData(){
        monthBook= Integer.parseInt(date.getText().toString().trim().substring(6,8));
        yearBook = Integer.parseInt(date.getText().toString().trim().substring(10,14));

        fireBaseManager = new FireBaseManager();
        fireBaseManager.getDatabase(yearBook, monthBook, dayBook, mainActivity.sendID(), mListSchedile);

    }
}
