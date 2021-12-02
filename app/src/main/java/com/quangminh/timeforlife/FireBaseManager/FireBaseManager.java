package com.quangminh.timeforlife.FireBaseManager;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.quangminh.timeforlife.AddProject;
import com.quangminh.timeforlife.LoginActivity;
import com.quangminh.timeforlife.MainActivity;
import com.quangminh.timeforlife.R;
import com.quangminh.timeforlife.model.Account;
import com.quangminh.timeforlife.model.Schedule;

import java.util.ArrayList;
import java.util.List;

public class FireBaseManager {
    FirebaseAuth mAuth;
    public static int x=1;
    int n;

    String userId;
    LoginActivity loginActivity;

    public final static int RC_SIGN_IN = 123;


    //Đăng ký
    public int pushData1(String mEmail, String mPass, Activity activity){

        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(mEmail, mPass)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            String mId = firebaseUser.getUid();
                            userId=mId;
                            //opentDialog(Gravity.CENTER, activity);
                            x=1;
                            Account account = new Account(mEmail, mPass, mId);

                            FirebaseDatabase.getInstance().getReference("Account")
                                    .child(FirebaseAuth.getInstance().getUid()).setValue(account)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if(task.isSuccessful()){


                                                Toast.makeText(activity.getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                            }else{
                                                Toast.makeText(activity.getApplicationContext(), "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

                        }else {
                            Toast.makeText(activity.getApplicationContext(), "Đăng ký thất bại...", Toast.LENGTH_SHORT).show();

                            x=0;
                        }

                    }
                });

        return x;
    }

    //Đăng nhập
    public void loginFireBaseWithGuest(String mAccount, String mPass, Activity activity){

        loginActivity = new LoginActivity();
        mAuth = FirebaseAuth.getInstance();
        activity.findViewById(R.id.progressBarLog).setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(mAccount, mPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){


                    Toast.makeText(activity.getApplicationContext(), userId, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(activity.getApplicationContext(), MainActivity.class);
                    i.putExtra("ID", mAuth.getCurrentUser().getUid());
                    activity.startActivity(i);
                    activity.finishAffinity();
                    activity.findViewById(R.id.progressBarLog).setVisibility(View.INVISIBLE);

                }else{
                    Toast.makeText(activity.getApplicationContext(), "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();

                    activity.findViewById(R.id.progressBarLog).setVisibility(View.INVISIBLE);
                }

            }

        }
        );

    }

    public void onClickPushData( Schedule schedule, Activity activity,  String ID){

        mAuth=FirebaseAuth.getInstance();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        int year = schedule.getBookingYear();
        int month=schedule.getBookingMonth();
        int date = schedule.getBookingDate();
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser != null){
//            userId=currentUser.getUid();
//        }

        DatabaseReference databaseReference = firebaseDatabase.getReference("Account");
        databaseReference.child(ID+"/"+"list_booking_Day"+"/"+year+"/"+month+"/"+date+"/"+schedule.getId() ).setValue(schedule, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(activity.getApplicationContext(), "Push Success", Toast.LENGTH_SHORT).show();

            }
        });


    }

    public List<Schedule>  getDatabase(int year, int month, int date, String ID, List<Schedule> mListSchedule){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mRef= database.getReference(ID+"/"+"list_booking_Day"+"/"+year+"/"+month+"/"+date);

        List<Schedule> list = new ArrayList<>();
        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                Schedule schedule = snapshot.getValue(Schedule.class);
                if(schedule!=null){
                    list.add(schedule); // thoát hàm này là list thành null phải ko anh
                }


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return list;
    }


}
