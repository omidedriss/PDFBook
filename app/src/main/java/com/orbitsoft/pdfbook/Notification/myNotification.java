package com.orbitsoft.pdfbook.Notification;


import static com.orbitsoft.pdfbook.Notification.NotificationChannelCustom.CHANNEL_ID;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.session.MediaSession;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationManagerCompat;

import com.orbitsoft.pdfbook.MainActivity;
import com.orbitsoft.pdfbook.R;

import java.util.Random;

public class myNotification {

    Context context;

    public myNotification(Context context) {
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void showNotification() {

        // MARBOOT BE COVER NOTIFICATION...
        int[] backGround = {R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4, R.drawable.p6,
                R.drawable.p7, R.drawable.p8, R.drawable.p9, R.drawable.p10, R.drawable.p11,
                R.drawable.p12,R.drawable.p13, R.drawable.p14, R.drawable.p15};
        Random myRand = new Random();
        int i = myRand.nextInt(15);


        MediaSession mediaSession = new MediaSession(context, "Media Picture");
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), backGround[i]);


        // BARAYE BARGASHTAN BE MAIN ACTIVITY...
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
//
            pendingIntent = PendingIntent.getActivity(context, 0, intent,  PendingIntent.FLAG_MUTABLE);
        }
        else
        {
            pendingIntent = PendingIntent.getActivity
                    (context, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        }

        Uri soundUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"+ context.getApplicationContext().getPackageName() + "/" + R.raw.sound_2);

        AudioAttributes soundAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .build();

        NotificationChannel channel = new NotificationChannel("0", "Channel",
                NotificationManager.IMPORTANCE_HIGH);
        channel.setSound(soundUri, soundAttributes);
        channel.setLightColor(Color.YELLOW);
        channel.setDescription("msg");
        channel.enableLights(true);
        channel.enableVibration(true);
        channel.setShowBadge(true);
        channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
       // getManager().createNotificationChannel(channel);


        Notification notification = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification = new Notification.Builder(context, "0")
                    .setContentTitle("STUDY TIME ...")
                    .setContentText("lets get back to work ")
                    .setSmallIcon(R.drawable.ic_baseline_menu_book_24)
                    .setLargeIcon(bitmap)
                  //  .setSound(soundUri)

                    .setContentIntent(pendingIntent)
                    .setStyle(new Notification.MediaStyle()

                            .setMediaSession(mediaSession.getSessionToken()))
                   // .setPriority(Notification.PRIORITY_DEFAULT)
                    .build();

        }

        NotificationManagerCompat nft = NotificationManagerCompat.from(context);
//        NotificationManager nft =
//                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nft.createNotificationChannel(channel);
        nft.notify(0, notification);

    }


}
