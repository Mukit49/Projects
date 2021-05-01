package com.example.asus.eventmanager;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EventPage extends AppCompatActivity {

    private RecyclerView event_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);


        event_list=(RecyclerView)findViewById(R.id.event_list);

        event_list.setHasFixedSize(true);
        event_list.setLayoutManager(new LinearLayoutManager(this));





    }

    @Override
    protected void onStart() {
        super.onStart();


    }
    public static class EventViewHolder extends RecyclerView.ViewHolder{

        View nview;


        public EventViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView=nview;




        }

        public void setEvent_name(String event_name){


            TextView event_name1=(TextView)nview.findViewById(R.id.event_name_eventspage_id);
            event_name1.setText(event_name);

        }









    }

}
