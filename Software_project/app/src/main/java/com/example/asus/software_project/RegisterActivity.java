package com.example.asus.software_project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private Button create_acount_btn;
    private EditText username_txt, pass_txt, phone_number_txt;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_activity);


        create_acount_btn = findViewById(R.id.register_page_btn_id);
        username_txt = findViewById(R.id.register__username_txt_id);
        pass_txt = findViewById(R.id.register__password_txt_id);
        phone_number_txt = findViewById(R.id.register_phone_number_txt_id);
        dialog = new ProgressDialog(this);
        Log.i("INSIDE", "HH");

        create_acount_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                creatAccount();


            }
        });

    }

    private void creatAccount() {

        String name = username_txt.getText().toString();
        String phone_number = phone_number_txt.getText().toString();
        String password = pass_txt.getText().toString();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Please Write your name", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(phone_number)) {
            Toast.makeText(this, "Please Write your phone number", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please Write your password", Toast.LENGTH_SHORT).show();
        } else

        {
            dialog.setTitle("Create Account");
            dialog.setMessage("Wait...");
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            ValidatephoneNumber(name,phone_number,password);
        }


    }

    private void ValidatephoneNumber(final String name, final String phone_number, final String password) {
        final DatabaseReference RootRef;
        RootRef= FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                     if(!(dataSnapshot.child("User").child(phone_number).exists())) {
                         HashMap<String,Object> userdataMap=new HashMap<>();
                         userdataMap.put("phone",phone_number);
                         userdataMap.put("password",password);
                         userdataMap.put("name",name);

                         RootRef.child("Users").child(phone_number).updateChildren(userdataMap). addOnCompleteListener(new OnCompleteListener<Void>() {
                             @Override
                             public void onComplete(@NonNull Task<Void> task) {

                                 if(task.isSuccessful()){

                                     Toast.makeText(RegisterActivity.this,"Congrats",Toast.LENGTH_SHORT).show();
                                     dialog.dismiss();

                                     Intent intent = new Intent(RegisterActivity.this, StartingActivity.class);
                                     startActivity(intent);

                                 }

                                 else
                                 {
                                      dialog.dismiss();
                                     Toast.makeText(RegisterActivity.this,"Network Error:please try again",Toast.LENGTH_SHORT).show();
                                 }



                             }
                         }) ;



                     }

                     else
                     {
                           Toast.makeText(RegisterActivity.this,"This "+phone_number+ "already exists",Toast.LENGTH_SHORT) .show();
                           dialog.dismiss();
                           Toast.makeText(RegisterActivity.this,"Please try again using another phone number",Toast.LENGTH_SHORT).show();
                         Intent intent = new Intent(RegisterActivity.this, StartingActivity.class);
                         startActivity(intent);


                     }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
