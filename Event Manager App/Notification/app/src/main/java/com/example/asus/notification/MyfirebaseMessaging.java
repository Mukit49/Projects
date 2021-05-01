package com.example.asus.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyfirebaseMessaging extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Intent intent=new Intent(this,MainActivity.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );

        PendingIntent  pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT );
        NotificationCompat.Builder notification_builder=new NotificationCompat.Builder(this);
         notification_builder.setContentTitle(" fcm notification");

         notification_builder.setContentText(remoteMessage.getNotification().getBody());

         notification_builder.setAutoCancel(true);
         notification_builder.setSmallIcon(R.mipmap.ic_launcher);

        NotificationManager notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0,notification_builder.build());








    }
}
