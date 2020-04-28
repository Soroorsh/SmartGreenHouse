package com.example.smart_greenhouse;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.content.ContextCompat;
//import android.support.v4.view.GravityCompat;
//import android.support.v4.widget.DrawerLayout;
//import android.support.v7.app.ActionBarDrawerToggle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;

import android.os.Bundle;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Vibrator;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

import android.widget.RelativeLayout;
//import android.widget.Toolbar;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {

    Fragment fragment = null;
    static  private String[] mNavigationDrawerItemTitles;
    static Typeface iranianSansFont;
    Menu m;
    static FragmentManager fragmentManager;
    static RelativeLayout snake;



//###################################################################################################################
// --------------  wifi  ------------------


    String straboute="این برنامه وای فای است،و قابلیت های زیر را دارد:"+"\n"
            +"♦اتصال خودکار."
            +"\n"+"♦کنترل 8 دستگاه."
            +"\n"+"♦قابلیت دریافت دما."
            +"\n"+"♦کنترل میزان روشنایی."
            +"\n"+ "♦دارای قابلیت ریفرش دکمه ها."
            +"\n"+ "♦امکان ایجاد دکمه به تعداد دلخواه."
            +"\n"+ "♦امکان تعریف نام دکمه ها."
            +"\n"+ "♦نمایش دیتای ارسالی و دریافتی."
            +"\n"+ "♦نمایش وضعیت دستگاه."
            +"\n"+ " برای دیدن بیشتر و سفارش پروژه می توانید به وب سایت مراجعه کنید : "
            +"\n"+ "site:http://microdroidprj.ir/"
            +"\n"+ "version:V 1.0"
            +"\n"+ "همچنین جهت سفارش نرم افزار اندرویدی می توانید با برنامه نویس ارتباط برقرار کنید،آیا مایلید؟";



    //*******************************************************************-----------------------------------------------------------------
    public static void vibrate_(int ms,Context context){

        Vibrator vi = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        assert vi != null;
        vi.vibrate(ms);
    }
    static void show_snacbar(String s)
    {

        Snackbar.make(snake,s , Snackbar.LENGTH_LONG)
                .setAction("Status", null).show();

    }


//*************************************************************************************************************************
//####################################################################################################################
//####################################################################################################################
//####################################################################################################################
//####################################################################################################################


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//set ui
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);//set toolbar
        setSupportActionBar(toolbar);


        startActivity(new Intent(MainActivity.this, SplashActivity.class));//startActivity SplashActivity
        snake = (RelativeLayout) findViewById(R.id.content);

        fragmentManager = getSupportFragmentManager();//set fragmentManager
        fragmentManager.beginTransaction().replace(R.id.frame_container, new DemoFragment()).commit();//replace fragment


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);//define drawer_layout

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationDrawerItemTitles= getResources().getStringArray(R.array.navigation_drawer_items_array);//define array title

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);//define NavigationView
        navigationView.setNavigationItemSelectedListener(this);

        //-------------------------------------------set drawer font
        m = navigationView.getMenu();
        for (int i = 0; i < m.size(); i++) {
            MenuItem mi = m.getItem(i);
            SubMenu subMenu = mi.getSubMenu();
            if (subMenu != null && subMenu.size() > 0) {
                for (int j = 0; j < subMenu.size(); j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    applyFontToMenuItem(subMenuItem);
                }
            }
            applyFontToMenuItem(mi);
        }
