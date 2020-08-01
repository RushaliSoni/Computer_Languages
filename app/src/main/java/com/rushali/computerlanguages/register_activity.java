package com.rushali.computerlanguages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class register_activity extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6;
    TextView txtLogin;
    Button btnRegister;
    String Email,Password,CPassword;
    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_activity);
        AllocateMemory();
        SetEvent();
    }
    private void SetEvent()
    {
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i9 = new Intent(register_activity.this, login_activity.class);
                startActivity(i9);
            }
        });

    }
    private void AllocateMemory()
    {
        e1= findViewById(R.id.regEmail);
        e5= findViewById(R.id.regPassword);
        e6= findViewById(R.id.regCPassword);
        txtLogin= findViewById(R.id.txtlogin);
        btnRegister= findViewById(R.id.btnRegister);
        auth= FirebaseAuth.getInstance();
        user= auth.getCurrentUser();
    }

    public void checkRegistration(View view)
    {
        Email= e1.getText().toString();
        Password= e5.getText().toString();
        CPassword= e6.getText().toString();
        if(!TextUtils.isEmpty(Email) && !TextUtils.isEmpty(Password) && !TextUtils.isEmpty(CPassword))
        {
            if(Password.equals(CPassword))
            {
                if(Password.length()>8)
                {
                    auth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                auth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful())
                                        {
                                            Toast.makeText(register_activity.this, "Please Check Your Mail and Verify Your Account", Toast.LENGTH_SHORT).show();
                                            auth.signOut();
                                            Intent i8= new Intent(register_activity.this,login_activity.class);
                                            startActivity(i8);
                                            finish();
                                        }
                                        else
                                        {
                                            Toast.makeText(register_activity.this, "Verification Email is Not Send Please Try Again", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                            }
                            else
                            {
                                Toast.makeText(register_activity.this, "Check Your Connection", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(register_activity.this, "Please Enter Strong Password Length greter Then 8", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(register_activity.this, "Password and Conform Password Not Matched", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(register_activity.this, "Please Enter All Above Field", Toast.LENGTH_SHORT).show();
        }
    }
}
