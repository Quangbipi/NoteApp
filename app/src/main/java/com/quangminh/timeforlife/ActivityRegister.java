package com.quangminh.timeforlife;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.quangminh.timeforlife.FireBaseManager.FireBaseManager;

public class ActivityRegister extends AppCompatActivity implements View.OnClickListener {

    EditText ReAccount, RePass, ReCheckPass;
    Button btRegister;
    ImageView eyeOn, eyeOn2;

    int check = 0;

    FirebaseAuth mAuth;

    String mEmail, mPass, mEnterPass, mId;

    FireBaseManager fireBaseManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        anhXa();

        btRegister.setOnClickListener(this);
        eyeOn.setOnClickListener(this);
        eyeOn2.setOnClickListener(this);
    }

    private void anhXa() {
        ReAccount = findViewById(R.id.ReTk);
        RePass = findViewById(R.id.ReMk);
        ReCheckPass = findViewById(R.id.ReXn);
        btRegister = findViewById(R.id.bt_register);
        eyeOn = findViewById(R.id.eye_check);
        eyeOn2 = findViewById(R.id.eye_check2);
    }


    private boolean registerUser() {
        mEmail = ReAccount.getText().toString().trim();
        mPass = RePass.getText().toString().trim();
        mEnterPass = ReCheckPass.getText().toString().trim();

        if(mEmail.isEmpty()){
            ReAccount.setError("Không được bỏ trống");
            ReAccount.requestFocus();
            return false;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(mEmail).matches()){
            ReAccount.setError("Email không hợp lệ");
            ReAccount.requestFocus();
            return false;
        }
        if(mPass.isEmpty()){
            RePass.setError("Không được bỏ trống");
            RePass.requestFocus();
            return false;
        }
        if(mPass.length()<6){
            RePass.setError("Mật khẩu phải lớn hơn 6");
            RePass.requestFocus();
            return false;
        }
        if(mEnterPass.isEmpty()){
            ReCheckPass.setError("Không được bỏ trống");
            ReCheckPass.requestFocus();
            return false;
        }
        if(!mEnterPass.equals(mPass)){
            ReCheckPass.setError("Không hợp lệ");
            ReCheckPass.requestFocus();
            return false;
        }

        return true;

    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id){
            case R.id.bt_register:
                if(registerUser()){
                    if(registerFB()==1){
                        opentDialog(Gravity.CENTER);
                    }
                }
                break;
            case R.id.eye_check:

                if(check == 0){
                    check=1;
                    eyeOn.setImageResource(R.drawable.ic_pass_off);
                    RePass.setTransformationMethod(null);
                }else if(check==1){
                    eyeOn.setImageResource(R.drawable.ic_show_pass);
                    RePass.setTransformationMethod(new PasswordTransformationMethod());
                    check=0;
                }
                break;
            case R.id.eye_check2:
                if(check == 0){
                    check=1;
                    eyeOn2.setImageResource(R.drawable.ic_pass_off);
                    ReCheckPass.setTransformationMethod(null);
                }else if(check==1){
                    eyeOn2.setImageResource(R.drawable.ic_show_pass);
                    ReCheckPass.setTransformationMethod(new PasswordTransformationMethod());
                    check=0;
                }
                break;
        }
    }

    private int registerFB(){
        fireBaseManager = new FireBaseManager();
        int n = fireBaseManager.pushData1(mEmail,mPass, ActivityRegister.this);

        return n;

    }

    public void opentDialog(int gravity){

        final Dialog dialog = new Dialog(this);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.register_success);

        Window window = dialog.getWindow();
        if(window == null){
            return;
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttribuites = window.getAttributes();
        windowAttribuites.gravity = gravity;
        window.setAttributes(windowAttribuites);

        if(Gravity.BOTTOM==gravity){

            dialog.setCancelable(true);
        }else{
            dialog.setCancelable(false);
        }

        TextView textView = dialog.findViewById(R.id.tvdialog);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivityRegister.this, LoginActivity.class);
                startActivity(i);
            }
        });

        dialog.show();

    }
}