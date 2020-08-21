package com.education.problemsolve;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class save extends AppCompatActivity {
    EditText nameb,numb,varb,salb,subb;
    Button saveb;
    Spinner locb;
    public DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        getSupportActionBar().setTitle(" Registration form");

        nameb=(EditText)findViewById(R.id.nameid);
        numb=(EditText)findViewById(R.id.numberid);
        varb=(EditText)findViewById(R.id.uniid);
        salb=(EditText)findViewById(R.id.salaryid);
        subb=(EditText)findViewById(R.id.subid);
        locb=(Spinner)findViewById(R.id.locationid);
        saveb=(Button)findViewById(R.id.saveitid);
        databaseReference= FirebaseDatabase.getInstance().getReference("upload");
        saveb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String    name=nameb.getText().toString().trim();
                String    number=nameb.getText().toString().trim();
                String    varsity=varb.getText().toString().trim();
                String    salary=salb.getText().toString().trim();
                String    sub=subb.getText().toString().trim();
                String    loc=locb.getSelectedItem().toString();

                ConnectivityManager manager=(ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork=manager.getActiveNetworkInfo();
                if(null==activeNetwork){

                   // Toasty.warning(this,"No internet connection",Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();

                }
                else {
                    if(name.isEmpty() || number.isEmpty()|| varsity.isEmpty() || salary.isEmpty()|| salary.isEmpty())

                    {
                        Toast.makeText(getApplicationContext(), "Enter all info", Toast.LENGTH_LONG).show();
                    }
                    else{

                        String    ans="";
                        upload up = new upload(name, salary, number, ans, sub, loc, varsity);
                        String ImageUploadId = databaseReference.push().getKey();
                        databaseReference.child(ImageUploadId).setValue(up);
                        Toast.makeText(getApplicationContext(), "Registration successfull", Toast.LENGTH_LONG).show();



                    }

                }







                }






        });




    }
}
