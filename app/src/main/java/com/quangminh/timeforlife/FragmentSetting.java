package com.quangminh.timeforlife;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

public class FragmentSetting extends Fragment implements View.OnClickListener {

    ImageButton expand1, expand2, expand3;
    ConstraintLayout viewEx1, viewEx2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        anhXa(view);

        expand1.setOnClickListener(this);
        expand2.setOnClickListener(this);
        expand3.setOnClickListener(this);
        return  view;
    }

    private void anhXa(View view) {
        expand1 = view.findViewById(R.id.expand_1);
        expand2 = view.findViewById(R.id.expand_2);
        expand3 = view.findViewById(R.id.expand_3);
        viewEx1 = view.findViewById(R.id.contr_2);
        viewEx2 = view.findViewById(R.id.contr_f2);
    }

    @Override
    public void onClick(View v) {
        int id= v.getId();
        switch (id){
            case R.id.expand_1:
                if(viewEx1.getVisibility()==View.GONE){
                    viewEx1.setVisibility(View.VISIBLE);
                    expand1.setRotation(90);
                }else if(viewEx1.getVisibility()==View.VISIBLE){
                    viewEx1.setVisibility(View.GONE);
                    expand1.setRotation(0);
                }
                break;

            case R.id.expand_2:
                if(viewEx2.getVisibility()==View.GONE){
                    viewEx2.setVisibility(View.VISIBLE);
                    expand2.setRotation(90);
                }else if(viewEx2.getVisibility()==View.VISIBLE){
                    viewEx2.setVisibility(View.GONE);
                    expand2.setRotation(0);
                }
                break;
            case R.id.expand_3:
                break;
        }
    }
}
