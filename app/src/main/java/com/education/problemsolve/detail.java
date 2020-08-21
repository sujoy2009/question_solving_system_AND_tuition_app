package com.education.problemsolve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class detail extends AppCompatActivity {
    private ListView lv;
    // public static Context context;

    DatabaseReference databaseReference;

    private ArrayList<upload> doctorList;

    private castomadapter3 ca3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        lv = (ListView) findViewById(R.id.lkid);
        databaseReference = FirebaseDatabase.getInstance().getReference("upload");
        Bundle bundle=new Bundle();
        bundle= getIntent().getExtras();
       // String ctg=new String() ;
        String LC=new String();
        Query query = FirebaseDatabase.getInstance().getReference("upload");

        LC=bundle.getString("lockey");
        getSupportActionBar().setTitle(" Tutors of "+LC+" city");







        query = FirebaseDatabase.getInstance().getReference("upload")
                .orderByChild("cat")
                .equalTo(LC);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                doctorList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    upload up = dataSnapshot1.getValue(upload.class);
                    doctorList.add(up);
                }
                lv.setAdapter(ca3);
                if(ca3.isEmpty()){
                    // Toast.makeText(getApplicationContext(),"কোন ডাক্তার এখনো রেজিষ্ট্রেশন করে নি এখানে",Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(),"No tutor is registered in this scope yet",Toast.LENGTH_SHORT).show();

                }

            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
        super.onStart();

        //databaseReference2= FirebaseDatabase.getInstance().getReference("doctorss");
        doctorList = new ArrayList<>();
        ca3 = new castomadapter3(detail.this, doctorList);




    }
}
