package com.example.asus.software_project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.software_project.Model.Users;
import com.example.asus.software_project.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {
   private Button Login_btn;
   private EditText InputNumber,InputPassword;
   private ProgressDialog dialog;
   private CheckBox checkBox;
   private TextView AdminLink,NotAdminLink;



   private String    parentDbname="Users";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Login_btn= findViewById(R.id.login_btn_id);
        InputNumber=findViewById(R.id.login_phone_number_txt);
        InputPassword=findViewById(R.id.login__password_txt);
        checkBox=findViewById(R.id.remember_me_check_box_id);

        AdminLink=findViewById(R.id.admin_panel_id);
        NotAdminLink=findViewById(R.id.non_admin_panel_id);



          Paper.init(this);
        dialog=new ProgressDialog(this);




        Login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();

            }
        });


        AdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login_btn.setText("Login Admin");
                AdminLink.setVisibility(View.INVISIBLE);
                NotAdminLink.setVisibility(View.VISIBLE);
                parentDbname="Admins";

            }
        });

        NotAdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login_btn.setText("Login");
                AdminLink.setVisibility(View.VISIBLE);
                NotAdminLink.setVisibility(View.INVISIBLE);
                parentDbname="Users";



            }
        });


    }



    private void loginUser() {

              String phone_number=InputNumber.getText().toString();
              String password=InputPassword.getText().toString();
              if (TextUtils.isEmpty(phone_number))
              {
              Toast.makeText(this, "Please Write your phone number", Toast.LENGTH_SHORT).show();
        }
         else if (TextUtils.isEmpty(password))
         {
            Toast.makeText(this, "Please Write your password", Toast.LENGTH_SHORT).show();
        }

        else
              {
                  dialog.setTitle("Login Account");
                  dialog.setMessage("Wait...");
                  dialog.setCanceledOnTouchOutside(false);
                  dialog.show();

                  AllowAccessToAccount(phone_number,password);


              }



    }

    private void AllowAccessToAccount(final String phone_number, final String password) {
         if(checkBox.isChecked())
         {

             Paper.book().write(Prevalent.UserPhoneKey,phone_number);
             Paper.book().write(Prevalent.UserPasswordKey,password);

         }
        final DatabaseReference RootRef;
        RootRef= FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("OUTSIDE", dataSnapshot.toString());
                if(dataSnapshot.exists()) {
                    Users userData = dataSnapshot.child(parentDbname).child(phone_number).getValue(Users.class);
                    if (userData.getPhone().equals(phone_number)){
                        if (userData.getPassword().equals(password))
                        {
                              if (parentDbname.equals("Admins"))
                              {
                                  Log.i("INSIDE", userData.getName());
                                  Toast.makeText(LoginActivity.this,"Welcome Admin...",Toast.LENGTH_SHORT).show();
                                  dialog.dismiss();

                                  Intent intent=new Intent(LoginActivity.this,AdminCategoryActivity.class);
                                  Prevalent.currentonlineUser=userData ;
                                  startActivity(intent);
                              }

                              else
                              {
                                  Toast.makeText(LoginActivity.this,"Logged in successfully...",Toast.LENGTH_SHORT).show();
                                  dialog.dismiss();

                                  Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
                                  Prevalent.currentonlineUser=userData ;

                                  startActivity(intent);


                              }



                        }



                    }
                }

                else
                {

                  Toast.makeText(LoginActivity.this,"Account with this "+ phone_number + " number not exists",Toast.LENGTH_SHORT).show();
                  dialog.dismiss();



                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
