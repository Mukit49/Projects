package com.example.jfisrat.eventmanagerapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    private Button registerButton;
    private Toolbar toolbar;

    private TextView heading,username,email,password,birthday,male,female;
    private EditText userNameEditText,emailEditText,passEditText;
    private RadioGroup radioGroup;
    private RadioButton genderButton;
    private String[] day,month,year;
    private Spinner daySpinner,monthSpinner,yearSpinner;
    Typeface typeface1,typeface2;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth=FirebaseAuth.getInstance();
        toolbar=findViewById(R.id.registerToolbarId);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //finding text
        heading=findViewById(R.id.headingId);
        username=findViewById(R.id.R_UsernameId);
        email=findViewById(R.id.R_emailId);
        password=findViewById(R.id.R_passId);

        birthday=findViewById(R.id.R_BirthdayId);

        //finding edit text
        userNameEditText=findViewById(R.id.R_UsernameEditId);
        emailEditText=findViewById(R.id.R_EmailEditId);
        passEditText=findViewById(R.id.R_passEditTextId);

        //finding radio button
        radioGroup=findViewById(R.id.radioGroupId);
        registerButton=findViewById(R.id.R_RegisterButtonId);

        //finding spinner
        daySpinner=findViewById(R.id.DaySpinnerId);
        monthSpinner =findViewById(R.id.MonthSpinnerId);
        yearSpinner=findViewById(R.id.YearSpinnerId);

        //typeface
        typeface1=Typeface.createFromAsset(getAssets(),"font/text_font.ttf");
        typeface2=Typeface.createFromAsset(getAssets(),"font/button_font.ttf");

        //set typeface
        heading.setTypeface(typeface2);
        username.setTypeface(typeface1);
        email.setTypeface(typeface1);
        password.setTypeface(typeface1);

        birthday.setTypeface(typeface1);
        registerButton.setTypeface(typeface2);

        //for spinner
        day=getResources().getStringArray(R.array.Day);
        month=getResources().getStringArray(R.array.Month);
        year=getResources().getStringArray(R.array.Year);

        ArrayAdapter<String> dayadapter=new ArrayAdapter<String>(this,R.layout.sample_view,R.id.sampleViewId,day);
        ArrayAdapter<String> monthadapter=new ArrayAdapter<String>(this,R.layout.sample_view,R.id.sampleViewId,month);
        ArrayAdapter<String> yearadapter=new ArrayAdapter<String>(this,R.layout.sample_view,R.id.sampleViewId,year);
        daySpinner.setAdapter(dayadapter);
        monthSpinner.setAdapter(monthadapter);
        yearSpinner.setAdapter(yearadapter);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = userNameEditText.getText().toString().trim();
                String remail = emailEditText.getText().toString().trim();
                String rpassword = passEditText.getText().toString().trim();
                String dateOfBirth = daySpinner.getSelectedItem().toString() + "/" + monthSpinner.getSelectedItem().toString() + "/" + yearSpinner.getSelectedItem().toString();
                int selectedId = radioGroup.getCheckedRadioButtonId();
                genderButton = findViewById(selectedId);
                String gender = genderButton.getText().toString().trim();

                if (TextUtils.isEmpty(remail)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(rpassword)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }
                //if (TextUtils.isEmpty(dateOfBirth)) {
                  //  Toast.makeText(getApplicationContext(), "Select your date of birth!", Toast.LENGTH_SHORT).show();
                   // return;
               // }
                if (TextUtils.isEmpty(gender)) {
                    Toast.makeText(getApplicationContext(), "Select your gender!", Toast.LENGTH_SHORT).show();
                    return;
                }


                //create user
                auth.createUserWithEmailAndPassword(remail, rpassword)
                        .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(Register.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();

                                if (!task.isSuccessful()) {
                                    Toast.makeText(Register.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_LONG).show();
                                } else {
                                    startActivity(new Intent(Register.this, HomePageMenu.class));
                                    finish();
                                }
                            }
                        });


                }

        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
