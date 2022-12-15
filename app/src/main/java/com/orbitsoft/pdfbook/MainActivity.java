package com.orbitsoft.pdfbook;

import static com.orbitsoft.pdfbook.Notification.NotificationChannel.CHANNEL_ID;

import androidx.appcompat.app.AppCompatActivity;



import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.orbitsoft.pdfbook.Notification.myNotification;

public class MainActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 myNotification  myotification=new myNotification(MainActivity.this);
                myotification.showNotification();
            }
        });





    }

}