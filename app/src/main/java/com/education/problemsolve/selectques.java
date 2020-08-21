package com.education.problemsolve;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class selectques extends AppCompatActivity implements View.OnClickListener {
    public ImageButton pb,cb,mb,ib;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectques);
        pb=(ImageButton) findViewById(R.id.phyid);
        cb=(ImageButton) findViewById(R.id.chemid);
        mb=(ImageButton) findViewById(R.id.mathid);

        TextView t2 = (TextView) findViewById(R.id.tvid);
        t2.setMovementMethod(LinkMovementMethod.getInstance());

        MobileAds.initialize(this,"ca-app-pub-6053745747833573~6309094886");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        ib=(ImageButton) findViewById(R.id.ictid);
        pb.setOnClickListener(this);
        cb.setOnClickListener(this);
        mb.setOnClickListener(this);
        ib.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
      switch (view.getId()){
          case R.id.phyid:
              Intent intent=new Intent(selectques.this,chooser.class);
              intent.putExtra("subkey", "physics");

              startActivity(intent);

              break;

          case R.id.chemid:
              Intent intent2=new Intent(selectques.this,chooser.class);
              intent2.putExtra("subkey", "chemistry");

              startActivity(intent2);

              break;
          case R.id.mathid:
              Intent intent3=new Intent(selectques.this,chooser.class);
              intent3.putExtra("subkey", "math");

              startActivity(intent3);

              break;
          case R.id.ictid:
              Intent intent4=new Intent(selectques.this,chooser.class);
              intent4.putExtra("subkey", "ict");

              startActivity(intent4);

              break;
      }



    }
}
