package com.quangminh.timeforlife;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.util.TimeUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.quangminh.timeforlife.FireBaseManager.FireBaseManager;

import java.util.concurrent.TimeUnit;

import static android.content.ContentValues.TAG;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button login;
    TextView createUser;
    EditText accLog, passLog;
    ImageView loginGg, loginFb, seenPass;

    String mAccount, mPass;
    int check=0;

    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
    ProgressBar progressBar;
    FireBaseManager fireBaseManager;


    private GoogleSignInClient mGoogleSignInClient;
    public final static int RC_SIGN_IN = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        anhXa();
        mAuth = FirebaseAuth.getInstance();


        login.setOnClickListener(this);
        createUser.setOnClickListener(this);
        seenPass.setOnClickListener(this);
        loginGg.setOnClickListener(this);

        createRequest();


    }

        @Override
    protected void onStart() {
        super.onStart();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if(firebaseUser!=null){
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            i.putExtra("ID", mAuth.getCurrentUser().getUid());
            startActivity(i);
        }
    }

    private void anhXa() {
        accLog = findViewById(R.id.Logtk);
        passLog = findViewById(R.id.LogMk);
        login = findViewById(R.id.bt_login);
        progressBar = findViewById(R.id.progressBarLog);
        seenPass = findViewById(R.id.eye_checkLog);
        createUser = findViewById(R.id.tvCreate);
        loginGg = findViewById(R.id.imageView51);
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id){
            case R.id.bt_login:


                if(valiDate()){
                    loginWithFirebase();

                }


                break;
            case R.id.textView8:
                Intent i = new Intent(LoginActivity.this, ActivityRegister.class);
                startActivity(i);
                break;
            case R.id.eye_checkLog:
                if(check == 0){
                    check=1;
                    seenPass.setImageResource(R.drawable.ic_pass_off);
                    passLog.setTransformationMethod(null);
                }else if(check==1){
                    seenPass.setImageResource(R.drawable.ic_show_pass);
                    passLog.setTransformationMethod(new PasswordTransformationMethod());
                    check=0;
                }
                break;
            case R.id.imageView51:

                Toast.makeText(LoginActivity.this, "img click", Toast.LENGTH_SHORT).show();
                resultLauncher.launch(new Intent(mGoogleSignInClient.getSignInIntent()));
                break;

        }
    }

    //validate
    private boolean valiDate(){

        mAccount = accLog.getText().toString().trim();
        mPass = passLog.getText().toString().trim();

        if(mAccount.isEmpty()){
            accLog.setError("Không được bỏ trống");
            accLog.requestFocus();
            return false;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(mAccount).matches()){
            accLog.setError("Email không hợp lệ");
            accLog.requestFocus();
            return false;
        }
        if(mPass.isEmpty()){
            passLog.setError("Không được bỏ trống");
            passLog.requestFocus();
            return false;
        }
        if(mPass.length()<6){
            passLog.setError("Mật khẩu phải lớn hơn 6");
            passLog.requestFocus();
            return false;
        }

        return true;
    }

    //login
    private void loginWithFirebase(){
        fireBaseManager = new FireBaseManager();
        fireBaseManager.loginFireBaseWithGuest(mAccount,mPass,LoginActivity.this);

    }

    private void createRequest() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    ActivityResultLauncher<Intent> resultLauncher= registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {

            if(result.getResultCode()== Activity.RESULT_OK){
                Intent intent = result.getData();

                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(intent);
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    GoogleSignInAccount account = task.getResult(ApiException.class);
                    Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                    firebaseAuthWithGoogle(account.getIdToken());
                } catch (ApiException e) {
                    // Google Sign In failed, update UI appropriately
                    Log.w(TAG, "Google sign in failed", e);
                }
            }
        }
    });

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();

                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(i);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());

                        }
                    }
                });
    }

    private void setData(){

    }

}