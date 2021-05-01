package com.example.asus.eventmanager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Event_Details extends AppCompatActivity {






    private Button event_details_next_btn;
    private ImageButton mselectImage;
    private ImageButton header_image_btn;
    private EditText event_name_txt;
    private EditText event_details_txt;
    private Button event_post_btn;
    private Uri event_image_uri=null;


    private StorageReference event_storage;
    private DatabaseReference database;



    private ProgressDialog mprogress;







    private static final int GALLERY_REQUEST=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event__details);


        event_storage= FirebaseStorage.getInstance().getReference("BAA");

        mprogress=new ProgressDialog(this);
        database= FirebaseDatabase.getInstance().getReference().child("Event");







        event_details_next_btn=(Button)findViewById(R.id.event_details_next_btn_id);
        mselectImage=(ImageButton)findViewById(R.id.event_image_btn_id);
        header_image_btn=(ImageButton)findViewById(R.id.admin_image_btn_id);
        event_name_txt=(EditText)findViewById(R.id.event_name_id);
        event_details_txt=(EditText)findViewById(R.id.event_decription_id);
        event_post_btn=(Button)findViewById(R.id.event_post_btn_id);






        event_details_next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Event_Details.this,Select_sectors.class);
                startActivity(intent);




            }
        });


        mselectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent=new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,GALLERY_REQUEST);

            }
        });


        header_image_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  galleryIntent2=new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent2.setType("image/*");
                startActivityForResult(galleryIntent2,GALLERY_REQUEST);


            }
        });


        event_post_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postEvent();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==GALLERY_REQUEST&&resultCode==RESULT_OK){


             event_image_uri=data.getData();

            mselectImage.setImageURI(event_image_uri);



        }
    }


    private void postEvent()
    {


        mprogress.setMessage("posting.....");




        final String event_name_val=event_name_txt.getText().toString().trim();
        final String event_details_val=event_details_txt.getText().toString().trim();


        if(!TextUtils.isEmpty(event_name_val)&&!TextUtils.isEmpty(event_details_val)&&event_image_uri!=null)
        {




mprogress.show();


            StorageReference filepath=event_storage.child("event_image").child(event_image_uri.getLastPathSegment());
            filepath.putFile(event_image_uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Uri downloadUrl=taskSnapshot.getDownloadUrl();
mprogress.dismiss();
                   //atabaseReference newpost=database.push();
Event event = new Event(event_name_val,event_details_val,downloadUrl.toString());
                database.child(""+System.currentTimeMillis()).setValue(event);



                }
            });

        }





    }








}
