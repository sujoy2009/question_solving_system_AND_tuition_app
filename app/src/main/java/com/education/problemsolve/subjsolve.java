package com.education.problemsolve;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class subjsolve extends AppCompatActivity {

ImageButton psb,csb,msb,isb;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjsolve);
        psb=(ImageButton) findViewById(R.id.physolve);
        csb=(ImageButton) findViewById(R.id.chemsolve);
        msb=(ImageButton) findViewById(R.id.mathsolve);
        isb=(ImageButton) findViewById(R.id.ictsolve);

        MobileAds.initialize(this,"ca-app-pub-6053745747833573~6309094886");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        ConnectivityManager manager=(ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork=manager.getActiveNetworkInfo();



        psb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(null==activeNetwork){

                    Toast.makeText(getApplicationContext(),"No internet connection",Toast.LENGTH_LONG).show();

                }
                else {
                    Intent intent=new Intent(subjsolve.this,image2.class);
                    intent.putExtra("subsolkey", "physics");
                    // Toast.makeText(getApplicationContext(),"Long press to solve problem",Toast.LENGTH_LONG).show();

                    startActivity(intent);


                }


            }
        });

        csb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(null==activeNetwork){

                    Toast.makeText(getApplicationContext(),"No internet connection",Toast.LENGTH_LONG).show();

                }
                else {
                    Intent intent=new Intent(subjsolve.this,image2.class);
                    intent.putExtra("subsolkey", "chemistry");
                    // Toast.makeText(getApplicationContext(),"Long press to solve problem",Toast.LENGTH_LONG).show();

                    startActivity(intent);


                }

            }
        });
        msb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(null==activeNetwork){

                    Toast.makeText(getApplicationContext(),"No internet connection",Toast.LENGTH_LONG).show();

                }
                else {
                    Intent intent=new Intent(subjsolve.this,image2.class);
                    intent.putExtra("subsolkey", "math");
                    // Toast.makeText(getApplicationContext(),"Long press to solve problem",Toast.LENGTH_LONG).show();

                    startActivity(intent);


                }
            }
        });
        isb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(null==activeNetwork){

                    Toast.makeText(getApplicationContext(),"No internet connection",Toast.LENGTH_LONG).show();

                }
                else {
                    Intent intent=new Intent(subjsolve.this,image2.class);
                    intent.putExtra("subsolkey", "ict");
                    // Toast.makeText(getApplicationContext(),"Long press to solve problem",Toast.LENGTH_LONG).show();

                    startActivity(intent);


                }

            }
        });

    }
}
