package com.example.asus.networkingproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class Splashscreen extends AppCompatActivity {

    private ProgressBar progressBar;
    private int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splashscreen);
        progressBar=(ProgressBar)findViewById(R.id.prober_id);

        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                dowork();
                startapp();



            }
        });

        thread.start();



    }

    public  void dowork() {

        for (progress = 20; progress <= 100; progress = progress + 20) {


            try {
                Thread.sleep(1000);
                progressBar.setProgress(progress);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

    public  void startapp(){
        Intent intent=new Intent(Splashscreen.this,MainActivity.class);
        startActivity(intent);
        finish();



    }
}




