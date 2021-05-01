package com.example.asus.eventmanager;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.squareup.picasso.Picasso;


import com.bumptech.glide.Glide;

import java.util.List;


public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private Context mContext;
    private List<Event> mUploads;
    private OnItemClickListener mListener;

    public ImageAdapter(Context context, List<Event> uploads) {
        mContext = context;
        mUploads = uploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_row, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        Event uploadCurrent = mUploads.get(position);
        holder.name.setText(uploadCurrent.getEvent_name());
        Glide.with(mContext)
                .load(uploadCurrent.getImage())
                .into(holder.iv);

    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
            , View.OnCreateContextMenuListener
            , MenuItem.OnMenuItemClickListener {


        public TextView name;
        public  ImageView iv;
        public ImageViewHolder(View itemView) {

            super(itemView);
           name =  itemView.findViewById(R.id.event_name_eventspage_id);
            iv = itemView.findViewById(R.id.event_image_id)  ;
            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);

        }


        @Override
        public void onClick(View v) {
            //  if (mListener != null) {
            int position = getAdapterPosition();
            //  if (position != RecyclerView.NO_POSITION) {
            mListener.onItemClick(position);
            //}
            // }
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select Action");
            MenuItem delete = menu.add(Menu.NONE, 1, 1, "Join Event");
            delete.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            //if (mListener != null) {
            int position = getAdapterPosition();
            //  if (position != RecyclerView.NO_POSITION) {

            switch (item.getItemId()) {
                case 1:
                    mListener.onJoinClick(position);
                    return true;
            }
            //  }
            //  }
            return false;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onJoinClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
}