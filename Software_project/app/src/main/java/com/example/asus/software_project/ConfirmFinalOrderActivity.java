package com.example.asus.software_project;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus.software_project.Prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ConfirmFinalOrderActivity extends AppCompatActivity {

       private EditText nametxt,phonetxt,addresstxt,citytxt;
       private Button confirmbtn;

       private String totalamount="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_final_order);

        totalamount=getIntent().getStringExtra("Total Price");

        Toast.makeText(this,"Total price= "+totalamount,Toast.LENGTH_SHORT).show();

        nametxt=findViewById(R.id.order_name_txt_id);
        phonetxt=findViewById(R.id.order_phone_txt_id);
        addresstxt=findViewById(R.id.order_homeaddress_txt_id);
        citytxt=findViewById(R.id.order_city_txt_id);

        confirmbtn=findViewById(R.id.confirm_order_btn_id);

        confirmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ConfirmFinalOrderActivity.this,AdminCategoryActivity.class);
                startActivity(intent);

                Check();
            }
        });



    }

    private void Check() {
        if(TextUtils.isEmpty(nametxt.getText().toString()))
        {
         Toast.makeText(this,"Please provide your full name",Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(phonetxt.getText().toString()))
        {
            Toast.makeText(this,"Please provide your phone number",Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(addresstxt.getText().toString()))
        {
            Toast.makeText(this,"Please provide your address",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(citytxt.getText().toString()))
        {
            Toast.makeText(this,"Please provide your city name",Toast.LENGTH_SHORT).show();
        }
        
        else
        {
            confirmOrder();
        }




    }

    private void confirmOrder() {
        final String saveCurrentDate,saveCurrentTime;
        Calendar calForDate=  Calendar.getInstance();
        SimpleDateFormat currentDate= new SimpleDateFormat("MMM DD,YYYY");
        saveCurrentDate=currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime= new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime=currentDate.format(calForDate.getTime());


        final DatabaseReference orderRef = FirebaseDatabase.getInstance().getReference().child("Orders");

        HashMap<String,Object> orderMap=new HashMap<>();
        orderMap.put("totalamount",totalamount);
        orderMap.put("name",nametxt.getText().toString());
        orderMap.put("phone",phonetxt.getText().toString());
        orderMap.put("address",addresstxt.getText().toString());
        orderMap.put("city",citytxt.getText().toString());
        orderMap.put("date",saveCurrentDate);
        orderMap.put("time",saveCurrentTime);

        orderMap.put("state","not shipped");
        orderRef.updateChildren(orderMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    FirebaseDatabase.getInstance().getReference().child("Cart List")
                            .child("User view")
                            .child(Prevalent.currentonlineUser.getPhone())
                            .removeValue()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(ConfirmFinalOrderActivity.this,"Final order has been placed successfully",Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(ConfirmFinalOrderActivity.this,HomeActivity.class);
                                         intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                         startActivity(intent);
                                         finish();

                                    }

                                }
                            }) ;


                }

            }
        }) ;




    }
}
