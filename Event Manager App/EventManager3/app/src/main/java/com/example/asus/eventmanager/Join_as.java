package com.example.asus.eventmanager;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

                openDialog();







            }
        });
    }

    public void openDialog(){






    }

}
