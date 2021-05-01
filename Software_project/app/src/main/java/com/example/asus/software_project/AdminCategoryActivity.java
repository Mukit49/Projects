package com.example.asus.software_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class AdminCategoryActivity extends AppCompatActivity {

    private ImageView tshirt, sport_tshirt,femaledress;
    private  ImageView mobile,watches,headphone;
    private ImageView glasses,shoes,purse;
    private  ImageView laptop,books;
    private Button adminlogout_btn ,checkorder_btn;









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__category_activity);

        tshirt=findViewById(R.id.t_shirt_id);
        sport_tshirt=findViewById(R.id.sports_t_id);
        femaledress=findViewById(R.id.female_dress_id);

        mobile=findViewById(R.id.mobile_id);
        watches=findViewById(R.id.watch_id);
        headphone=findViewById(R.id.headphones_id);

        glasses=findViewById(R.id.sunglass_id);
        shoes=findViewById(R.id.shoee_id);
        purse=findViewById(R.id.pursebag_id);

        laptop=findViewById(R.id.laptop_id);
        books=findViewById(R.id.book_id);

        adminlogout_btn=findViewById(R.id.admin_logout_btn_id);
        checkorder_btn=findViewById(R.id.check_orders_btn_id);



         adminlogout_btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent=new Intent(AdminCategoryActivity.this,StartingActivity.class);
                 intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK  | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                 startActivity(intent);
                 finish();


             }
         });

         checkorder_btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent=new Intent(AdminCategoryActivity.this,AdminsNewOrderActivity.class);
                 startActivity(intent);


             }
         });

        tshirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminaddNewProductActivity.class);
                intent.putExtra("category","tshirt");
                startActivity(intent);


            }
        });




       sport_tshirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminaddNewProductActivity.class);
                intent.putExtra("category","sport_tshirt");
                startActivity(intent);


            }
        });

        femaledress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminaddNewProductActivity.class);
                intent.putExtra("category","femaledress");
                startActivity(intent);


            }
        });

        mobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminaddNewProductActivity.class);
                intent.putExtra("category","mobile");
                startActivity(intent);


            }
        });

        watches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminaddNewProductActivity.class);
                intent.putExtra("category","watches");
                startActivity(intent);


            }
        });

        headphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminaddNewProductActivity.class);
                intent.putExtra("category","headphone");
                startActivity(intent);


            }
        });

        glasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminaddNewProductActivity.class);
                intent.putExtra("category","glasses");
                startActivity(intent);


            }
        });

        shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminaddNewProductActivity.class);
                intent.putExtra("category","shoes");
                startActivity(intent);


            }
        });


        purse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminaddNewProductActivity.class);
                intent.putExtra("category","purse");
                startActivity(intent);


            }
        });


        books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminaddNewProductActivity.class);
                intent.putExtra("category","books");
                startActivity(intent);


            }
        });

        laptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this,AdminaddNewProductActivity.class);
                intent.putExtra("category","laptop");
                startActivity(intent);


            }
        });




    }
}
