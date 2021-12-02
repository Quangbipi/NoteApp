package com.quangminh.timeforlife.FireBaseManager;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import com.quangminh.timeforlife.LoginActivity;
import com.quangminh.timeforlife.MyApplication;
import com.quangminh.timeforlife.R;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    //nhận notifi từ fireBase
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        RemoteMessage.Notification notification = remoteMessage.getNotification();

        if(notification==null){
            return;
        }

        String strTitle = notification.getTitle();
        String strBody = notification.getBody();

        sendNotification(strTitle, strBody);
    }

    private void sendNotification(String strTitle, String strBody) {
        Intent intent = new Intent(this, LoginActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID)
                .setContentTitle(strTitle)
                .setContentText(strBody)
                .setSmallIcon(R.drawable.ic_brands)
                .setContentIntent(pendingIntent);

        Notification notification = notificationBuilder.build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if(notificationManager!=null){
            notificationManager.notify(1,notification);
        }

    }
}