//-------------------------------------------------------

    }

    boolean backPressToExit=false;
    @Override
    public void onBackPressed()// on Back Pressed event
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {


            if (backPressToExit) {
                super.onBackPressed();//exit app on Back Pressed event
                return;
            }
            this.backPressToExit = true;
            Snackbar.make(findViewById(R.id.content), "جهت خروج دوباره بازگشت را بزنید!!!", Snackbar.LENGTH_LONG)// show msg
                    .setAction("Status", null).show();
            fragmentManager.beginTransaction().replace(R.id.frame_container, new DemoFragment()).commit();//replace Demo Fragment

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    backPressToExit = false;
                }
            }, 2000);

            // super.onBackPressed();
        }
    }

    //--------------------- ------------------------------

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    //------------------------------------------------------------------------------------------------

    String abuote_us="در دنياي امروز و درنخستين دهه هزاره سوم اهميت انرژي برق و پايداري آن از مهمترين عوامل توسعه در كشورهاي گوناگون مي\u200Cباشد امروزه همه انديشمندان بر اين باورند كه اداره سيستمها با تكيه بر تحولات تكنولوژيك و مديريت صحيح صورت مي\u200Cپذيرد.\n" +
            "\n" +
            "متخصصين شركت مهندسي معراج طرح ياران به سهم خود جهت نيل به خودكفايي كشور عزيزمان،  ايران اسلامي با تلاش بسيار و انگيزه\u200Cاي قوي اقدام به طراحي وساخت 5 محصول:\n" +
            "\n" +
            "\n" +
            "\n" +
            "دستگاه HVDC تسترPH25&40-100/400/1000\n" +
            "\n" +
            "نشت یاب فراصوتیUCD-20/B\n" +
            "\n" +
            "ولت متر فشارمتوسط وفازمتر دوبل20kvPHV-20/DPM\n" +
            "\n" +
            "ولت متر فشارمتوسط وفازمتر دوبل 33kvPHV-33/DPM\n" +
            "\n" +
            "آمپرمتر فشار متوسط خط هواییPHA-300/OHL\n" +
            "\n" +
            "دستگاه تستر مقاومت اهمی ترانسفورماتورM1210CM\n" +
            "\n" +
            " \n" +
            "\n" +
            "نموده و توليدات اين شركت با كاربردها و مزاياي برجسته اقتصادي در سطح خرد و كلان كشور همسو با اهداف سازماني وزارت نيرو وارد بازار فروش گرديد.\n" +
            "\n" +
            "پارامترهايي نظير قيمت تمام شده،  ارائه خدمات پس از فروش و ضمانتهاي لازم و از همه بالاتر درج نظرات كاربردي متخصصين برق در ساخت دستگاه از جمله عواملي است كه تستر فشارقوي ساخت شركت مهندسي معراج در رقابت با محصولات خارجي با استقبال خوبي روبرو شده است.\n" +
            "\n" +
            "لازم بذكر است شركت مهندسي معراج به موازات ساخت، توليد و در راستاي تحقيقات، همواره خود را ملزم به ارتقاء كيفي وكمي محصولات دانسته و در انجام اين امر از هيچگونه تلاشي  فروگزار نمي نمايد.\n" +
            "\n" +
            "\n";

    public  void about_us()
    {
        AlertDialog.Builder gps_dialog=new AlertDialog.Builder(MainActivity.this);
        gps_dialog
                .setCancelable(false)
                .setMessage(abuote_us)
                .setPositiveButton("بستن",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

//            Intent browserIntent =
//                    new Intent(Intent.ACTION_VIEW,
//                            Uri.parse("https://telegram.me/MicroDroidPrj"));
//            startActivity(browserIntent);
                                dialog.cancel();

                            }
                        });
//            .setNegativeButton("NO", new DialogInterface.OnClickListener() {
//        @Override
//        public void onClick(DialogInterface dialog, int which) {
//            dialog.cancel();
//        }
        // });
        AlertDialog _alert=gps_dialog.create();
        _alert.setTitle("درباره ما");
        _alert.show();

    }


    //------------------------------------------------------------------------------------------------

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.connect_ip_port) {
//            connect_to_wifi();
//
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {//on Navigation Item Selected
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        int titleid=0;
        if (id == R.id.nav_home) {
            fragment = new DemoFragment();//Selected Demo Fragment
            titleid=1;
        }

        else if (id == R.id.nav_wifi_off)
        {
            fragment = new DemoFragment();//Selected Demo Fragment
            titleid=2;
        }
        else if (id == R.id.nav_wifi_on)
        {
            fragment = new DemoFragment();
            titleid=3;
        }
        else if (id == R.id.nav_demo_mode)
        {
            fragment = new DemoFragment();//Selected Demo Fragment
            titleid=3;
        }


        else if (id == R.id.nav_about)
        {
            fragment = new DemoFragment();//Selected Demo Fragment

        }

        else if (id == R.id.content)
        {
            fragment = new DemoFragment();//Selected Demo Fragment
            about_us();
        }


        if (fragment != null) { //set title fragment
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
            setTitle(mNavigationDrawerItemTitles[titleid]);

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    private void applyFontToMenuItem(MenuItem mi) {//apply Font To Menu Item
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan(getApplicationContext(), "", getIranianSansFont(getApplicationContext())), 0, mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }



    public static Typeface getIranianSansFont(Context context) {//set font fanction get Iranian Sans Font
        if (iranianSansFont == null) {
            iranianSansFont = Typeface.createFromAsset(context.getAssets(), "fonts/iranian_sans.ttf");
        }
        return iranianSansFont;
    }





}