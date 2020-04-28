package com.example.smart_greenhouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TableLayout;
import android.widget.Toolbar;


public class SplashActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TableLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_content);


        final Handler handler = new Handler();//set Splash Activity for 2 sec
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                finish();
            }
        }, 2000);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
