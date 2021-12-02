package com.quangminh.timeforlife.ViewPager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.quangminh.timeforlife.FragmentStatisticalDay;
import com.quangminh.timeforlife.FragmentStatisticalMonth;
import com.quangminh.timeforlife.FragmentStatisticalYear;

public class ViewPagerAdapterTk extends FragmentStateAdapter {


    public ViewPagerAdapterTk(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new FragmentStatisticalDay();
            case 1:
                return new FragmentStatisticalMonth();
            case 2:
                return new FragmentStatisticalYear();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
