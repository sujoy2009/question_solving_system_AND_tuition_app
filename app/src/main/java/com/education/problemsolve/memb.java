package com.education.problemsolve;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class memb extends AppCompatActivity {
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memb);

        MobileAds.initialize(this,"ca-app-pub-6053745747833573~6309094886");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        TextView t2 = (TextView) findViewById(R.id.lid);
        t2.setMovementMethod(LinkMovementMethod.getInstance());
        getSupportActionBar().setTitle("Solver membership form");
    }
}
