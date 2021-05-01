package com.example.asus.eventmanager;



import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class nav_sector extends AppCompatActivity {


    private TextView tx1;
    private TextView tx2;
    private TextView tx3;
    private TextView tx4;
    private TextView tx5;
    private TextView tx6;
    private TextView tx7;
    private TextView tx8;
    private TextView tx9;
    private TextView tx10;
    private TextView tx11;
    private TextView tx12;


    private  String sc1,sc2,sc3,sc4,sc5,sc6,sc7,sc8,sc9,sc10,sc11,sc12;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_sector);

        tx1=findViewById(R.id.se1_id);
        tx2=findViewById(R.id.se2_id);
        tx3=findViewById(R.id.se3_id);
        tx4=findViewById(R.id.se4_id);
        tx5=findViewById(R.id.se5_id);
        tx6=findViewById(R.id.se6_id);
        tx7=findViewById(R.id.se7_id);
        tx8=findViewById(R.id.se8_id);
        tx9=findViewById(R.id.se9_id);
        tx10=findViewById(R.id.se10_id);
        tx11=findViewById(R.id.se11_id);
        tx12=findViewById(R.id.se12_id);


        sc1=getIntent().getExtras().getString("val1");
        sc2=getIntent().getExtras().getString("val2");
        sc3=getIntent().getExtras().getString("val3");
        sc4=getIntent().getExtras().getString("val4");
        sc5=getIntent().getExtras().getString("val5");
        sc6=getIntent().getExtras().getString("val6");
        sc7=getIntent().getExtras().getString("val7");
        sc8=getIntent().getExtras().getString("val8");
        sc9=getIntent().getExtras().getString("val9");
        sc10=getIntent().getExtras().getString("val10");
        sc11=getIntent().getExtras().getString("val11");
        sc12=getIntent().getExtras().getString("val12");










    }
}
