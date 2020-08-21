package com.education.problemsolve;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class solving extends AppCompatActivity {
    public DatabaseReference databaseReference;
   public FirebaseStorage firebaseStorage;
    public StorageReference storageReference;
    public Button choseb,savebb;
    private ImageView imageView;
    public EditText e1,e2;
    public ProgressBar progressBar;
    private ProgressDialog progressDialog;
    StorageTask uploadtask;
   // public Uri imageuri;
    private Uri anspicuri;
    public  int tt=0;

    private  static  final int IMAGE_REQUEST=2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solving);
        choseb=(Button) findViewById(R.id.chosesid);
        getSupportActionBar().setTitle(" Answere submission");
        e1=(EditText) findViewById(R.id.aboutpicsid);
        progressBar= findViewById(R.id.psid);
        e2=(EditText) findViewById(R.id.pinsid);
        progressDialog = new ProgressDialog(solving.this);
        savebb=(Button)findViewById(R.id.savesid);
        imageView=findViewById(R.id.picsid);

        databaseReference= FirebaseDatabase.getInstance().getReference("upload");
        storageReference= FirebaseStorage.getInstance().getReference("upload");

        choseb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openchoser();
            }
        });
        savebb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(uploadtask!=null && uploadtask.isInProgress()){
                    Toast.makeText(getApplicationContext(),"already sAVING",Toast.LENGTH_SHORT).show();

                }
                else {
                    Bundle bundle=new Bundle();
                    bundle= getIntent().getExtras();
                    final String imagename=bundle.getString("queskey");
                    final String imageurl=bundle.getString("quesurlkey");
                    final String cat=bundle.getString("catkey");
                    final String numberis=bundle.getString("numberkey");
                    final String key=bundle.getString("mkey");




                   // Intent intent=new Intent(solving.this,test.class);
                   // startActivity(intent);
                    ConnectivityManager manager=(ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo activeNetwork=manager.getActiveNetworkInfo();
                    if(null==activeNetwork){

                        // Toasty.warning(this,"No internet connection",Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();

                    }
                    else {
                        save(imagename,imageurl,numberis,cat,key);

                    }



                }


            }
        });
    }
    private void openchoser() {
        Intent intent=new Intent();
        intent.setType("image/*");
        tt=1;
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMAGE_REQUEST);
    }
    public String ex(Uri anspicuri){
        ContentResolver contentResolver=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(anspicuri));

    }

    public void save(final String imagename, final String imageurl, final String numberis, final String cat, final String key){

        final String ans=e1.getText().toString();
        final String solver=e2.getText().toString();
       final String anspicurl="";



       // final String solver="";

       // final String cat=sub+"us";
        if(solver.isEmpty()) {
            e2.setError("enter");
            e2.requestFocus();
            return;
        }
        else {
           // cat=cat+"d";
            if(solver.equalsIgnoreCase("aa11") || solver.equalsIgnoreCase("aa22") || solver.equalsIgnoreCase("bb11") || solver.equalsIgnoreCase("bb22")|| solver.equalsIgnoreCase("cc22")|| solver.equalsIgnoreCase("dd22") || solver.equalsIgnoreCase("ee22")|| solver.equalsIgnoreCase("cc11") || solver.equalsIgnoreCase("dd11") || solver.equalsIgnoreCase("ee11") || solver.equalsIgnoreCase("ss11") || solver.equalsIgnoreCase("ss22") ){
                if(tt==0){
                    String dnurl2="https://firebasestorage.googleapis.com/v0/b/problemsolve-ab11c.appspot.com/o/upload%2F1589531622750.jpg?alt=media&token=2a50fa33-1fb3-4721-8b3f-ec03d2275502";
                    //cat=cat+"d";
                    upload up = new upload(imagename,imageurl,numberis,ans,dnurl2,cat+"d",solver);
                    // String ImageUploadId = databaseReference.push().getKey();
                    databaseReference.child(key).setValue(up);
                    Toast.makeText(getApplicationContext(), "ans uploaded", Toast.LENGTH_LONG).show();

                }

                else {


                    progressDialog.setTitle("Ans posting.. Make sure you are connected with vpn");
                    progressDialog.show();
                    StorageReference storageReference2 = storageReference.child(System.currentTimeMillis() + "." + ex(anspicuri));
                    storageReference2.putFile(anspicuri)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                progressDialog.dismiss();

                                   Toast.makeText(getApplicationContext(), "Ans uploaded", Toast.LENGTH_LONG).show();
                                    @SuppressWarnings("VisibleForTests")
                                    Task<Uri> uriTask=taskSnapshot.getStorage().getDownloadUrl();
                                    while (!uriTask.isSuccessful());
                                    Uri dnurl2=uriTask.getResult();
                                    upload up = new upload(imagename,imageurl,numberis,ans,dnurl2.toString(),cat+"d",solver);
                                    // String ImageUploadId = databaseReference.push().getKey();
                                    databaseReference.child(key).setValue(up);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Ans not  Uploaded ", Toast.LENGTH_LONG).show();

                        }
                    });

                }



            }
            else {
                e2.setError("Enter wright passward");
                e2.requestFocus();
                return;
               // Toast.makeText(getApplicationContext(), "Enter wright passward", Toast.LENGTH_LONG).show();

            }

        }

     /*

           StorageReference storageReferenced=firebaseStorage.getReferenceFromUrl(key);
        storageReferenced.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                databaseReference.child(key).removeValue();
            }
        });

      */








    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==IMAGE_REQUEST && resultCode==RESULT_OK && data!=null && data.getData()!=null){

            anspicuri=data.getData();
            Picasso.with(this).load(anspicuri).into(imageView);
        }
    }


}
