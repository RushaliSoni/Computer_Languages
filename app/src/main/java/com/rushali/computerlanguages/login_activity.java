package com.rushali.computerlanguages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login_activity extends AppCompatActivity {
    EditText e1, e2;
    TextView txtRegister, txtforgot;
    Button btnLogin;
    String Email, Password;
    FirebaseUser user;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
        AllocateMemory();
        SetEvent();
    }

    private void SetEvent()
    {
        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i11 = new Intent(login_activity.this,register_activity.class);
                startActivity(i11);
            }
        });
        txtforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i11 = new Intent(login_activity.this,forgotpass_activity.class);
                startActivity(i11);
            }
        });
    }


    private void AllocateMemory()
    {
        e1= findViewById(R.id.logEmail);
        e2= findViewById(R.id.logPassword);
        txtRegister= findViewById(R.id.txtRegister);
        txtforgot=findViewById(R.id.txtForgot);
        btnLogin= findViewById(R.id.btnlogin);
        auth= FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
    }

    public void checkLogin(View view)
    {
        Email= e1.getText().toString();
        Password= e2.getText().toString();
        if(!TextUtils.isEmpty(Email) && !TextUtils.isEmpty(Password))
        {
            auth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        if(auth.getCurrentUser().isEmailVerified())
                        {
                            Intent i10 = new Intent(login_activity.this, language_list_activity.class);
                            startActivity(i10);
                            finish();
                        }
                        else {
                            Toast.makeText(login_activity.this, "Please Verify your Email", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(login_activity.this, "Please Check Your Connection", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
        else
        {
            Toast.makeText(login_activity.this, "Please Enter All Above Field", Toast.LENGTH_SHORT).show();
        }
    }
}
