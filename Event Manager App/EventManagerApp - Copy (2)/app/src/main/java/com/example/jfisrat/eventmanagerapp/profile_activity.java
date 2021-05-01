package com.example.jfisrat.eventmanagerapp;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;

import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

import static java.security.AccessController.getContext;

public class profile_activity extends AppCompatActivity {
    private static final int PICK_IMAGE = 1;
    private Toolbar toolbar;
    private ImageView imageView;
    private Button editButton,save;
    private TextView userName,name,dept,varsity,mail,contact,mobile,addTitle,address,occupation;
    FirebaseAuth auth;
    Uri uriImage;
    String profileImageUrl;
    Typeface typeface1,typeface2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_activity);
        toolbar=findViewById(R.id.myProfileToolbarId);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageView=findViewById(R.id.profileImageId_myProfile);
        userName=findViewById(R.id.P_usernameId);
        editButton=findViewById(R.id.editButtonId);
        name=findViewById(R.id.P_nameId);
        dept=findViewById(R.id.P_deptId);
        varsity=findViewById(R.id.P_varsityId);
        occupation=findViewById(R.id.P_occupationId);
        contact=findViewById(R.id.P_contactId);
        mail=findViewById(R.id.P_mailId);
        mobile=findViewById(R.id.P_mobileId);
        addTitle=findViewById(R.id.P_addressTitleId);
        address=findViewById(R.id.P_addressId);
        save=findViewById(R.id.imageSaveButtonId);


        typeface1=Typeface.createFromAsset(getAssets(),"font/text_font.ttf");
        typeface2=Typeface.createFromAsset(getAssets(),"font/button_font.ttf");

        userName.setTypeface(typeface2);
        name.setTypeface(typeface1);
        dept.setTypeface(typeface1);
        varsity.setTypeface(typeface1);
        occupation.setTypeface(typeface1);
        contact.setTypeface(typeface2);
        mail.setTypeface(typeface1);
        mobile.setTypeface(typeface1);
        addTitle.setTypeface(typeface2);
        address.setTypeface(typeface1);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 showImageChooser();
            }
        });
        
        //loadUserImage();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInformation();
            }
        });

        //loadUserImage();

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(profile_activity.this,EditProfile.class));
            }
        });

    }




    /*private void loadUserImage() {
        FirebaseUser user=auth.getCurrentUser();
        //String photoUrl=user.getPhotoUrl().toString();

        if(user!=null){
            if(user.getPhotoUrl()!=null){

               // Glide.with(this).load(user.getPhotoUrl().toString())
                        //.into(imageView);
            }
        }
    }*/


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE&&resultCode==RESULT_OK&&data!=null&&data.getData()!=null){

            uriImage=data.getData();
            try {
                Bitmap bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(),uriImage);
                imageView.setImageBitmap(bitmap);
                uploadImageToFireBaseStorage();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void uploadImageToFireBaseStorage() {
        StorageReference profileImageref=FirebaseStorage.getInstance().getReference("profilepics"+System.currentTimeMillis()+".jpg");
        if(uriImage!=null){
            profileImageref.putFile(uriImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                     profileImageUrl=taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(profile_activity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void saveUserInformation() {
        FirebaseUser user=auth.getCurrentUser();
        if(user!=null && profileImageUrl!=null){
            UserProfileChangeRequest profile=new UserProfileChangeRequest.Builder()
                    .setPhotoUri(Uri.parse(profileImageUrl)).build();

            user.updateProfile(profile)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(profile_activity.this,"update Profile",Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        }
    }


    private void showImageChooser(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"profile Image"),PICK_IMAGE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
