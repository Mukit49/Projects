package com.example.jfisrat.eventmanagerapp;

import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class profile_activity extends AppCompatActivity {
    private Toolbar toolbar;
    private ImageView imageView;
    private Button editButton;
    private TextView user,name,dept,varsity,mail,contact,mobile,addTitle,address;
    FirebaseAuth auth;
    Uri image;
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
        user=findViewById(R.id.P_usernameId);
        editButton=findViewById(R.id.editButtonId);
        name=findViewById(R.id.P_nameId);
        dept=findViewById(R.id.P_deptId);
        varsity=findViewById(R.id.P_varsityId);
        contact=findViewById(R.id.P_contactId);
        mail=findViewById(R.id.P_mailId);
        mobile=findViewById(R.id.P_mobileId);
        addTitle=findViewById(R.id.P_addressTitleId);
        address=findViewById(R.id.P_addressId);


        typeface1=Typeface.createFromAsset(getAssets(),"font/text_font.ttf");
        typeface2=Typeface.createFromAsset(getAssets(),"font/button_font.ttf");

        user.setTypeface(typeface2);
        name.setTypeface(typeface1);
        dept.setTypeface(typeface1);
        varsity.setTypeface(typeface1);
        contact.setTypeface(typeface2);
        mail.setTypeface(typeface1);
        mobile.setTypeface(typeface1);
        addTitle.setTypeface(typeface2);
        address.setTypeface(typeface1);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
