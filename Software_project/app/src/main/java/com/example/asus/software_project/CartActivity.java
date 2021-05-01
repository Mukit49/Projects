package com.example.asus.software_project;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.software_project.Model.Cart;
import com.example.asus.software_project.Prevalent.Prevalent;
import com.example.asus.software_project.ViewHolder.CartViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CartActivity extends AppCompatActivity {

      private RecyclerView recyclerView;
      private RecyclerView.LayoutManager layoutManager;
      private Button NextProcessButton;
      private TextView txtTotalAmount,txtmsg1;


      private int overallPrice=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView=findViewById(R.id.cart_list_id);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        NextProcessButton=findViewById(R.id.next_process_btn_id);
        txtTotalAmount=findViewById(R.id.total_price_id);
        txtmsg1=findViewById(R.id.msg1_id);





        NextProcessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtTotalAmount.setText(" Total Price = "+String.valueOf(overallPrice));

                Intent intent=new Intent(CartActivity.this,ConfirmFinalOrderActivity.class);
                 intent.putExtra("Total Price",String.valueOf(overallPrice)) ;
                  startActivity(intent);
                  finish();


            }
        });


    }

    protected void  onStart()
    {
        super.onStart();
        checkOrderState();
        final DatabaseReference cartListRef= FirebaseDatabase.getInstance().getReference().child("Cart List");
        FirebaseRecyclerOptions<Cart>  options=
                new FirebaseRecyclerOptions.Builder<Cart>()
                .setQuery(cartListRef.child("User view").child(Prevalent.currentonlineUser.getPhone()).child("Products"),Cart.class).build();


        FirebaseRecyclerAdapter<Cart,CartViewHolder> adapter
                =new FirebaseRecyclerAdapter<Cart, CartViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CartViewHolder holder, int position, @NonNull final Cart model) {
                     holder.txtProductQuantity.setText("Quantity ="+model.getQuantity());
                     holder.txtProductName.setText(model.getPname());
                     holder.txtProductPrice.setText("Price = "+model.getPrice());

                       int individualTPrice=((Integer.valueOf(model.getPrice()))) * Integer.valueOf(model.getQuantity());
                       overallPrice=overallPrice+individualTPrice;

                         holder.itemView.setOnClickListener(new View.OnClickListener() {
                             @Override
                             public void onClick(View v) {
                                 CharSequence options[]=new CharSequence[]
                                         {
                                                 "Edit" ,
                                                 "Remove"

                                         };

                                 AlertDialog.Builder builder=new AlertDialog.Builder(CartActivity.this);
                                 builder.setTitle("Cart Options :");

                                 builder.setItems(options, new DialogInterface.OnClickListener() {
                                     @Override
                                     public void onClick(DialogInterface dialogInterface, int i) {

                                              if(i==0)
                                              {
                                                  Intent intent=new Intent(CartActivity.this,ProductDetailsActivity.class) ;
                                                   intent.putExtra("pid",model.getPid());
                                                   startActivity(intent);
                                              }

                                              if(i==1)
                                              {
                                                  cartListRef.child("User view")
                                                          .child(Prevalent.currentonlineUser.getPhone())
                                                          .child("Products")
                                                          .child(model.getPid())
                                                          .removeValue()
                                                          .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                              @Override
                                                              public void onComplete(@NonNull Task<Void> task) {

                                                                            if(task.isSuccessful())
                                                                            {
                                                                                Toast.makeText(CartActivity.this,"Item removed successfully",Toast.LENGTH_SHORT).show();

                                                                                Intent intent=new Intent(CartActivity.this,HomeActivity.class) ;

                                                                                startActivity(intent);

                                                                            }

                                                              }
                                                          }) ;

                                              }

                                     }
                                 }) ;
                                  builder.show();

                             }
                         });


            }

            @NonNull
            @Override
            public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items_layout,parent,false);
                CartViewHolder holder=new CartViewHolder(view);
                return holder;


            }
        } ;

        recyclerView.setAdapter(adapter);
        adapter.startListening();



    }
    private void  checkOrderState()
    {
        DatabaseReference orderRef ;
        orderRef= FirebaseDatabase.getInstance().getReference().child("Orders").child(Prevalent.currentonlineUser.getPhone()) ;
        orderRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists())
                {
                   String shippingState= dataSnapshot.child("state").getValue().toString();
                   String userName= dataSnapshot.child("name").getValue().toString();

                   if(shippingState.equals("shipped"))
                   {
                      txtTotalAmount.setText("Dear "+ userName +" \n order is shipped succesfully");
                      recyclerView.setVisibility(View.GONE);
                      txtmsg1.setVisibility(View.VISIBLE);
                      txtmsg1.setText("Congratulations,Your order shipped successfully.Soon it will be verified");
                      NextProcessButton.setVisibility(View.GONE);

                      Toast.makeText(CartActivity.this,"You can purchage more product, once you received your first order",Toast.LENGTH_SHORT).show();


                   }
                   else if(shippingState.equals("not shipped"))
                   {
                       txtTotalAmount.setText("The order is not shipped");
                       recyclerView.setVisibility(View.GONE);
                       txtmsg1.setVisibility(View.VISIBLE);
                       NextProcessButton.setVisibility(View.GONE);

                       Toast.makeText(CartActivity.this,"You can purchage more product, once you received your first order",Toast.LENGTH_SHORT).show();


                   }

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        })  ;

    }



}
