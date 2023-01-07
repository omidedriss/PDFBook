package com.orbitsoft.pdfbook;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnDrawListener;
import com.github.barteksc.pdfviewer.util.FitPolicy;
import java.sql.Time;

import abbas.Hilighter;
import abbas.PdfActivity;

//import top.defaults.colorpicker.ColorPickerPopup;
//import com.xeoh.android.texthighlighter.TextHighlighter;

public class MainActivity extends AppCompatActivity{
    private Button button, time;

    // Initialize variable
    private int selectedColor = 0;
    Button btSelect;
    //TextView tvUri, tvPath;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_btn);

        //button=(Button) findViewById(R.id.bt_select);
        // time=(Button) findViewById(R.id.time1);
      /*  time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, TimeActivity.class);
                startActivity(i);
            }
        });*/
       /* button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {


                //myNotification  myotification=new myNotification(MainActivity.this);
                // myotification.showNotification();

                //getMyColor(v);




            }*/
        //   });


        // assign variable
        btSelect = findViewById(R.id.bt_select);
        //  tvUri = findViewById(R.id.tv_uri);
        //tvPath = findViewById(R.id.tv_path);
        btSelect.setOnClickListener(v -> {
            Intent next = new Intent(getApplicationContext(), PdfActivity.class);
            startActivity(next);




        });

        // Initialize result launcher

    }
}











