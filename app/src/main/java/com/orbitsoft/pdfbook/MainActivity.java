package com.orbitsoft.pdfbook;

import android.content.Intent;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import abbas.PdfActivity;

//import top.defaults.colorpicker.ColorPickerPopup;
//import com.xeoh.android.texthighlighter.TextHighlighter;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private Button  time,button;
    private BottomNavigationView button_nav;
    // Initialize variable
    private int selectedColor = 0;
    Button btSelect;
    //TextView tvUri, tvPath;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        entry();

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

        //  tvUri = findViewById(R.id.tv_uri);
        //tvPath = findViewById(R.id.tv_path);
        button_nav.setOnNavigationItemSelectedListener(this);
     /*   btSelect.setOnClickListener(v -> {
            Intent next = new Intent(getApplicationContext(), PdfActivity.class);
            startActivity(next);


        });*/



        }
        public void entry(){
        //btSelect=findViewById(R.id.bt_select);
        button_nav=findViewById(R.id.nav_btn);
        }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.home:

                HomeFragment fragment = new HomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, fragment, "");
                fragmentTransaction.commit();

                return true;

            case R.id.alarm:

                AlarmFragment fragment1 = new AlarmFragment();
                FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction1.replace(R.id.frame_layout, fragment1);
                fragmentTransaction1.commit();
                return true;

            case R.id.hilight:

                HilightFragment fragment2 = new HilightFragment();
                FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction2.replace(R.id.frame_layout, fragment2, "");
                fragmentTransaction2.commit();
                return true;

        }

        return false;
    }
}













