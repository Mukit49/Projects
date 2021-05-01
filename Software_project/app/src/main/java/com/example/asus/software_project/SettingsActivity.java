package com.example.asus.software_project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.software_project.Prevalent.Prevalent;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingsActivity extends AppCompatActivity {

    private CircleImageView profileimageview;
    private EditText fullNameEdittxt,phoneEdittxt,addressEdittxt;
    private TextView profilechangetxt,closetxt,updatetxt;


    private Uri imageUri;
    private String myUrl= "";
    private StorageReference storageProfilePictureRef;
    private String checker = "";

     private StorageTask uploadTask;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        storageProfilePictureRef= FirebaseStorage.getInstance().getReference().child("Profile pictures");


        profileimageview=findViewById(R.id.profile_image_settings_id);
        fullNameEdittxt=findViewById(R.id.settings_fullname_id);
        phoneEdittxt=findViewById(R.id.settings_phone_number_id);
        addressEdittxt=findViewById(R.id.settings_address_id);

        profilechangetxt=findViewById(R.id.profile_image_change_id);
        closetxt=findViewById(R.id.close_settings_id);
        updatetxt=findViewById(R.id.update_settings_id);

        userInfoDisplay(profileimageview,fullNameEdittxt,phoneEdittxt,addressEdittxt) ;

            closetxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();


                }
            });

            updatetxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                         if(checker.equals("clicked"))
                         {
                            userInfoSaved() ;

                         }
                         else
                         {
                              updateOnlyUserInfo();
                         }
                }
            });

            profilechangetxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checker="clicked";

                    CropImage.activity(imageUri) .setAspectRatio(1,1)
                            .start(SettingsActivity.this);


                }
            });







    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
           if(requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode==RESULT_OK && data!=null)
           {
                 CropImage.ActivityResult result=CropImage.getActivityResult(data);
                 imageUri=result.getUri();
                 profileimageview.setImageURI(imageUri);
           }

           else
           {
               Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
               startActivity(new Intent(SettingsActivity.this,SettingsActivity.class));
               finish();
           }

    }

    private void updateOnlyUserInfo() {

        DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("Users");

        HashMap<String, Object> userMap = new HashMap<>();
        userMap. put("name", fullNameEdittxt.getText().toString());
        userMap. put("address", addressEdittxt.getText().toString());
        userMap. put("phoneOrder", phoneEdittxt.getText().toString());

        ref.child(Prevalent.currentonlineUser.getPhone()).updateChildren(userMap);



        startActivity(new Intent(SettingsActivity.this, HomeActivity.class));
        Toast.makeText(SettingsActivity.this, "Profile Info update successfully.", Toast.LENGTH_SHORT).show();
        finish();



    }

    private void userInfoSaved() {
        if(TextUtils.isEmpty(fullNameEdittxt.getText().toString()))
        {
            Toast.makeText(this,"Name is mandatory",Toast.LENGTH_SHORT).show();


        }
        else if(TextUtils.isEmpty(addressEdittxt.getText().toString()))
        {
            Toast.makeText(this,"Address is mandatory",Toast.LENGTH_SHORT).show();


        }
       else  if(TextUtils.isEmpty(phoneEdittxt.getText().toString()))
        {
            Toast.makeText(this,"Number is mandatory",Toast.LENGTH_SHORT).show();


        }
        else if(checker.equals("clicked"))
        {

              uploadImage();

        }





    }

    private void uploadImage() {
        final ProgressDialog progressDialog=new ProgressDialog(this)  ;
         progressDialog.setTitle("Update profile");
         progressDialog.setMessage("Wait...");
         progressDialog.setCanceledOnTouchOutside(false);
         progressDialog.show();

         if(imageUri!=null)
         {
             final StorageReference fileRef=storageProfilePictureRef.child(Prevalent.currentonlineUser.getPhone()+".jpg");

             uploadTask=fileRef.putFile(imageUri);

             uploadTask.continueWithTask(new Continuation() {
                 @Override
                 public Object then(@NonNull Task task) throws Exception {
                    if(!task.isSuccessful())
                    {
                        throw  task.getException();

                    }
                     return fileRef.getDownloadUrl();


                 }
             }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                 @Override
                 public void onComplete(@NonNull Task<Uri> task) {
                           if(task.isSuccessful())
                           {
                               Uri downloadUrl=task.getResult();
                               myUrl=downloadUrl.toString();


                               DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("Users");

                               HashMap<String, Object> userMap = new HashMap<>();
                               userMap. put("name", fullNameEdittxt.getText().toString());
                               userMap. put("address", addressEdittxt.getText().toString());
                               userMap. put("phoneOrder", phoneEdittxt.getText().toString());
                               userMap. put("image", myUrl);
                               ref.child(Prevalent.currentonlineUser.getPhone()).updateChildren(userMap);

                               progressDialog.dismiss();

                               startActivity(new Intent(SettingsActivity.this, HomeActivity.class));
                               Toast.makeText(SettingsActivity.this, "Profile Info update successfully.", Toast.LENGTH_SHORT).show();
                               finish();

                           }

                           else
                           {
                               progressDialog.dismiss();
                               Toast.makeText(SettingsActivity.this, "Error.", Toast.LENGTH_SHORT).show();
                           }
                 }
             }) ;

         }
         else
         {
             Toast.makeText(this, "image is not selected.", Toast.LENGTH_SHORT).show();
         }


    }

    private void userInfoDisplay(final CircleImageView profileimageview, final EditText fullNameEdittxt, final EditText phoneEdittxt, final EditText addressEdittxt) {
        DatabaseReference UserRef= FirebaseDatabase.getInstance().getReference().child("Users").child(Prevalent.currentonlineUser.getPhone());
        UserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists())
                {
                    if (dataSnapshot.child("image").exists())
                    {
                        String image=dataSnapshot.child("image").getValue().toString();
                        String name=dataSnapshot.child("name").getValue().toString();
                        String phone=dataSnapshot.child("phone").getValue().toString();
                        String address=dataSnapshot.child("address").getValue().toString();

                        Picasso.get().load(image).into(profileimageview);
                        fullNameEdittxt.setText(name);
                        phoneEdittxt.setText(phone);
                        addressEdittxt.setText(address);




                    }
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        }) ;

    }
}
