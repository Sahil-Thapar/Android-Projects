package com.mandeepkaur.angrybird;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import pl.droidsonroids.gif.GifTextView;

public class SplashScreenActivity extends AppCompatActivity {

    GifTextView play;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        final MediaPlayer ring= MediaPlayer.create(SplashScreenActivity.this,R.raw.starttheme);
        ring.start();


        play = (GifTextView)findViewById(R.id.play);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ring.stop();
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });

    }
}
