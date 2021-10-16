package com.quangminh.timeforlife;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.quangminh.timeforlife.AdapterProject.Adapter_Date;
import com.quangminh.timeforlife.model.Schedule;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddProject extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Spinner cycle, before_minute;
    RecyclerView rcw_date;
    Adapter_Date adapter_date;
    TextView time_now, timeStart, timeEnd, alarmTv, notifiTv, kpiTv, qTrong, kQTrong, kCap, kKcap;
    Button save;

    EditText note;
    Calendar cal;
    List<String> dateList;
    List<Integer> weekDayList;

    List<String> date0fWeekList;

    Boolean a=true, n=true ,k=true;

    int check = 0;
    public static final int TYPE_QTKC = 1;
    public static final int TYPE_QTKKC = 2;
    public static final int TYPE_KQTKC = 3;
    public static final int TYPE_KQTKKC = 4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);

        anhXa();

        cal = Calendar.getInstance();

        dateList = new ArrayList<>();
        weekDayList = new ArrayList<>();
        date0fWeekList = new ArrayList<>();

        date0fWeekList=getDate();
        getDateOfMonth(cal.get(Calendar.MONTH));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        rcw_date.setLayoutManager(linearLayoutManager);

       adapterSpiner();

       setClick();

    }

    private void setClick(){
        time_now.setOnClickListener(this);
        timeStart.setOnClickListener(this);
        timeEnd.setOnClickListener(this);

        alarmTv.setOnClickListener(this);
        notifiTv.setOnClickListener(this);
        kpiTv.setOnClickListener(this);

        qTrong.setOnClickListener(this);
        kQTrong.setOnClickListener(this);
        kKcap.setOnClickListener(this);
        kCap.setOnClickListener(this);
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter_date = new Adapter_Date(dateList, AddProject.this, weekDayList);
        rcw_date.setAdapter(adapter_date);

    }

    @Override
    public void onResume() {
        super.onResume();

        cycle.setOnItemSelectedListener(this);

    }
    // show dialogPicker
    private void showDialogPicker() {

        cal = Calendar.getInstance();
        final int year= cal.get(Calendar.YEAR);
        final int month = cal.get(Calendar.MONTH);
        final int day = cal.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String date1 = "Tháng " + month+", "+year;
                time_now.setText(date1);
                getDateOfMonth(month-1);
            }
        }, year, month, day);
        datePickerDialog.show();
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

    public ArrayList<String> getDate(){
        ArrayList<String> dateOfWeek1 = new ArrayList<>();
        dateOfWeek1.add("T2");
        dateOfWeek1.add("T3");
        dateOfWeek1.add("T4");
        dateOfWeek1.add("T5");
        dateOfWeek1.add("T6");
        dateOfWeek1.add("T7");
        dateOfWeek1.add("CN");
        return dateOfWeek1;
    }

    private void anhXa() {
        cycle  = findViewById(R.id.cycle_repetition);
        rcw_date = findViewById(R.id.recyclerView11);
        time_now = findViewById(R.id.textView22);
        save = findViewById(R.id.btn_save);
        before_minute = findViewById(R.id.spiner_minute);

        timeEnd = findViewById(R.id.time_end);
        timeStart = findViewById(R.id.time_start);

        alarmTv = findViewById(R.id.alarm_tv);
        notifiTv = findViewById(R.id.notifi_tv);
        kpiTv = findViewById(R.id.kpi_tv);

        kQTrong = findViewById(R.id.kquan_trong);
        qTrong = findViewById(R.id.quan_trong);
        kCap = findViewById(R.id.khan_cap);
        kKcap = findViewById(R.id.kkhan_cap);

        note = findViewById(R.id.edt_misstion);
    }

    //Spiner Manager
    private void adapterSpiner(){
        ArrayAdapter<CharSequence> adapter =  ArrayAdapter.createFromResource(this, R.array.spiner_cycle, R.layout.coloe_spiner);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        cycle.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.spiner_minute, R.layout.support_simple_spinner_dropdown_item);
        before_minute.setAdapter(adapter1);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.textView22:
                showDialogPicker();
                break;
            case R.id.time_end:
                chonGio(timeEnd);
                break;
            case R.id.time_start:
                chonGio(timeStart);
                break;
            case R.id.alarm_tv:
                if(alarmTv.isClickable()){
                    a=true;
                }
                Toast.makeText(this, "Bật báo thức", Toast.LENGTH_SHORT).show();
                break;
            case R.id.notifi_tv:
                if(notifiTv.isClickable()){
                    n=true;

                }

                break;
            case R.id.kpi_tv:
                if(kpiTv.isClickable()){
                    k=true;
                    Toast.makeText(this, "Bật báo thức", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.quan_trong:
                kQTrong.setBackground(getDrawable(R.drawable.custom_bgk_priority_3));
                qTrong.setBackground(getDrawable(R.drawable.custom_bgk_priority_1));
                break;
            case R.id.kquan_trong:
                qTrong.setBackground(getDrawable(R.drawable.custom_bgk_priority_3));
                kQTrong.setBackground(getDrawable(R.drawable.custom_bgk_priority_4));
                break;
            case R.id.khan_cap:
                kKcap.setBackground(getDrawable(R.drawable.custom_bgk_priority_3));
                kCap.setBackground(getDrawable(R.drawable.custom_bgk_priority_5));
                break;
            case R.id.kkhan_cap:
                kCap.setBackground(getDrawable(R.drawable.custom_bgk_priority_3));
                kKcap.setBackground(getDrawable(R.drawable.custom_bgk_priority_2));
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        time_now.setVisibility(View.VISIBLE);
        String fff = String.valueOf(id);
        Toast.makeText(this, fff, Toast.LENGTH_SHORT).show();



        if(id==0){
            adapter_date = new Adapter_Date(dateList, AddProject.this, weekDayList);
            rcw_date.setAdapter(adapter_date);
        }else if(id==1){
            adapter_date = new Adapter_Date(date0fWeekList, AddProject.this, weekDayList);
            time_now.setVisibility(View.INVISIBLE);
            rcw_date.setAdapter(adapter_date);
            Toast.makeText(this, "aaa1",Toast.LENGTH_SHORT).show();
        } else if (id==2) {
            adapter_date = new Adapter_Date(date0fWeekList, AddProject.this, weekDayList);
            time_now.setVisibility(View.INVISIBLE);
            rcw_date.setAdapter(adapter_date);
        }
    }

    private void setData(){
        String misstion = note.getText().toString().trim();
        Schedule schedule = new Schedule();

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "aaa",Toast.LENGTH_SHORT).show();
    }

    //timepicker dialog
    private void chonGio(TextView time){

        Calendar cl = Calendar.getInstance();
        int gio = cl.get(Calendar.HOUR_OF_DAY);
        int phut = cl.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        time.setText(hourOfDay + ":" + minute);
                    }
                },gio,phut,true);

        timePickerDialog.show();
    }

}