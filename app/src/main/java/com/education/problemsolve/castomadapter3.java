package com.education.problemsolve;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class castomadapter3 extends ArrayAdapter<upload> {

    private Activity context;
    //  public List<doctor> doctorList;
    public ArrayList<upload> doctorList;

    public castomadapter3(Activity context, ArrayList<upload> doctorList) {
        super(context, R.layout.sample, doctorList);
        this.context = context;
        this.doctorList = doctorList;
    }

    @NonNull
    @Override
    public View getView(int position, View ConvertView, ViewGroup parent) {
        LayoutInflater layoutInflater =context.getLayoutInflater();


        View view=layoutInflater.inflate(R.layout.sample,null,true);
        upload up=doctorList.get(position);

        TextView textView1=view.findViewById(R.id.nm2id);
        TextView textView2=view.findViewById(R.id.loc2id);
        TextView textView3=view.findViewById(R.id.cn2id);
        TextView textView4=view.findViewById(R.id.sal2id);
        TextView textView5=view.findViewById(R.id.var2id);
        TextView textView6=view.findViewById(R.id.sub2id);


        textView1.setText("Name: "+up.getImagename());
        textView2.setText("Location: "+up.getCat());
        textView3.setText("contact: "+up.getNumberis());
        textView4.setText("Aproximate salary: "+up.getImageurl());
        textView5.setText("Educational background: "+up.getSolver());
        textView5.setText("subject: "+up.getAnspicurl());





        return view;
    }

}
