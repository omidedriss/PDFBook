package abbas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.VideoView;

import com.orbitsoft.pdfbook.MainActivity;
import com.orbitsoft.pdfbook.R;

import java.util.Random;

public class StartActivity extends AppCompatActivity {
VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        videoView=findViewById(R.id.vvideoView);
        Random random = new Random();
        int r = random.nextInt(7);

        if (r==0) {
            videoView.setVideoPath("android.resource://"+ getPackageName()+"/"+R.raw.start);
            videoView.start();
        }
        if (r==1){
            videoView.setVideoPath("android.resource://"+ getPackageName()+"/"+R.raw.start_1);
            videoView.start();}
        if (r==2){
            videoView.setVideoPath("android.resource://"+ getPackageName()+"/"+R.raw.start_2);
            videoView.start();}
        if(r==3){
            videoView.setVideoPath("android.resource://"+ getPackageName()+"/"+R.raw.start_3);
            videoView.start();
        }
        if(r==4){
            videoView.setVideoPath("android.resource://"+ getPackageName()+"/"+R.raw.start_4);
            videoView.start();
        }
        if(r==5){
            videoView.setVideoPath("android.resource://"+ getPackageName()+"/"+R.raw.start_5);
            videoView.start();
        }
        if(r==6){
            videoView.setVideoPath("android.resource://"+ getPackageName()+"/"+R.raw.start_6);
            videoView.start();
        }
        if(r==7){
            videoView.setVideoPath("android.resource://"+ getPackageName()+"/"+R.raw.start_7);
            videoView.start();
        }


        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Intent init = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(init);
                finish();
            }
        });

    }
}