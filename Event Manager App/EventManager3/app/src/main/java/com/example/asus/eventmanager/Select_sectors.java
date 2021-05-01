package com.example.asus.eventmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Select_sectors extends AppCompatActivity {

    private Button select_sector_done_btn;
    private Button add_more_sector_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_sectors);


        select_sector_done_btn=(Button)findViewById(R.id.select_sectors_done_btn_id);
        add_more_sector_btn=(Button)findViewById(R.id.add_more_sectors_btn_id);


        select_sector_done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Select_sectors.this,EventPage.class);
                startActivity(intent);

            }
        });
    }
}
