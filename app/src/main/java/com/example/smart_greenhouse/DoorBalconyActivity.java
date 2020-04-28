package com.example.smart_greenhouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toolbar;


public class DoorBalconyActivity extends AppCompatActivity {//Door Balcony Activity

    private Toolbar toolbar;
    private TableLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doorbalcony_content); // set ui


        //------------------- set object -----------------------------
        ImageButton close_imbtn= findViewById(R.id.close_imbtn);
        final ImageView stop_imbtn= findViewById(R.id.stop_imbtn);
        final ImageButton open_imbtn= findViewById(R.id.open_imbtn);
        final TextView line_txt=  findViewById(R.id.line_txt);
        final TextView status_txt=  findViewById(R.id.status_txt);

        final TextView device_name =findViewById(R.id.device_name);
        final TextView device_loc =findViewById(R.id.device_loc);
        final TextView floor_name =findViewById(R.id.floor_name);


        stop_imbtn.setVisibility(View.INVISIBLE);
        //-------------------------------------------------------------





        device_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//device_name set On Click Listener

                add_on_dialog(device_name);

            }
        });

        device_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//device_loc set On Click Listener

                add_on_dialog(device_loc);

            }
        });
        floor_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//floor_name set On Click Listener

                add_on_dialog(floor_name);

            }
        });




        close_imbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {////close_imbtn set On Click Listener

                start_anim_imageview_ccw(stop_imbtn);//start_anim_imageview_ccw


                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void run()
                    {
                        stop_imbtn.setVisibility(View.INVISIBLE);
                        status_txt.setText("Status (Locked)");
                        open_imbtn.setBackgroundResource(R.drawable.ic_lock_control_open);
                        line_txt.setBackgroundColor(R.color.color_red );
                    }
                }, 3000);


            }
        });


        stop_imbtn.setOnClickListener(new View.OnClickListener() {//   stop_anim_imageview
            @Override
            public void onClick(View view) {

                stop_anim_imageview(stop_imbtn);

            }
        });




        open_imbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//open_imbtn set On Click Listener

                start_anim_imageview(stop_imbtn);


                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void run()
                    {
                        stop_imbtn.setVisibility(View.INVISIBLE);
                        status_txt.setText("Status (Opened)");
                        open_imbtn.setBackgroundResource(R.drawable.ic_lock_control_open_red);
                        line_txt.setBackgroundColor(R.color.per54black);
                    }
                }, 3000);




            }
        });


    }





    String get="";
    void add_on_dialog(final TextView text_name)// add dilog
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(DoorBalconyActivity.this);//make dilog
        builder.setTitle("Input Name");

        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.dialog_change_title, null);//make ui
        final EditText edit_dialog =  view.findViewById(R.id.edit_dialog);

        edit_dialog.setHint(text_name.getText().toString());
        builder.setView(view);
        //builder.setNegativeButton("خروج",null);

        builder.setPositiveButton("save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {


                if(edit_dialog.getText().toString().length()<1) get="No name";//set object name
                else get=edit_dialog.getText().toString();
                text_name.setText(get);

                dialog.dismiss();
            }


        });

        builder.show();

    }






    public void start_anim_imageview(final ImageView Ibt_name_anim) {//start anim imageview

        Ibt_name_anim.setVisibility(View.VISIBLE);
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_1);
        Ibt_name_anim.startAnimation(anim);


    }

    public void start_anim_imageview_ccw(final ImageView Ibt_name_anim) {//start anim imageview ccw

        Ibt_name_anim.setVisibility(View.VISIBLE);

        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_ccw1);
        Ibt_name_anim.startAnimation(anim);


    }

    public void stop_anim_imageview(final ImageView Ibt_name_anim) {//stop_anim_imageview
        Ibt_name_anim.setVisibility(View.VISIBLE);
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_up);
        anim.cancel();
        Ibt_name_anim.startAnimation(anim);
    }



}