package com.example.jfisrat.eventmanagerapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity {

    private Button loginButton,registerButon,forgotButton;
    private TextView forgetPass,username,password;
    private EditText emailEditText,passEditText;
    private ImageView welcome;


    private FirebaseAuth auth;

    Typeface typeface1,typeface2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(LoginPage.this, HomePageMenu.class));
            finish();
        }

        loginButton=(Button)findViewById(R.id.loginButtonId);
        registerButon=findViewById(R.id.registerButtonId);
        welcome=findViewById(R.id.welcomeId);
        forgetPass=findViewById(R.id.forget_password_id);
        emailEditText=findViewById(R.id.login_edit_textId);
        passEditText=findViewById(R.id.passEditTextId);
        username=findViewById(R.id.login_nameId);
        password=findViewById(R.id.passwordId);
        forgotButton=findViewById(R.id.forget_password_id);

        typeface1=Typeface.createFromAsset(getAssets(),"font/text_font.ttf");
        typeface2=Typeface.createFromAsset(getAssets(),"font/button_font.ttf");

        username.setTypeface(typeface1);
        password.setTypeface(typeface1);
        loginButton.setTypeface(typeface2);
        registerButon.setTypeface(typeface2);
        forgotButton.setTypeface(typeface1);


        forgotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(LoginPage.this,ForgetPassword.class));
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                final String password = passEditText.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                //authenticate user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginPage.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        passEditText.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(LoginPage.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Intent intent = new Intent(LoginPage.this, HomePageMenu.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });

        registerButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(LoginPage.this,Register.class);
                startActivity(intent1);
            }
        });

    }
}
