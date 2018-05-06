package com.example.prathamesh.policeapp;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by Planet on 4/6/2018.
 */
public class Splash extends Activity {
    Animation animation;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        imageView = (ImageView)findViewById(R.id.image2);
        animation = AnimationUtils.loadAnimation(this,R.anim.fadeinsplash);

        imageView.setAnimation(animation);

       final Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(8000);
                }catch (InterruptedException e){

                }finally {
                  //  finishAffinity();
                    Intent intent = new Intent(Splash.this,Login.class);
                    overridePendingTransition(0,0);
                    startActivity(intent);
                }
            }
        };
        timer.start();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                timer.interrupt();

               // finishAffinity();
                Intent intent = new Intent(Splash.this,Login.class);
                overridePendingTransition(0,0);
                startActivity(intent);

            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();

    }

}
