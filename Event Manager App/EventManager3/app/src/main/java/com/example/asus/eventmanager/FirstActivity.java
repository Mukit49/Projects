package com.example.asus.eventmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity {


    private Button sign_in_btn;
    private Button sign_up_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        sign_in_btn=(Button)findViewById(R.id.signin_btn_id);
        sign_up_btn=(Button)findViewById(R.id.signup_btn_id);

        sign_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FirstActivity.this,SignUp.class);
                startActivity(intent);


            }
        });
         sign_in_btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 Intent intent=new Intent(FirstActivity.this,Event_creat.class);
                 startActivity(intent);



             }
         });

    }
}
