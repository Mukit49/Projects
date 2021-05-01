package com.example.jfisrat.eventmanagerapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassword extends AppCompatActivity {

    private EditText inputEmail;
    private TextView forgetText,instructionText;
    private Button resetButton,backButton;
    private FirebaseAuth auth;
    Typeface typeface1,typeface2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        auth = FirebaseAuth.getInstance();

        inputEmail = (EditText) findViewById(R.id.email);
        resetButton = (Button) findViewById(R.id.btn_reset_password);
        backButton = (Button) findViewById(R.id.btn_back);
        forgetText=findViewById(R.id.forgetPassId);
        instructionText=findViewById(R.id.instructionTextId);

        //auth = FirebaseAuth.getInstance();
        //typeface
        typeface1=Typeface.createFromAsset(getAssets(),"font/text_font.ttf");
        typeface2=Typeface.createFromAsset(getAssets(),"font/button_font.ttf");

        forgetText.setTypeface(typeface2);
        instructionText.setTypeface(typeface1);
        resetButton.setTypeface(typeface2);
        backButton.setTypeface(typeface2);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplication(), "Enter your registered email id", Toast.LENGTH_SHORT).show();
                    return;
                }


                auth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(ForgetPassword.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(ForgetPassword.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                                }


                            }
                        });
            }
        });
    }

}
