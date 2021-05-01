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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EventPage extends AppCompatActivity implements ImageAdapter.OnItemClickListener{
    @Override
    public void onItemClick(int position) {
        Toast.makeText(this, "Long click to see option", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onJoinClick(int position) {
        finish();
        startActivity(new Intent(this,Join_as.class
        ));
    }

    private RecyclerView event_list;
    ImageAdapter Iadapter;
    List<Event> list;
    DatabaseReference db;
    ValueEventListener dbListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        list = new ArrayList<>();
        Iadapter = new ImageAdapter(EventPage.this,list);
        event_list = findViewById(R.id.event_list);
        event_list.setHasFixedSize(true);
        event_list.setLayoutManager(new LinearLayoutManager(this));
        event_list=(RecyclerView)findViewById(R.id.event_list);
        Iadapter.setOnItemClickListener(this);

        event_list.setHasFixedSize(true);
        event_list.setLayoutManager(new LinearLayoutManager(this));
        db = FirebaseDatabase.getInstance().getReference().child("Event");

        dbListener = db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Event event = postSnapshot.getValue(Event.class);
                    list.add(event);
                }
                Iadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        event_list.setAdapter(Iadapter);



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
