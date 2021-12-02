package com.quangminh.timeforlife;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.AdapterListUpdateCallback;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
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
import com.quangminh.timeforlife.FireBaseManager.FireBaseManager;
import com.quangminh.timeforlife.Interface.SetBMonth;
import com.quangminh.timeforlife.model.Announce;
import com.quangminh.timeforlife.model.Schedule;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddProject extends AppCompatActivity implements View.OnClickListener, Adapter_Date.AdapterCallback{

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

    Announce announce;
    Schedule schedule;

    Boolean a=true, n=true ,k=true, a1 = true,n1=true ,k1=true ;

    int check = 0, x=0, y=0, type, beforeMinute=5,  monthBook, yearBook, dayBook,id=1, monthBook1, yearBook1, dayBook1, monthBook2=0, yearBook2=0, dayBook2=0;

    FireBaseManager fireBaseManager;

    private SetBMonth setBMonth;

    LinearLayoutManager linearLayoutManager;

    String ID;
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
        //định hướng rcw
        linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        rcw_date.setLayoutManager(linearLayoutManager);

       adapterSpiner();

       setClick();

        Intent intent = getIntent();
        ID=intent.getStringExtra("ID");
        Toast.makeText(this, ID, Toast.LENGTH_SHORT).show();


        cal = Calendar.getInstance();
        int mothh= cal.get(Calendar.MONTH)+1;
        if(mothh<10){
            time_now.setText("Tháng 0" +mothh+", " + cal.get(Calendar.YEAR));
        }else{
            time_now.setText("Tháng " +mothh+", " + cal.get(Calendar.YEAR));
        }


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

        save.setOnClickListener(this);
    }
    @Override
    protected void onStart() {
        super.onStart();
        getThongtin();
        adapter_date = new Adapter_Date(dateList, AddProject.this, weekDayList, AddProject.this);
        rcw_date.setAdapter(adapter_date);


    }

    @Override
    public void onResume() {
        super.onResume();

        cycle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                time_now.setVisibility(View.VISIBLE);
                String fff = String.valueOf(id);
                Toast.makeText(getApplicationContext(), fff, Toast.LENGTH_SHORT).show();



                if(id==0){
                    adapter_date = new Adapter_Date(dateList, AddProject.this, weekDayList, AddProject.this);
                    rcw_date.setAdapter(adapter_date);
                    cal = Calendar.getInstance();
                    scrollItem(cal.get(Calendar.DATE));
                }else if(id==1){
                    adapter_date = new Adapter_Date(date0fWeekList, AddProject.this, weekDayList, AddProject.this);
                    time_now.setVisibility(View.INVISIBLE);
                    rcw_date.setAdapter(adapter_date);
                    Toast.makeText(getApplicationContext(), "aaa1",Toast.LENGTH_SHORT).show();
                } else if (id==2) {
                    adapter_date = new Adapter_Date(date0fWeekList, AddProject.this, weekDayList,AddProject.this);
                    time_now.setVisibility(View.INVISIBLE);
                    rcw_date.setAdapter(adapter_date);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                Toast.makeText(getApplicationContext(), "aaa",Toast.LENGTH_SHORT).show();
            }
        });
        before_minute.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(id==0){
                    beforeMinute=5;
                }else if (id==1){
                    beforeMinute=10;
                }else if (id==2){
                    beforeMinute=15;
                }else if (id==3){
                    beforeMinute=20;
                }else if (id==4){
                    beforeMinute=25;
                }else if (id==5){
                    beforeMinute=30;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




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
                if(month<10){
                    time_now.setText("Tháng 0"+month+", " + year);
                }else {
                    time_now.setText("Tháng "+month+", " + year);
                }


//                monthBook= month;
//                yearBook=year;
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

    //add thứ
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
                if(a1==true){
                    a=true;
                    a1=false;
                    Toast.makeText(this, "Bật báo thức", Toast.LENGTH_SHORT).show();
                    alarmTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_notifi_2,0,0,0);
                }else if(a1==false){
                    a=false;
                    a1=true;
                    alarmTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_alarm_bl,0,0,0);
                    Toast.makeText(this, "Tắt báo thức", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.notifi_tv:
                if(n1==false){
                    n=false;
                    n1=true;
                    notifiTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_notifi_bl,0,0,0);
                    Toast.makeText(this, "Tắt notifi", Toast.LENGTH_SHORT).show();
                }else if(n1==true){
                    n=true;
                    n1=false;
                    Toast.makeText(this, "Bật notifi", Toast.LENGTH_SHORT).show();
                    notifiTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_notifi,0,0,0);
                }

                break;
            case R.id.kpi_tv:
                if(k1==true){
                    k=true;
                    k1=false;
                    kpiTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.kpi_ic,0,0,0);
                    Toast.makeText(this, "Bật kpi", Toast.LENGTH_SHORT).show();
                }else if (k1==false){
                    k=false;
                    k1=true;
                    Toast.makeText(this, "Tắt kpi", Toast.LENGTH_SHORT).show();
                    kpiTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.kpi_bl,0,0,0);
                }

                break;

            case R.id.quan_trong:
                x=0;
                kQTrong.setBackground(getDrawable(R.drawable.custom_bgk_priority_3));
                qTrong.setBackground(getDrawable(R.drawable.custom_bgk_priority_1));
                break;
            case R.id.kquan_trong:
                x=1;
                qTrong.setBackground(getDrawable(R.drawable.custom_bgk_priority_3));
                kQTrong.setBackground(getDrawable(R.drawable.custom_bgk_priority_4));
                break;
            case R.id.khan_cap:
                y=2;
                kKcap.setBackground(getDrawable(R.drawable.custom_bgk_priority_3));
                kCap.setBackground(getDrawable(R.drawable.custom_bgk_priority_5));
                break;
            case R.id.kkhan_cap:
                y=3;
                kCap.setBackground(getDrawable(R.drawable.custom_bgk_priority_3));
                kKcap.setBackground(getDrawable(R.drawable.custom_bgk_priority_2));
                break;
            case R.id.btn_save:
                getThongtin();

                //Toast.makeText(this, Path, Toast.LENGTH_SHORT).show();
                fireBaseManager= new FireBaseManager();
                fireBaseManager.onClickPushData(schedule, AddProject.this,  ID);

                dayBook2=schedule.getBookingDate();
                monthBook2=schedule.getBookingMonth();
                yearBook2=schedule.getBookingYear();
                break;
        }
    }

