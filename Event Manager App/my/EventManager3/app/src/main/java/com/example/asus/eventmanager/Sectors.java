package com.example.asus.eventmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Sectors extends AppCompatActivity {


    private TextView  t1;
    private TextView  t2;
    private TextView  t3;
    private TextView  t4;
    private TextView  t5;
    private TextView  t6;
    private TextView  t7;
    private TextView  t8;
    private TextView  t9;
    private TextView  t10;
    private TextView  t11;
    private TextView  t12;

    private String  s_1,s_2,s_3,s_4,s_5,s_6,s_7,s_8,s_9,s_10,s_11,s_12;



    private Button sectors_ok_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sectors);


        t1=(TextView)findViewById(R.id.s1_id);
        t2=(TextView)findViewById(R.id.s2_id);
        t3=(TextView)findViewById(R.id.s3_id);
        t4=(TextView)findViewById(R.id.s4_id);
        t5=(TextView)findViewById(R.id.s5_id);
        t6=(TextView)findViewById(R.id.s6_id);
        t7=(TextView)findViewById(R.id.s7_id);
        t8=(TextView)findViewById(R.id.s8_id);
        t9=(TextView)findViewById(R.id.s9_id);
        t10=(TextView)findViewById(R.id.s10_id);
        t11=(TextView)findViewById(R.id.s11_id);
        t12=(TextView)findViewById(R.id.s12_id);


        s_1=getIntent().getExtras().getString("val1");
        s_2=getIntent().getExtras().getString("val2");
        s_3=getIntent().getExtras().getString("val3");
        s_4=getIntent().getExtras().getString("val4");
        s_5=getIntent().getExtras().getString("val5");
        s_6=getIntent().getExtras().getString("val6");
        s_7=getIntent().getExtras().getString("val7");
        s_8=getIntent().getExtras().getString("val8");
        s_9=getIntent().getExtras().getString("val9");
        s_10=getIntent().getExtras().getString("val10");
        s_11=getIntent().getExtras().getString("val11");
        s_12=getIntent().getExtras().getString("val12");


        t1.setText(s_1);
        t2.setText(s_2);
        t3.setText(s_3);
        t4.setText(s_4);
        t5.setText(s_5);
        t6.setText(s_6);
        t7.setText(s_7);
        t8.setText(s_8);
        t9.setText(s_9);
        t10.setText(s_10);
        t11.setText(s_11);
        t12.setText(s_12);


        sectors_ok_btn=(Button)findViewById(R.id.sectors_ok_btn_id);

        sectors_ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Sectors.this,EventPage.class);
                startActivity(in);

            }
        });




    }
}
