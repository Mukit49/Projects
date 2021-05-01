package com.example.asus.software_project;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.asus.software_project.Model.Products;
import com.example.asus.software_project.Prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ProductDetailsActivity extends AppCompatActivity {
    private FloatingActionButton addtoCart;
    private ImageView productImage;
    private ElegantNumberButton numberButton;
    private TextView productPrice,productDescription,productName;
    private String  productId="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        productId=getIntent().getStringExtra("pid");

        addtoCart=findViewById(R.id.add_product_to_cart_id);

        productImage=findViewById(R.id.product_image_details_id);
        numberButton=findViewById(R.id.number_product_btn_id);
        productPrice=findViewById(R.id.product_price_details_id);
        productDescription=findViewById(R.id.product_description_details_id);
        productName=findViewById(R.id.product_name_details_id);


        getProductDetails(productId);

        addtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addingToCatList();

            }
        });



    }

    private void addingToCatList() {
        String saveCurrentTime,saveCurrentDate;
        Calendar calForDate=  Calendar.getInstance();
        SimpleDateFormat currentDate= new SimpleDateFormat("MMM DD,YYYY");
         saveCurrentDate=currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime= new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime=currentDate.format(calForDate.getTime());

        final DatabaseReference cartListRef=  FirebaseDatabase.getInstance().getReference().child("Cart List");
        final HashMap<String,Object> cartMap=new HashMap<>();
         cartMap.put("pid",productId);
         cartMap.put("pname",productName.getText().toString());
         cartMap.put("price",productPrice.getText().toString());
         cartMap.put("date",saveCurrentDate);
         cartMap.put("time",saveCurrentTime);
         cartMap.put("quantity",numberButton.getNumber());
         cartMap.put("discount", "");

         cartListRef.child("User view").child(Prevalent.currentonlineUser.getPhone()).child("Products").child(productId)
                 .updateChildren(cartMap).addOnCompleteListener(new OnCompleteListener<Void>() {
             @Override
             public void onComplete(@NonNull Task<Void> task) {
                 if(task.isSuccessful())
                 {
                     cartListRef.child("Admin view").child(Prevalent.currentonlineUser.getPhone()).child("Products").child(productId)
                             .updateChildren(cartMap)
                             .addOnCompleteListener(new OnCompleteListener<Void>() {
                                 @Override
                                 public void onComplete(@NonNull Task<Void> task) {
                                       if (task.isSuccessful())
                                       {
                                           Toast.makeText(ProductDetailsActivity.this,"Added to cart list",Toast.LENGTH_SHORT).show();
                                           Intent intent= new Intent(ProductDetailsActivity.this,HomeActivity.class);
                                           startActivity(intent);
                                       }
                                 }
                             });


                 }


             }
         }) ;






    }

    private void getProductDetails(String productId) {

        DatabaseReference productRef= FirebaseDatabase.getInstance().getReference().child("Products");
        productRef.child(productId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                     if(dataSnapshot.exists())
                     {
                         Products products=dataSnapshot.getValue(Products.class);

                         productName.setText(products.getPname());
                         productPrice.setText(products.getPrice());
                         productDescription.setText(products.getDescription());

                         Picasso.get().load(products.getImage()).into(productImage);



                     }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        }) ;

    }
}
