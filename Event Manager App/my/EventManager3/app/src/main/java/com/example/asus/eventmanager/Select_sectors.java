package com.example.asus.eventmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Select_sectors extends AppCompatActivity {

    private Button select_sector_done_btn;
    private Button add_more_sector_btn;


    private EditText  txt1;
    private EditText  txt2;
    private EditText  txt3;
    private EditText  txt4;
    private EditText  txt5;
    private EditText  txt6;
    private EditText  txt7;
    private EditText  txt8;
    private EditText  txt9;
    private EditText  txt10;
    private EditText  txt11;
    private EditText  txt12;


    private  String sec1,sec2,sec3,sec4,sec5,sec6,sec7,sec8,sec9,sec10,sec11,sec12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_sectors);


        select_sector_done_btn=(Button)findViewById(R.id.select_sectors_done_btn_id);
        add_more_sector_btn=(Button)findViewById(R.id.add_more_sectors_btn_id);


        txt1=(EditText)findViewById(R.id.sector_1_id);
        txt2=(EditText)findViewById(R.id.sector_2_id);
        txt3=(EditText)findViewById(R.id.sector_3_id);
        txt4=(EditText)findViewById(R.id.sector_4_id);
        txt5=(EditText)findViewById(R.id.sector_5_id);
        txt6=(EditText)findViewById(R.id.sector_6_id);
        txt7=(EditText)findViewById(R.id.sector_7_id);
        txt8=(EditText)findViewById(R.id.sector_8_id);
        txt9=(EditText)findViewById(R.id.sector_9_id);
        txt10=(EditText)findViewById(R.id.sector_10_id);
        txt11=(EditText)findViewById(R.id.sector_11_id);
        txt12=(EditText)findViewById(R.id.sector_12_id);
















        select_sector_done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Select_sectors.this,Sectors.class);



                sec1=txt1.getText().toString();
                sec2=txt2.getText().toString();
                sec3=txt3.getText().toString();
                sec4=txt4.getText().toString();
                sec5=txt5.getText().toString();
                sec6=txt6.getText().toString();
                sec7=txt7.getText().toString();
                sec8=txt8.getText().toString();
                sec9=txt9.getText().toString();
                sec10=txt10.getText().toString();
                sec11=txt11.getText().toString();
                sec12=txt12.getText().toString();






                intent.putExtra("val1",sec1);
                intent.putExtra("val2",sec2);
                intent.putExtra("val3",sec3);
                intent.putExtra("val4",sec4);
                intent.putExtra("val5",sec5);
                intent.putExtra("val6",sec6);
                intent.putExtra("val7",sec7);
                intent.putExtra("val8",sec8);
                intent.putExtra("val9",sec9);
                intent.putExtra("val10",sec10);
                intent.putExtra("val11",sec11);
                intent.putExtra("val12",sec12);
                


                startActivity(intent);






            }
        });
    }
}
