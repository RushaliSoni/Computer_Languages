package com.rushali.computerlanguages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class language_list_activity extends AppCompatActivity {
    ListView lst;
    datastorage ds;
    String FileName = "Rushali";
    ShareActionProvider shareActionProvider;
    String[] languages;
    FirebaseAuth auth;
    FirebaseUser user;
    long time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_list_activity);
        AllocateMemory();
        GetData();
        ShowData();
        SetEvent();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(user == null)
        {
            Intent in= new Intent(language_list_activity.this, login_activity.class);
            startActivity(in);
            finish();
        }
    }

    private void SetEvent()
    {
     lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             Intent switchIntent2 = new Intent(language_list_activity.this, language_detail_activity.class);
             switchIntent2.putExtra("position", position);
             switchIntent2.putExtra("languageName", languages[position]);
             switchIntent2.putExtra("number", position);
             startActivity(switchIntent2);

         }
     });
    }

    private void ShowData()
    {
        languageadapter ad = new languageadapter(language_list_activity.this,languages);
        lst.setAdapter(ad);
    }

    private void GetData()
    {
        languages = getResources().getStringArray(R.array.languages);
    }

    private void AllocateMemory()
    {
        lst = findViewById(R.id.languageList);
        ds= new datastorage(language_list_activity.this,FileName);
        auth= FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.optionmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.share:
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBodyText = "Check it out this awesome app. https://play.google.com/store/apps/details?id=com.rushali.computerlanguages;";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "<_Subject goes here_>");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
                startActivity(Intent.createChooser(sharingIntent, "Sharing Option"));
                break;
            case R.id.rate:
                Uri uri = Uri.parse("market://details?id=" + getPackageName());
                Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    startActivity(myAppLinkToMarket);
                }
                catch (ActivityNotFoundException e) {
                    Toast.makeText(this, "Unable To Find This Application On PlayStore", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.Log:
                auth.signOut();
                Intent in= new Intent(language_list_activity.this, login_activity.class);
                startActivity(in);
                finish();
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(time +2000 > System.currentTimeMillis())
        {
            super.onBackPressed();
            finish();
        }
        else
        {
            Toast.makeText(this, "Press Back To Exit", Toast.LENGTH_SHORT).show();
        }
        time = System.currentTimeMillis();
    }
}

