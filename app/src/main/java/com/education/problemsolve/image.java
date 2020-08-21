package com.education.problemsolve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class image extends AppCompatActivity {

    private RecyclerView recyclerView;
   // private ProgressBar pb;
    ProgressDialog progressDialog ;
    private castomadapter ca;
    //private ImageView im;

    private  int ques=0;

    private castomadapter2 ca2;
    public  int i=0;
    public int [] arr=new int[500];

    private List<upload> uploadList;
    FirebaseStorage firebaseStorage;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        recyclerView=findViewById(R.id.cardviewid);
        recyclerView.setHasFixedSize(true);
        progressDialog = new ProgressDialog(image.this);
        progressDialog.setTitle("Loading.. Make sure u are connected to vpn");
       progressDialog.show();
     //  pb=findViewById(R.id.progressid);
        for (int i=0;i<3;i++){
            arr[i]=1;
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        uploadList=new ArrayList<>();
        firebaseStorage=FirebaseStorage.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference("upload");
        Bundle bundle=new Bundle();
        bundle= getIntent().getExtras();
        String pin=bundle.getString("pinkey");
       // Toast.makeText(getApplicationContext(),pin,Toast.LENGTH_LONG).show();

       //  Query query = FirebaseDatabase.getInstance().getReference("upload");
        Query query;
        query = FirebaseDatabase.getInstance().getReference("upload")
               .orderByChild("numberis")
              .equalTo(pin);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                uploadList.clear();
                i=0;
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    ques=1;
                    upload up=dataSnapshot1.getValue(upload.class);
                    up.setKey(dataSnapshot1.getKey());


                    if(up.getAnspicurl().isEmpty()){
                        int s=up.getCat().length();
                        String ms=up.getCat();
                        char c=ms.charAt(s-1);
                        if(c=='s'){
                            up.setAnspicurl("https://firebasestorage.googleapis.com/v0/b/problemsolve-ab11c.appspot.com/o/upload%2F1589531470993.jpg?alt=media&token=47ae25ad-0c09-4344-b024-a69cafdfb17d");

                        }

                    }


                    uploadList.add(up);

                }



                ca=new castomadapter(image.this,uploadList);

                recyclerView.setAdapter(ca);
                ca.setonitemclicklistner(new castomadapter2.onItemclicklistner() {
                    @Override
                    public void onItemclick(int position) {

                    }

                    @Override
                    public void ondoanytaskk(int position) {

                    }

                    @Override
                    public void ondelete(int position) {

                    }
                });





                if(ques==0){
                    Toast.makeText(getApplicationContext(),"No question found in this pin number",Toast.LENGTH_LONG).show();
                }
                /*
                if(ques==1){
                    Toast.makeText(getApplicationContext(),"Long press upon the question to solve ",Toast.LENGTH_LONG).show();
                }

                 */








                progressDialog.dismiss();

               // pb.setVisibility(View.INVISIBLE);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"ERROR "+ databaseError.getMessage(),Toast.LENGTH_LONG).show();
             //  pb.setVisibility(View.INVISIBLE);
               // progressDialog.dismiss();

            }
        });

    }


    }
