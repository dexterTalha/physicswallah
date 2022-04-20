package com.talha.physicswallahassignment.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.talha.physicswallahassignment.R;

public class SplashScreen extends AppCompatActivity {

    ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        logo = findViewById(R.id.logo_image);

        Animation scaleDown = AnimationUtils.loadAnimation(this, R.anim.scaleupanimation);

        logo.startAnimation(scaleDown);



        new Handler().postDelayed(() -> {

            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();

        }, 1500);
    }
}