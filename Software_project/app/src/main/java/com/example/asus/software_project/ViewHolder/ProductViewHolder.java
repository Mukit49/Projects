package com.example.asus.software_project.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.software_project.Interface.ItemClickListener;
import com.example.asus.software_project.R;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{

    public TextView txtproductName,txtproductDescription,txtproductCost;
    public ImageView imageView;
    public ItemClickListener listener;


    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView  = (ImageView) itemView.findViewById(R.id.user_product_image_id);
        txtproductName=(TextView) itemView.findViewById(R.id.user_product_name_id);
        txtproductDescription=(TextView) itemView.findViewById(R.id.user_product_description_id);
        txtproductCost=(TextView) itemView.findViewById(R.id.user_product_price_id);









    }

    public void setItemClickListener(ItemClickListener listener)
    {
        this.listener=listener;

    }



    @Override
    public void onClick(View view) {
        listener.onClick(view,getAdapterPosition(),false);

    }
}
