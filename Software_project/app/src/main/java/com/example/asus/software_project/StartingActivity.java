package com.example.asus.software_project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.asus.software_project.Model.Users;
import com.example.asus.software_project.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class StartingActivity extends AppCompatActivity {


    private Button login_btn, registerbtn;


    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_btn = findViewById(R.id.main_login_now_btn_id);
        registerbtn = findViewById(R.id.register_btn_id);
        dialog=new ProgressDialog(this);

        Paper.init(this);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(StartingActivity.this, LoginActivity.class);
                startActivity(intent);

                
            }
        });

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(StartingActivity.this, RegisterActivity.class);
                startActivity(in);

            }
        });


        String UserPhoneKey  =Paper.book().read(Prevalent.UserPhoneKey)  ;
        String UserPasswordKey=Paper.book().read(Prevalent.UserPasswordKey) ;

         if(UserPasswordKey!=null && UserPhoneKey!="")
         {
             if(!TextUtils.isEmpty(UserPhoneKey) && !TextUtils.isEmpty(UserPasswordKey))
             {
                 checkUserStatus();
                 AllowAccess(UserPhoneKey,UserPasswordKey);
                 dialog.setTitle("Already Loggedin");
                 dialog.setMessage("Wait...");
                 dialog.setCanceledOnTouchOutside(false);
                 dialog.show();

             }

         }


    }

    private void checkUserStatus() {

    }

    private void AllowAccess(final String phone_number, final String password) {

        final DatabaseReference RootRef;
        RootRef= FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.child("Users").child(phone_number).exists()) {

                    Users userData=dataSnapshot.child("Users").child(phone_number).getValue(Users.class);
                    if (userData.getPhone().equals(phone_number)){
                        if (userData.getPassword().equals(password)){
                            Toast.makeText(StartingActivity.this,"Logged in successfully...",Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            Prevalent.currentonlineUser = userData;
                            Intent intent=new Intent(StartingActivity.this,HomeActivity.class);
                            startActivity(intent);


                        }

                        else
                        {
                            dialog.dismiss();
                            Toast.makeText(StartingActivity.this,"password is incorrect",Toast.LENGTH_SHORT).show();

                        }

                    }
                }

                else
                {

                    Toast.makeText(StartingActivity.this,"Account with this "+ phone_number + " number not exists",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();



                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {



            }
        });


    }


}
