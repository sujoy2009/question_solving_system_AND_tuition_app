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

public class chooser extends AppCompatActivity {
    public DatabaseReference databaseReference;
    public StorageReference storageReference;
    private Button choseb,showb,saveb;
    private ImageView imageView;
    public  int t=0;
    public EditText e1,e2;
    public int vpn=0;
    public ProgressBar progressBar;
   ProgressDialog progressDialog ;
    StorageTask uploadtask;
    public Uri imageuri;
    private  static  final int IMAGE_REQUEST=1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooser);
       progressDialog = new ProgressDialog(chooser.this);
        getSupportActionBar().setTitle(" Question submission");



        choseb=(Button)findViewById(R.id.choseid);
        saveb=(Button)findViewById(R.id.saveid);

        imageView=findViewById(R.id.picid);
        e1=(EditText) findViewById(R.id.aboutpicid);
        e2=(EditText)findViewById(R.id.numberid);
        progressBar= findViewById(R.id.pid);
        databaseReference= FirebaseDatabase.getInstance().getReference("upload");
        storageReference= FirebaseStorage.getInstance().getReference("upload");
        saveb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(uploadtask!=null && uploadtask.isInProgress()){
                    Toast.makeText(getApplicationContext(),"already sAVING",Toast.LENGTH_SHORT).show();

                }
                else {
                    Bundle bundle=new Bundle();
                    bundle= getIntent().getExtras();
                    String sub=bundle.getString("subkey");



                    save(sub);



                }

            }
        });
        choseb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openchoser();


            }
        });


    }

    public String ex(Uri imageuri){
        ContentResolver contentResolver=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(imageuri));

    }
    private void openchoser() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        t=1;
        startActivityForResult(intent,IMAGE_REQUEST);
    }

    public void save(String sub) {

        final String imagename = e1.getText().toString();
        final String numberis = e2.getText().toString();
        final String ans = "";
        final String anspicurl = "";
        final String solver = "";

        final String cat = sub + "us";
        ConnectivityManager manager=(ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork=manager.getActiveNetworkInfo();
        if(null==activeNetwork){

            // Toasty.warning(this,"No internet connection",Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();

        }
        else {
            if (numberis.isEmpty()) {
                e2.setError("enter a pin for the question");
                e2.requestFocus();
                return;
            }
            if (numberis.length()<4) {
                e2.setError("pin must be at least of 4 digit");
                e2.requestFocus();
                return;
            }

            if (t == 0) {
                String dnurl = "https://firebasestorage.googleapis.com/v0/b/problemsolve-ab11c.appspot.com/o/upload%2F1589531430066.jpg?alt=media&token=faf47a7f-4a1c-40c4-9ae9-936988274c01";
                upload up = new upload(imagename, dnurl, numberis, ans, anspicurl, cat, solver);
                String ImageUploadId = databaseReference.push().getKey();
                databaseReference.child(ImageUploadId).setValue(up);
                Toast.makeText(getApplicationContext(), "data Uploaded Successfully ", Toast.LENGTH_LONG).show();

            } else {


                // Toast.makeText(getApplicationContext(), "saving....please wait", Toast.LENGTH_LONG).show();

                progressDialog.setTitle("Question posting.. Make sure you are connected with vpn");
                progressDialog.show();
                StorageReference storageReference2 = storageReference.child(System.currentTimeMillis() + "." + ex(imageuri));
                storageReference2.putFile(imageuri)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                vpn = 1;


                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Question posted Successfully ", Toast.LENGTH_LONG).show();
                                @SuppressWarnings("VisibleForTests")
                                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                                while (!uriTask.isSuccessful()) ;
                                Uri dnurl = uriTask.getResult();

                                upload up = new upload(imagename, dnurl.toString(), numberis, ans, anspicurl, cat, solver);
                                String ImageUploadId = databaseReference.push().getKey();
                                databaseReference.child(ImageUploadId).setValue(up);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        progressDialog.dismiss();
                                                        Toast.makeText(getApplicationContext(), "Question not  Uploaded ", Toast.LENGTH_LONG).show();
                                                        vpn = 1;

                                                    }
                                                }

                );


            }

        }

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==IMAGE_REQUEST && resultCode==RESULT_OK && data!=null && data.getData()!=null){

            imageuri=data.getData();
            Picasso.with(this).load(imageuri).into(imageView);
        }
    }
}
