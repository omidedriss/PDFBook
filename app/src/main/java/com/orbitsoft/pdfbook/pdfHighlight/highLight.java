package com.orbitsoft.pdfbook.pdfHighlight;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.orbitsoft.pdfbook.R;

public class highLight extends AppCompatActivity {
    Button button;
    EditText editText;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_high_light );
        button = (Button) findViewById( R.id.button );
        editText = (EditText) findViewById( R.id.search );
        textView = (TextView) findViewById( R.id.textView );
       /* button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TextHighlighter()
                        .setBackgroundColor( Color.parseColor( "#FFFF00" ) )
                        .setForegroundColor( Color.GREEN )
                        .addTarget( textView )
                        .highlight( editText.getText().toString(), TextHighlighter.BASE_MATCHER );
            }
        } );

        */
    }
}