package com.example.asus.software_project.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.asus.software_project.Interface.ItemClickListener;
import com.example.asus.software_project.R;

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
     public TextView txtProductName,txtProductPrice,txtProductQuantity;
     private ItemClickListener itemClickListener;

     public CartViewHolder(@NonNull View itemView) {
          super(itemView);
          txtProductName=itemView.findViewById(R.id.cart_product_name_id);
          txtProductPrice=itemView.findViewById(R.id.cart_product_price_id) ;
          txtProductQuantity=itemView.findViewById(R.id.cart_product_quantity_id);



     }


     @Override
     public void onClick(View view) {
          itemClickListener.onClick(view,getAdapterPosition(),false);


     }

     public void setItemClickListener(ItemClickListener itemClickListener) {
          this.itemClickListener = itemClickListener;
     }
}
