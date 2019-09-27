package com.alanger.peruappstest.preloader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.alanger.peruappstest.R;
import com.alanger.peruappstest.main.view.TabbetActivity;


public class ActivityPreloader extends Activity {

    static LottieAnimationView lAVbackground;
    static ImageView iViewLogoEmpresa;
    static Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preloader);
        declare();
        startAnimations();
    }

    private void declare() {
        ctx = this;
        lAVbackground = findViewById(R.id.lottie);
        iViewLogoEmpresa = findViewById(R.id.iViewLogoEmpresa);
    }

    void startAnimations(){
        final Animation animLayout =
                android.view.animation.AnimationUtils.loadAnimation(getBaseContext(), R.anim.fade_in);

        Handler handler = new Handler();
        handler.post(
                () -> {
                    lAVbackground.startAnimation(animLayout);
                    lAVbackground.setVisibility(View.VISIBLE);
                }
        );

        final Animation anim_rightFadeIn1 =
                android.view.animation.AnimationUtils.loadAnimation(getBaseContext(), R.anim.right_fade_in);


        handler.post(
                () -> {
                    iViewLogoEmpresa.startAnimation(anim_rightFadeIn1);
                    iViewLogoEmpresa.setVisibility(View.VISIBLE);
                }
        );


        handler.postDelayed(()->
            {
                    startActivity(new Intent(this, TabbetActivity.class));

            },2000);
    }

}
