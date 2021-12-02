package com.quangminh.timeforlife.ViewPager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.quangminh.timeforlife.FragmentNotification;
import com.quangminh.timeforlife.FragmentProject;
import com.quangminh.timeforlife.FragmentSetting;
import com.quangminh.timeforlife.FragmentTk;

public class ViewPagerAdapter  extends FragmentStateAdapter {


    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new FragmentProject();
            case 1:
                return new FragmentTk();
            case 2:
                return new FragmentNotification();
            case 3:
                return new FragmentSetting();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
