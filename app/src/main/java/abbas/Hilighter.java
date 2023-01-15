package abbas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.widget.EditText;
import android.widget.TextView;

import com.orbitsoft.pdfbook.R;

public class Hilighter extends AppCompatActivity {
EditText m;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hilighter);
// کد برای هایلایت کردن اما نمیدونم چجوری استفاده کنیم ازش
        m=findViewById(R.id.idddd);
        TextView tvvv = (TextView) findViewById(R.id.tsss);
        tvvv.setOnClickListener(v -> {
            SpannableString spannableString = new SpannableString(m.getText());
            spannableString.setSpan(new BackgroundColorSpan(R.color.purple_700),
                    m.getSelectionStart(),
                    m.getSelectionEnd(),0);

            m.setText(spannableString);
            tvvv.setText(spannableString);

        });


    }

}