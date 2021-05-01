package com.example.jfisrat.eventmanagerapp;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditProfile extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView name,varsityName,mobileNumber,occupation,address,department;
    private Button saveButton;
    private EditText nameEdit,varsityEdit,mobileEdit,occupationEdit,addressEdit,departmentEdit;
    private Typeface typeface1,typeface2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        toolbar=findViewById(R.id.editProfileToolbarId);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name=findViewById(R.id.nameTextId);
        varsityName=findViewById(R.id.UniversityNameTextId);
        mobileNumber=findViewById(R.id.mobileNumberTextId);
        occupation=findViewById(R.id.occupationTextId);
        address=findViewById(R.id.addressTextId);
        department=findViewById(R.id.deptNameId);
        nameEdit=findViewById(R.id.editNameId);
        varsityEdit=findViewById(R.id.varsityEditId);
        mobileEdit=findViewById(R.id.mobileNumberEditId);
        occupationEdit=findViewById(R.id.occupationEditId);
        addressEdit=findViewById(R.id.addressEditTextId);
        departmentEdit=findViewById(R.id.deptNameEditId);

        typeface1=Typeface.createFromAsset(getAssets(),"font/text_font.ttf");
        typeface2=Typeface.createFromAsset(getAssets(),"font/button_font.ttf");

        name.setTypeface(typeface1);
        varsityName.setTypeface(typeface1);
        mobileNumber.setTypeface(typeface1);
        occupation.setTypeface(typeface1);
        address.setTypeface(typeface1);
        department.setTypeface(typeface1);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
