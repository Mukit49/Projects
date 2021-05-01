package com.example.asus.software_project;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.asus.software_project.Model.Admin_orders;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminsNewOrderActivity extends AppCompatActivity {


      private RecyclerView orderList;
      private DatabaseReference orderRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admins_new_order);

        orderRef= FirebaseDatabase.getInstance().getReference().child("Orders");
         orderList=findViewById(R.id.order_list_id);



         orderList.setLayoutManager(new LinearLayoutManager(this));
         onStart();



    }

    protected void onStart() {

        super.onStart();

        FirebaseRecyclerOptions<Admin_orders> options=
                new FirebaseRecyclerOptions.Builder<Admin_orders>()
                .setQuery(orderRef,Admin_orders.class)
                .build();

        FirebaseRecyclerAdapter<Admin_orders,AdminOrdersViewHolder> adapter=
                new FirebaseRecyclerAdapter<Admin_orders, AdminOrdersViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull AdminOrdersViewHolder holder, int position, @NonNull Admin_orders model) {
                              holder.userName.setText("Name : "+ model.getName());
                              holder.userNum.setText(" Number : "+ model.getPhone());
                              holder.userAdd.setText(" Address"+ model.getAddress());
                              holder.userPrice.setText(" price= "+ model.getTotalamount());
                              holder.userDateTime.setText("Date and Time : "+ model.getDate() + "  "+model.getTime());
                    }

                    @NonNull
                    @Override
                    public AdminOrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orders_layout,parent,false);
                        return new AdminOrdersViewHolder(view);
                    }
                } ;

        orderList.setAdapter(adapter);
        adapter.startListening();


    }

    public static class AdminOrdersViewHolder extends RecyclerView.ViewHolder{


        public TextView  userName,userNum,userDateTime,userAdd,userPrice;
        public Button show_order_btn;



        public AdminOrdersViewHolder(@NonNull View itemView) {
            super(itemView);

            userName=itemView.findViewById(R.id.userr_name_id);
            userNum=itemView.findViewById(R.id.order_phone_txt_id);
            userAdd=itemView.findViewById(R.id.order_address_city_id);
            userDateTime=itemView.findViewById(R.id.order_date_time_id);
            userPrice=itemView.findViewById(R.id.order_totall_price_id);
            show_order_btn=itemView.findViewById(R.id.showall_product_btn_id);



        }
    }


}
