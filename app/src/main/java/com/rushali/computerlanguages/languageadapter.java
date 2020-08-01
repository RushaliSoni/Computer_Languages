package com.rushali.computerlanguages;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class languageadapter extends BaseAdapter
{
    private Context ctx;
    private String[] languages;
    private Mywidgetcontainer mywidgetcontainer;


    public languageadapter(Context ctx, String[] languages)
    {
        this.ctx = ctx;
        this.languages = languages;
    }

    @Override
    public int getCount() {
        return languages.length;
    }

    @Override
    public Object getItem(int index) {
        return languages[index];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View currentview, ViewGroup viewGroup)
    {
        View myview=currentview;
        if(myview==null)
        {
            LayoutInflater inflater = LayoutInflater.from(ctx);
            myview = inflater.inflate(R.layout.design_raw,null);
            mywidgetcontainer =new Mywidgetcontainer();
            mywidgetcontainer.lbltitle = (TextView)myview.findViewById(R.id.lbltitle);
            myview.setTag(mywidgetcontainer);
        }

        else
        {
            mywidgetcontainer = (Mywidgetcontainer)myview.getTag();
        }
        mywidgetcontainer.lbltitle.setText(languages[position]);

        return myview;

    }
    class Mywidgetcontainer
    {
        TextView lbltitle;
    }

}
