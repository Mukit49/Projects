package com.example.jfisrat.eventmanagerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Sector_head extends AppCompatActivity {

   private Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sector_head);
        b1=(Button)findViewById(R.id.done1_id);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Sector_head.this,HomePageMenu.class);
                startActivity(in);
            }
        });
    }
}
