package com.example.asus.notification;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstaceId extends FirebaseInstanceIdService {

    private static final String reg_token="reg_token";


    @Override
    public void onTokenRefresh() {
        String recent_token= FirebaseInstanceId.getInstance().getToken();


        Log.d(reg_token,recent_token);




    }
}


