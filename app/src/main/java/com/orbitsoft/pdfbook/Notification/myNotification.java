package com.orbitsoft.pdfbook.Notification;



import static com.orbitsoft.pdfbook.Notification.NotificationChannel.CHANNEL_ID;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.session.MediaSession;
import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.orbitsoft.pdfbook.MainActivity;
import com.orbitsoft.pdfbook.R;

public class myNotification {

    Context context;

    public myNotification(Context context) {
        this.context = context;
    }

    public void showNotification() {

        // MARBOOT BE COVER NOTIFICATION...
        MediaSession mediaSession = new MediaSession(context, "Media Picture");
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.oldbooks);


        // BARAYE BARGASHTAN BE MAIN ACTIVITY...
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        Uri soundUri =Uri.parse("android.resource://"+context.getPackageName()+"/"+R.raw.sound_2);


        @SuppressLint({"NewApi", "LocalSuppress"})
        android.app.Notification notification = new Notification.Builder(context, CHANNEL_ID)
                .setContentTitle("STUDY TIME ...")
                .setContentText("lets get back to work ")
                .setSmallIcon(R.drawable.ic_baseline_menu_book_24)
                .setLargeIcon(bitmap)
                .setSound(soundUri)
                .setContentIntent(pendingIntent)
                .setStyle(new Notification.MediaStyle()

                        .setMediaSession(mediaSession.getSessionToken()))
                .setPriority(android.app.Notification.PRIORITY_DEFAULT)
                .build();

        NotificationManagerCompat nft = NotificationManagerCompat.from(context);
        nft.notify(1, notification);
    }


}
