package com.rushali.computerlanguages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.ListView;
import android.widget.ShareActionProvider;
import android.widget.TextView;

public class language_detail_activity extends AppCompatActivity {
    TextView txttitle, txtdetail;
    int position;
    String FileName = "Rushali";
    datastorage ds;
    int FileId[] = {R.raw.language1, R.raw.language2, R.raw.language3,
            R.raw.language4, R.raw.language5, R.raw.language6, R.raw.language7, R.raw.language8,
            R.raw.language9};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_detail_activity);
        AllocateMemory();
        position = getIntent().getExtras().getInt("position");
        String FileContent = ds.ReadFileFromApp(FileId[position]);
        txtdetail.setText(Html.fromHtml(FileContent));
        txttitle.setText(getIntent().getExtras().getString("languageName"));
    }


    private void AllocateMemory()
    {
        txttitle = findViewById(R.id.txttitle);
        txtdetail= findViewById(R.id.txtdetail);
        ds = new datastorage(language_detail_activity.this,FileName);
    }
}
