package com.example.jfisrat.eventmanagerapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomePageMenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {
    private DrawerLayout drawerLayout;
    private Button createEventButton,joinEventButton;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_menu);

        Toolbar toolbar=(Toolbar) findViewById(R.id.home_toolBarId);

        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(HomePageMenu.this, LoginPage.class));
                    finish();
                }
            }
        };

        createEventButton=findViewById(R.id.createEventButtonId);
        createEventButton.setOnClickListener(this);
        joinEventButton=findViewById(R.id.joinEventButtonId);
        joinEventButton.setOnClickListener(this);

        setSupportActionBar(toolbar);
        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layoutId);
        NavigationView navigationView=findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.createEventButtonId){
            startActivity(new Intent(this,CreateEvent.class));
        }
        else if(v.getId()==R.id.joinEventButtonId){
            startActivity(new Intent(this,JoinEvent.class));
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if(menuItem.getItemId()==R.id.profileId){
            startActivity(new Intent(this,profile_activity.class));
        }
         else if(menuItem.getItemId()==R.id.createEventId){
            startActivity(new Intent(this,CreateEvent.class));
        }
         else if(menuItem.getItemId()==R.id.myEventId){
            startActivity(new Intent(this,MyEventActivity.class));
        }
         else if(menuItem.getItemId()==R.id.eventShareId){
            startActivity(new Intent(this,EventShareWithMe.class));
        }
         else if(menuItem.getItemId()==R.id.createGroupId){
            startActivity(new Intent(this,CreateGroups.class));
        }
         else if(menuItem.getItemId()==R.id.groupsId){
            startActivity(new Intent(this,Groups.class));
        }
         else if(menuItem.getItemId()==R.id.settingId){
            startActivity(new Intent(this,SettingsActivity.class));
        }
        else if(menuItem.getItemId()==R.id.helpId){
            startActivity(new Intent(this,HelpActivity.class));
        }
        else if(menuItem.getItemId()==R.id.dashboardId){
            startActivity(new Intent(this,DashboardActivity.class));
        }
        else if(menuItem.getItemId()==R.id.logOutId){
            logOut();
        }
        return false;
    }

    public void logOut() {
        auth.signOut();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }


}
