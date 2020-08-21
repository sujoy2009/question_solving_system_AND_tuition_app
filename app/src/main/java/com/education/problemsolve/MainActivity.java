package com.education.problemsolve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public Button subb,quesb,solvingb,mb,tm,rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        subb=(Button)findViewById(R.id.subid);
        quesb=(Button)findViewById(R.id.myquesid);
        solvingb=(Button)findViewById(R.id.solvingid);
        rb=(Button)findViewById(R.id.resultid);
    tm=(Button)findViewById(R.id.tutionid);
        mb=(Button)findViewById(R.id.memid);
        subb.setOnClickListener(this);
        quesb.setOnClickListener(this);
        solvingb.setOnClickListener(this);
        mb.setOnClickListener(this);
        tm.setOnClickListener(this);
        rb.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.helpid)
        {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=" + "com.education.problemsolve")));
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
            }


        }
        else if(item.getItemId()==R.id.shareid)
        {
            Intent inttt=new Intent(Intent.ACTION_SEND);
            inttt.setType("text/plain");
            String subject="An APP to ask question of science,find tutor and result";
            String body=" Student app.\nAn App to ask question of science to expert ,find tutor and result.\n Download it\n https://play.google.com/store/apps/details?id=com.education.problemsolve";
            inttt.putExtra(Intent.EXTRA_SUBJECT,subject);
            inttt.putExtra(Intent.EXTRA_TEXT,body);


            startActivity(Intent.createChooser(inttt,"Share with"));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {


        switch (view.getId()){
            case R.id.subid:

                Intent intent=new Intent(MainActivity.this,selectques.class);
                startActivity(intent);

                break;
            case R.id.myquesid:


                Intent intent2=new Intent(MainActivity.this,search.class);
                startActivity(intent2);





                break;
            case  R.id.solvingid:
                Intent intent3=new Intent(MainActivity.this,subjsolve.class);
                startActivity(intent3);
                break;
            case R.id.memid:
                Intent intent4=new Intent(MainActivity.this,memb.class);
                startActivity(intent4);
                break;
            case R.id.tutionid:
                Intent intent5=new Intent(MainActivity.this,tution.class);
                startActivity(intent5);
                break;
            case R.id.resultid:
                Intent intent6=new Intent(MainActivity.this,results.class);
                startActivity(intent6);
                break;


        }


    }
}
