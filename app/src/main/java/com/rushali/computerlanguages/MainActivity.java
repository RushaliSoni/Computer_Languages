package com.rushali.computerlanguages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class MainActivity extends AppCompatActivity
{
    int delay_in_sec=5000;
    Context ctx=this;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable()
        {
                @Override
                public void run() {
                    Intent i = new Intent(ctx, language_list_activity.class);
                    startActivity(i);
                    finish();
                }
            }, delay_in_sec);
    }

}






