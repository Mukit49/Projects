package com.example.asus.software_project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AdminaddNewProductActivity extends AppCompatActivity {

    private String CategoryName,p_description,p_price,p_name,saveCurrentDate,saveCurrent_time;

    private Button AddnewProduct_btn;
    private EditText product_name,product_description,producr_price;

    private ImageView product_image;
    private static final int   GalleryPick=1;


    private Uri imageUri;

    private String productRandomKey,downloadImageUrl;


    private StorageReference ProductImageRef;
    private DatabaseReference ProductRef;
    private ProgressDialog dialog;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminadd_new_product);

        CategoryName=getIntent().getExtras().get("category").toString();
        ProductRef=FirebaseDatabase.getInstance().getReference().child("Products");
        dialog=new ProgressDialog(this);




         ProductImageRef= FirebaseStorage.getInstance().getReference().child("Product Images") ;

        product_image=findViewById(R.id.select_product_image_id);
        AddnewProduct_btn=findViewById(R.id.addnewproduct_id);
        product_name=findViewById(R.id.product_name_id);
        product_description=findViewById(R.id.product_description_id);
        producr_price=findViewById(R.id.product_price_id);




        product_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   Opengallery();
            }
        });

        AddnewProduct_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateProduction();
            }
        });
        Toast.makeText(this,CategoryName,Toast.LENGTH_SHORT).show();






    }

    private void ValidateProduction() {

                     p_description=product_description.getText().toString();
                     p_price=producr_price.getText().toString();
                     p_name=product_name.getText().toString();

                    if(imageUri==null){

                        Toast.makeText(this,"Image required",Toast.LENGTH_SHORT).show();


                    }
                    else if (TextUtils.isEmpty(p_description))
                    {
                        Toast.makeText(this,"please write descriptin",Toast.LENGTH_SHORT).show();

                    }
                    else if (TextUtils.isEmpty(p_price))
                    {
                        Toast.makeText(this,"please write price",Toast.LENGTH_SHORT).show();

                    }
                    else if (TextUtils.isEmpty(p_name))
                    {
                        Toast.makeText(this,"please write product name",Toast.LENGTH_SHORT).show();

                    }

                    else

                    {

                        StoreProductInformation();
                    }




    }

    private void StoreProductInformation() {

        dialog.setTitle("Adding new product");
        dialog.setMessage("Wait...");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        Calendar calendar=Calendar.getInstance();

        SimpleDateFormat currentDate= new SimpleDateFormat("MMM DD,YYYY")  ;
        saveCurrentDate=currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime=new SimpleDateFormat("HH:mm:ss a") ;
        saveCurrent_time=currentTime.format(calendar.getTime());


        productRandomKey=  saveCurrentDate+saveCurrent_time;


        final StorageReference  filePath=ProductImageRef.child(imageUri.getLastPathSegment()+productRandomKey + ".jpg");

        final UploadTask uploadTask=filePath.putFile(imageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                 String message=e.toString();
                 Toast.makeText(AdminaddNewProductActivity.this,"Error: ",Toast.LENGTH_SHORT).show();
                 dialog.dismiss();

            }
        }) .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)

            {

                Toast.makeText(AdminaddNewProductActivity.this,"Image Uploaded successfully ",Toast.LENGTH_SHORT).show();
                Task<Uri>uriTask=uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception
                    {
                        if(!task.isSuccessful())
                        {
                            throw task.getException();



                        }

                        downloadImageUrl=filePath.getDownloadUrl().toString();
                        return filePath.getDownloadUrl();




                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task)
                    {

                          if(task.isSuccessful())
                          {
                              downloadImageUrl=task.getResult().toString();
                              Toast.makeText(AdminaddNewProductActivity.this,"Product Image saved successfully",Toast.LENGTH_SHORT).show();

                              SaveProDuctInfoToDatabase();

                          }

                    }
                }) ;
            }

        });



    }

    private void SaveProDuctInfoToDatabase() {

        HashMap<String,Object>productMap=new HashMap<>();
        productMap.put("pid",productRandomKey);
        productMap.put("date",saveCurrentDate);
        productMap.put("time",saveCurrent_time);
        productMap.put("description",p_description);
        productMap.put("image",downloadImageUrl);
        productMap.put("category",CategoryName);
        productMap.put("price",p_price);
        productMap.put("pname",p_name);

        ProductRef.child(productRandomKey).updateChildren(productMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful())
                {
                    Intent intent=new Intent(AdminaddNewProductActivity.this,AdminCategoryActivity.class) ;
                     dialog.dismiss();
                    Toast.makeText(AdminaddNewProductActivity.this,"Product id add successfully",Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
                else
                {
                    dialog.dismiss();
                    String message=task.getException().toString();
                    Toast.makeText(AdminaddNewProductActivity.this,"Error :"+message,Toast.LENGTH_SHORT).show();
                }

            }
        }) ;


    }

    private void Opengallery() {
        Intent galleryIntent=new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*") ;
        startActivityForResult(galleryIntent,GalleryPick);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK && data!=null && requestCode == GalleryPick)
        {
              imageUri=data.getData();

              product_image.setImageURI(imageUri);



        }
    }
}


