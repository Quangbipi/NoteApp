package com.quangminh.timeforlife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.quangminh.timeforlife.AdapterProject.Adapter_Date;
import com.quangminh.timeforlife.ViewPager.ViewPagerAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    ViewPager2 viewPager2;
    String ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setBackground(null);


        Intent intent = getIntent();
        ID=intent.getStringExtra("ID");
        viewPager2 = findViewById(R.id.view_pager);

        sendID();
        setUpViewPager();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.home:
                        viewPager2.setCurrentItem(0);
                        break;
                    case R.id.thong_ke:
                        viewPager2.setCurrentItem(1);
                        break;
                    case R.id.notifi:
                        viewPager2.setCurrentItem(2);
                        break;
                    case R.id.setting:
                        viewPager2.setCurrentItem(3);
                        break;


                }
                return  true;
            }
        });



    }

    private void setUpViewPager() {

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager2.setAdapter(viewPagerAdapter);
    }


    public String sendID(){
        return ID;
    }
}