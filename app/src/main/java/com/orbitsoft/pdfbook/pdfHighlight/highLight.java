package com.orbitsoft.pdfbook.pdfHighlight;

import static android.provider.Telephony.Mms.Part.FILENAME;
import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.orbitsoft.pdfbook.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class highLight extends AppCompatActivity {
    Button button;
    EditText editText;
    TextView textView;
    private void writeToFile(String data) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(FILENAME, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {

            Log.e(TAG, "File write failed: " + e.toString());
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_high_light );
        button = (Button) findViewById( R.id.button );
        editText = (EditText) findViewById( R.id.search );
        textView = (TextView) findViewById( R.id.textView );
        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    PlayWithRawFiles();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(),
                            "Problems: ", Toast.LENGTH_LONG).show();
                }
                String text = (String) textView.getText();
                Spannable textSpannable = new SpannableString(text);
                String[] array = text.split("\\|", -1);
                for (int j =0 ; j<array.length ; j++) {
                    //word of your list
                   //  String word = String.valueOf(text.getT(j));
                    //find index of words
                    for (int i = -1; (i = text.indexOf(array[i], i + 1)) != -1; i++) {
                        //find the length of word for set color
                        int last = i + array.length;
                        //set text color with spannable
                        textSpannable.setSpan(new BackgroundColorSpan(Color.parseColor("#0cab8f")),
                                i, last, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                }
                textView.setText(textSpannable);
            }
            public void PlayWithRawFiles() throws IOException {
                String str="";
                StringBuffer buf = new StringBuffer();
                InputStream is = getResources().openRawResource(R.raw.a);
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                if (is!=null) {
                    while ((str = reader.readLine()) != null) {
                        buf.append(str + "\n" );
                    }
                }
                is.close();
                TextView tv=(TextView)findViewById(R.id.textView);
                tv.setText(buf.toString());


            }//

        });
        writeToFile( (String) textView.getText() );

    }
}