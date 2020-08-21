package com.education.problemsolve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class image2 extends AppCompatActivity {

    private RecyclerView recyclerView;
   // private ProgressBar pb;
   ProgressDialog progressDialog ;
    public  int ques2=0;
    private castomadapter2 ca2;
    private List<upload> uploadList;
    FirebaseStorage firebaseStorage;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image2);
        recyclerView=findViewById(R.id.cardviewid);
        recyclerView.setHasFixedSize(true);
        progressDialog = new ProgressDialog(image2.this);
        progressDialog.setTitle("Loading.. Make sure u are connected to vpn");
        progressDialog.show();

       // pb=findViewById(R.id.progressid);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        uploadList=new ArrayList<>();
        firebaseStorage=FirebaseStorage.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference("upload");
        Bundle bundle=new Bundle();
        bundle= getIntent().getExtras();
        String sub=bundle.getString("subsolkey");
        String subb=sub+"us";
       // Toast.makeText(getApplicationContext(),subb,Toast.LENGTH_LONG).show();

        //  Query query = FirebaseDatabase.getInstance().getReference("upload");
        Query query;
        query = FirebaseDatabase.getInstance().getReference("upload")
                .orderByChild("cat")
                .equalTo(subb);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                uploadList.clear();
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    ques2=1;

                    upload up=dataSnapshot1.getValue(upload.class);
                    up.setKey(dataSnapshot1.getKey());



                    uploadList.add(up);




                }

                ca2=new castomadapter2(image2.this,uploadList);
                recyclerView.setAdapter(ca2);
                if(ques2==0){
                    Toast.makeText(getApplicationContext(),"No unsolved question found in this subject",Toast.LENGTH_LONG).show();
                }

                ca2.setonitemclicklistner(new castomadapter2.onItemclicklistner() {
                                             @Override
                                             public void onItemclick(int position) {
                                                 String text=uploadList.get(position).getImagename();
                                                 Toast.makeText(getApplicationContext(),text+" is selected  "+position,Toast.LENGTH_LONG).show();

                                             }

                                             @Override
                                             public void ondoanytaskk(int position) {
                                              //   Toast.makeText(getApplicationContext()," do any ",Toast.LENGTH_LONG).show();
                                                 upload selecteditem=uploadList.get(position);
                                                 String imagename=selecteditem.getImagename().trim();
                                                 String imageurl=selecteditem.getImageurl().trim();
                                                 String numberis=selecteditem.getNumberis().trim();
                                                 String cat=selecteditem.getCat();

                                                // upload up = new upload(imagename,dnurl.toString(),numberis,ans,anspicurl,cat,solver);

                                                 final String key=selecteditem.getKey();
                                                 StorageReference storageReference=firebaseStorage.getReferenceFromUrl(selecteditem.getImageurl());
                                                // final String key=selecteditem.getKey();
                                                // StorageReference storageReference=firebaseStorage.getReferenceFromUrl(selecteditem.getImageurl());

                                                 Intent intent=new Intent(image2.this,solving.class);
                                                 intent.putExtra("queskey", imagename);
                                                 intent.putExtra("quesurlkey", imageurl);
                                                 intent.putExtra("numberkey", numberis);
                                                 intent.putExtra("catkey", cat);
                                                 intent.putExtra("mkey", key);


                                                 startActivity(intent);

                                             }

                                             @Override
                                             public void ondelete(int position) {
                                                 upload selecteditem=uploadList.get(position);
                                                 Intent intent=new Intent(image2.this,zoomer.class);
                                               String abc=  selecteditem.getImageurl();
                                               intent.putExtra("zid",abc);
                                                 startActivity(intent);

                                                 /*
                                                 final String key=selecteditem.getKey();
                                                 StorageReference storageReference=firebaseStorage.getReferenceFromUrl(selecteditem.getImageurl());
                                                 storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                     @Override
                                                     public void onSuccess(Void aVoid) {
                                                         databaseReference.child(key).removeValue();
                                                     }
                                                 });


                                                 Toast.makeText(getApplicationContext()," delete ",Toast.LENGTH_LONG).show();

                                                  */

                                             }
                                         }

                );




                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"ERROR "+ databaseError.getMessage(),Toast.LENGTH_LONG).show();
               // pb.setVisibility(View.INVISIBLE);
                progressDialog.dismiss();

            }
        });

    }


}
