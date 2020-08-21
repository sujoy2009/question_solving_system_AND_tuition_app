package com.education.problemsolve;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class results extends AppCompatActivity {
    Button s,h,J,n;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        s=(Button)findViewById(R.id.sscid);
        h=(Button)findViewById(R.id.hscid);
        J=(Button)findViewById(R.id.jscid);
      //  n=(Button)findViewById(R.id.nuid);

        MobileAds.initialize(this,"ca-app-pub-6053745747833573~6309094886");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager manager=(ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork=manager.getActiveNetworkInfo();
                if(null==activeNetwork){

                    Toast.makeText(getApplicationContext(),"No internet connection",Toast.LENGTH_LONG).show();

                }
                else {
                    Intent intent=new Intent(results.this,resultshow.class);

                    startActivity(intent);

                }

            }
        });
        h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager manager=(ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork=manager.getActiveNetworkInfo();
                if(null==activeNetwork){

                    Toast.makeText(getApplicationContext(),"No internet connection",Toast.LENGTH_LONG).show();

                }
                else {
                    Intent intent=new Intent(results.this,resultshow.class);

                    startActivity(intent);

                }
            }
        });
        J.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager manager=(ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork=manager.getActiveNetworkInfo();
                if(null==activeNetwork){

                    Toast.makeText(getApplicationContext(),"No internet connection",Toast.LENGTH_LONG).show();

                }
                else {
                    Intent intent=new Intent(results.this,resultshow.class);

                    startActivity(intent);

                }
            }
        });

    }
}