//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        time_now.setVisibility(View.VISIBLE);
//        String fff = String.valueOf(id);
//        Toast.makeText(this, fff, Toast.LENGTH_SHORT).show();
//
//
//
//        if(id==0){
//            adapter_date = new Adapter_Date(dateList, AddProject.this, weekDayList);
//            rcw_date.setAdapter(adapter_date);
//        }else if(id==1){
//            adapter_date = new Adapter_Date(date0fWeekList, AddProject.this, weekDayList);
//            time_now.setVisibility(View.INVISIBLE);
//            rcw_date.setAdapter(adapter_date);
//            Toast.makeText(this, "aaa1",Toast.LENGTH_SHORT).show();
//        } else if (id==2) {
//            adapter_date = new Adapter_Date(date0fWeekList, AddProject.this, weekDayList);
//            time_now.setVisibility(View.INVISIBLE);
//            rcw_date.setAdapter(adapter_date);
//        }
//    }

    private void setData(){
        String misstion = note.getText().toString().trim();


    }
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//        Toast.makeText(this, "aaa",Toast.LENGTH_SHORT).show();
//    }

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

    private void getThongtin(){
        String noiDung = note.getText().toString().trim();
        String startTime = timeStart.getText().toString().trim();
        String endTime = timeEnd.getText().toString().trim();

        monthBook= Integer.parseInt(time_now.getText().toString().trim().substring(6,8));
        yearBook = Integer.parseInt(time_now.getText().toString().trim().substring(10,14));
        setAnnounce();


        if(x+y==0){
            Toast.makeText(this, "Vui lòng chọn mức độ", Toast.LENGTH_SHORT).show();
        }else if(x+y==2){
            type=TYPE_QTKC;
        }else if(x+y==3&&x==0){
            type=TYPE_QTKKC;
        }else if(x+y==3&&x==1){
            type=TYPE_KQTKC;
        }else if(x+y==4){
            type=TYPE_KQTKKC;
        }
        Toast.makeText(this, beforeMinute + "", Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, startTime + " " + endTime + "type" + type + " " + noiDung + n + beforeMinute, Toast.LENGTH_SHORT).show();
        //
        //check cùng 1 ngày có nhiều nvu cần làm
        if(dayBook2==0){

            schedule = new Schedule(dayBook, monthBook, yearBook, noiDung, startTime, endTime, type, announce, id );

        }else if(dayBook2!=0){
            if(monthBook==monthBook2 && yearBook==yearBook2){
                if(dayBook==dayBook2){
                    id++;
                    schedule = new Schedule(dayBook, monthBook, yearBook, noiDung, startTime, endTime, type, announce, id );
                }else if(dayBook != dayBook2){
                    id=1;
                    schedule = new Schedule(dayBook, monthBook, yearBook, noiDung, startTime, endTime, type, announce, id );
                }
            }
        }


    }

    @Override
    public void onMethodCallback(String date) {

        dayBook=Integer.parseInt(date);

    }



   public void setAnnounce(){
       announce = new Announce(beforeMinute, n,a,k);
   }



   private void scrollItem(int index){
        if(linearLayoutManager==null){
            return;
        }
        cal = Calendar.getInstance();
        linearLayoutManager.scrollToPositionWithOffset(index-4,0);
   }


}