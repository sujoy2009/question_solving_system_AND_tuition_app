package com.education.problemsolve;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class location extends AppCompatActivity {
    Spinner lc;
    Button searchb;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        getSupportActionBar().setTitle(" Location");
        lc=(Spinner)findViewById(R.id.lsid);

        MobileAds.initialize(this,"ca-app-pub-6053745747833573~6309094886");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        searchb=(Button)findViewById(R.id.s2);
        searchb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loc=lc.getSelectedItem().toString();
                ConnectivityManager manager=(ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork=manager.getActiveNetworkInfo();
                if(null==activeNetwork){

                    Toast.makeText(getApplicationContext(),"No internet connection",Toast.LENGTH_LONG).show();

                }
                else {
                    Intent intent=new Intent(location.this,detail.class);
                    intent.putExtra("lockey", loc);
                    startActivity(intent);


                }


            }
        });
    }
}
