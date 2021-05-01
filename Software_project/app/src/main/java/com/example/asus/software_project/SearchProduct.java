package com.example.asus.software_project;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.asus.software_project.Model.Products;
import com.example.asus.software_project.ViewHolder.ProductViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class SearchProduct extends AppCompatActivity {

    private Button  search_btn;
    private EditText input_serch_txt;
    private RecyclerView searchList;
    private String searchInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product);
        search_btn=findViewById(R.id.search_btn_id);
        input_serch_txt=findViewById(R.id.search_product_name_id);
        searchList=findViewById(R.id.search_list_id);

         searchList.setLayoutManager(new LinearLayoutManager(this));

         search_btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                       searchInput=input_serch_txt.getText().toString();
                       onStart();

             }
         });




    }

    protected void onStart() {

        super.onStart();
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("Products");


        FirebaseRecyclerOptions<Products> options=
           new FirebaseRecyclerOptions.Builder<Products>()
                .setQuery(reference.orderByChild("pname").startAt(searchInput).endAt(searchInput),Products.class)
                .build();

        FirebaseRecyclerAdapter<Products,ProductViewHolder> adapter=
                new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull final Products model) {
                        holder.txtproductName.setText(model.getPname());
                        holder.txtproductDescription.setText(model.getDescription());
                        holder.txtproductCost.setText("price= "+model.getPrice()+"$");

                        Picasso.get().load(model.getImage()).into(holder.imageView);

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent intent=new Intent(SearchProduct.this,ProductDetailsActivity.class) ;
                                intent.putExtra("pid",model.getPid());
                                startActivity(intent);


                            }
                        });
                    }

                    @NonNull
                    @Override
                    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_items_layout,parent,false);
                        ProductViewHolder holder=new ProductViewHolder(view);
                        return holder;


                    }
                };
        searchList.setAdapter(adapter);
        adapter.startListening();

    }
}
