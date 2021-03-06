package com.example.jfisrat.eventmanagerapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Splash_screen extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView textView1,textView2;
    private Typeface typeface;
    int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        textView1=findViewById(R.id.eventNameId);
        textView2=findViewById(R.id.event_manage_NameId);

        typeface=Typeface.createFromAsset(getAssets(),"font/name_font.ttf");

        textView1.setTypeface(typeface);
        textView2.setTypeface(typeface);

        progressBar =  findViewById(R.id.progressBarId);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                load();
                goLoginPage();
            }
        });
        thread.start();

    }

    public void load() {
        for (progress = 20; progress <= 100; progress = progress + 20){
            try {
                Thread.sleep(1000);
                progressBar.setProgress(progress);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void goLoginPage() {
        Intent intent = new Intent(Splash_screen.this, LoginPage.class);
        startActivity(intent);
        finish();
    }


}


