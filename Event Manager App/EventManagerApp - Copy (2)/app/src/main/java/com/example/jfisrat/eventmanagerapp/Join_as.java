package com.example.jfisrat.eventmanagerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Join_as extends AppCompatActivity {

    private Button join_as_sector_head_btn;
    private Button join_as_volunteer_btn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_as);

        join_as_sector_head_btn=(Button)findViewById(R.id.join_as_sector_head_btn_id);
        join_as_volunteer_btn=(Button)findViewById(R.id.join_as_volunteer_btn_id);

        join_as_sector_head_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //openDialog();
                Intent in2=new Intent(Join_as.this,Sector_head.class);
                startActivity(in2);


            }
        });

        join_as_volunteer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Join_as.this,Sector_head.class));
            }
        });
    }

    public void openDialog(){






    }

}
