package com.rushali.computerlanguages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

public class forgotpass_activity extends AppCompatActivity {

    EditText e1;
    FirebaseAuth auth;
    FirebaseUser user;
    String RegisterEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpass_activity);
        e1=findViewById(R.id.forEmail);
        auth= FirebaseAuth.getInstance();
        user= auth.getCurrentUser();


    }


    public void RecoverPassword(View view)
    {
        RegisterEmail= e1.getText().toString().trim();
        if(!TextUtils.isEmpty(RegisterEmail))
        {
            auth.sendPasswordResetEmail(RegisterEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful()) {
                            Toast.makeText(forgotpass_activity.this, "Check Your Mail and Reset Your Password", Toast.LENGTH_SHORT).show();
                            Intent i6 = new Intent(forgotpass_activity.this, login_activity.class);
                            startActivity(i6);
                            finish();
                        } else
                            {
                            Toast.makeText(forgotpass_activity.this, "Something Went Wrong Please Check Email Address Or Internet Connection", Toast.LENGTH_SHORT).show();
                        }

                    }
                });


        }
        else
        {
            Toast.makeText(this, "Please Enter Email Address", Toast.LENGTH_SHORT).show();
        }

    }
}
