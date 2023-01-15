package com.orbitsoft.pdfbook.Notification;

import android.app.Application;
import android.app.NotificationManager;
import android.os.Build;

public class NotificationChannelCustom extends Application {
    public static final String CHANNEL_ID ="myNotification Channel";
    public static final String NOTIFICATION_TITLE="PDF BOOK"+"\n"+"کتاب خوان من";
    @Override
    public void onCreate() {
        super.onCreate();
        creatNotification();



    }
      //sakhtan notification channel
    private void creatNotification() {

        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.O){

            android.app.NotificationChannel channel=new android.app.NotificationChannel(CHANNEL_ID,
                    NOTIFICATION_TITLE,
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("this is going to show message");

            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }
}
