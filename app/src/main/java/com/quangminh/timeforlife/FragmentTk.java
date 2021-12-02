package com.quangminh.timeforlife;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.quangminh.timeforlife.ViewPager.ViewPagerAdapter;
import com.quangminh.timeforlife.ViewPager.ViewPagerAdapterTk;


public class FragmentTk extends Fragment implements View.OnClickListener {


    ViewPager2 viewPagerStaticstical;
    Button Day, Month, Year;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_thong_ke, container, false);

        anhXa(view);
        setUpViewPager();
        Day.setOnClickListener(this);
        Month.setOnClickListener(this);
        Year.setOnClickListener(this);
        return  view;
    }

    private void anhXa(View view) {
        viewPagerStaticstical = view.findViewById(R.id.view_pager2);
        Day = view.findViewById(R.id.bt_day);
        Month = view.findViewById(R.id.bt_month);
        Year = view.findViewById(R.id.bt_year);
    }


    private void setUpViewPager() {

        ViewPagerAdapterTk viewPagerAdapterTk = new ViewPagerAdapterTk(getActivity());
        viewPagerStaticstical.setAdapter(viewPagerAdapterTk);
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.bt_day:
                viewPagerStaticstical.setCurrentItem(0);
                break;
            case R.id.bt_month:
                viewPagerStaticstical.setCurrentItem(1);
                break;
            case R.id.bt_year:
                viewPagerStaticstical.setCurrentItem(2);
                break;
        }

    }
}
