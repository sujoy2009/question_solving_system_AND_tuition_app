package com.education.problemsolve;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.List;

public class search extends AppCompatActivity {
    EditText pinerb;
    private List<upload> uploadList;
    private AdView mAdView;

    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        pinerb=(EditText)findViewById(R.id.studentpinid);
        b1=(Button)findViewById(R.id.showbid);



        MobileAds.initialize(this,"ca-app-pub-6053745747833573~6309094886");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pin=pinerb.getText().toString().trim();
                if(pin.length()<4){
                    pinerb.setError("Wrong pin");
                }
                else {
                    Intent intent=new Intent(search.this,image.class);
                    intent.putExtra("pinkey", pin);
                    startActivity(intent);


                }

        }
        });



}}
