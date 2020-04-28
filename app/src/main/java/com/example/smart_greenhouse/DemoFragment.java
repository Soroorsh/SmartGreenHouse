package com.example.smart_greenhouse;

import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.fragment.app.Fragment;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.github.anastr.speedviewlib.TubeSpeedometer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import mehdi.sakout.fancybuttons.FancyButton;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;

public class DemoFragment extends Fragment { //demo fragment

    private WebView webview;
    String url = "http://microdroidprj.ir/";
    private static final String TAG = "Main";
    private ProgressDialog progressBar;
//    static FancyButton btn_status;

//    static TextView tx_time;
//    static TextView tx_data;

    public static LinearLayout balcony_layot;

//    public static RoundCornerProgressBar Batteryprogress;
//    public static  TubeSpeedometer voltagetubeSpeedometer;
//    public DemoFragment() {
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_demo, container, false); // set ui demo
        TextView txt_enter=rootView.findViewById(R.id.enter);
        balcony_layot=rootView.findViewById(R.id.balcony_layot);
        balcony_layot.setVisibility(View.GONE);

        FloatingActionButton fab =  rootView.findViewById(R.id.fab);//set button
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                balcony_layot.setVisibility(View.VISIBLE);
//                reset_timer();
            }
        });

        txt_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), DoorBalconyActivity.class));
            }
        });

        return rootView;
    }
}