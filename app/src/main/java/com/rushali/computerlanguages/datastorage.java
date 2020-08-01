package com.rushali.computerlanguages;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;



public class datastorage
{
    private Context ctx;
    private String FileName;
    SharedPreferences pref;
    SharedPreferences.Editor writer;
    public static final int INTEGER=1;
    public static final int FLOAT=2;
    public static final int LONG=3;
    public static final int BOOLEAN=4;
    public static final int STRING=5;
    public datastorage(Context ctx,String FileName)
    {
        this.ctx = ctx;
        this.FileName = FileName;
        pref = this.ctx.getSharedPreferences(this.FileName,Context.MODE_PRIVATE);
        writer = pref.edit();
    }
    public void write(String key,int value)
    {
        writer.putInt(key,value);
        writer.commit();
    }

    public void write(String key,float value)
    {
        writer.putFloat(key,value);
        writer.commit();
    }

    public void write(String key,long value)
    {
        writer.putLong(key,value);
        writer.commit();
    }

    public void write(String key,boolean value)
    {
        writer.putBoolean(key,value);
        writer.commit();
    }

    public void write(String key,String value)
    {
        writer.putString(key,value);
        writer.commit();
    }

    public Object read(int DataType,String key)
    {
        Object temp=null;
        if(DataType==INTEGER)
            temp = pref.getInt(key,0);
        else if(DataType==FLOAT)
            temp = pref.getFloat(key,0);
        else if(DataType==LONG)
            temp = pref.getLong(key,0);
        else if(DataType==BOOLEAN)
            temp = pref.getBoolean(key,false);
        else if(DataType==STRING)
            temp = pref.getString(key,"");
        return temp;
    }

    public String ReadFileFromApp(int FileId)
    {
        Resources res = ctx.getResources();
        InputStream is = res.openRawResource(FileId);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(isr);
        StringBuffer temp = new StringBuffer(8192);
        char packet [] = new char[128];
        try
        {
            while(reader.read(packet)!=-1)
            {
                temp.append(packet);
                packet = new char[128];
            }
            reader.close();
            isr.close();
            is.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return temp.toString();
    }

}
