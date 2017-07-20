package com.bawei.newsheadline_demo.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.andy.library.ChannelActivity;
import com.andy.library.ChannelBean;
import com.bawei.newsheadline_demo.R;
import com.bawei.newsheadline_demo.application.MyApplication;
import com.bawei.newsheadline_demo.fragment.BenDiFragment;
import com.bawei.newsheadline_demo.fragment.LeftFragment;
import com.bawei.newsheadline_demo.fragment.RecommendFragment;
import com.bawei.newsheadline_demo.utils.UmengHeper;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.umeng.socialize.UMShareAPI;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
//    @ViewInject(R.id.tablayout)
    private TabLayout tabLayout;

//    @ViewInject(R.id.toutitle)
    private ImageView touimg;

//    @ViewInject(R.id.pager)
    private ViewPager pager;

//    @ViewInject(R.id.tv_times)
    private TextView tvtimes;

//    @ViewInject(R.id.tv_todays)
    private TextView tvtodays;
    private int theme = R.style.AppTheme;

    private String[] title = {"推荐","热点","本地","视频","社会","娱乐","科技","汽车","体育","财经","军事","国际","段子","趣图","健康","美女"};
    private SlidingMenu menu;
    private SlidingMenu menu1;
    private UMShareAPI api;
    private LeftFragment content;
    private LeftFragment home;
    private TextView tvnight;
    private List<ChannelBean> list;
    private String jsonstr;
    private SharedPreferences sp;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            theme = savedInstanceState.getInt("theme");
            setTheme(theme);
        }
        setContentView(R.layout.activity_main);
        sp =getSharedPreferences("setting",MODE_PRIVATE);
        init();
        View v = View.inflate(this,R.layout.left_slidingmenu,null);
        tvnight = (TextView) v.findViewById(R.id.tv_nigth);
        TextView tvdownload = (TextView) v.findViewById(R.id.download);
        tvnight.setOnClickListener(this);
        tvdownload.setOnClickListener(this);
        initTime();
        initTab();
        initMenu();
        initMenuright();
        initUMeng();
    }

    private void initMenuright() {
        menu1 =new SlidingMenu(this);
        menu1.setMenu(R.layout.right_slidingmenu);
        menu1.setMode(SlidingMenu.RIGHT);
//        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        menu1.setShadowDrawable(R.mipmap.ic_launcher);
        menu1.setBehindWidth(350);
        menu1.attachToActivity(MainActivity.this,SlidingMenu.SLIDING_CONTENT);
        menu1.setFadeDegree(0.35f);
    }

    private void init() {
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        touimg = (ImageView) findViewById(R.id.toutitle);
        pager = (ViewPager) findViewById(R.id.pager);
        tvtimes = (TextView)findViewById(R.id.tv_times);
        tvtodays =(TextView)findViewById(R.id.tv_todays);
    }


    private void initTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String t = format.format(new Date());
        tvtimes.setText(t);
        // 星期
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        String today = null;
        if (day == 2) {
            today = "星期一";
        } else if (day == 3) {
            today = "星期二";
        } else if (day == 4) {
            today = "星期三";
        } else if (day == 5) {
            today = "星期四";
        } else if (day == 6) {
            today = "星期五";
        } else if (day == 7) {
            today = "星期六";
        } else if (day == 1) {
            today = "星期日";
        }
        tvtodays.setText("  " + today+"\t\t\t\t\t\t今天");
    }

    private void initUMeng() {
        MyApplication app = (MyApplication) getApplication();
        api = app.getUmShareAPI();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        api.onActivityResult(requestCode,resultCode,data);
        if(requestCode==ChannelActivity.REQUEST_CODE&&resultCode==ChannelActivity.RESULT_CODE){
            jsonstr = data.getStringExtra(ChannelActivity.RESULT_JSON_KEY);
            Log.d("tag",""+ jsonstr);
            sp.edit().putString("setting_str",jsonstr).commit();
        }
    }

    private void initMenu() {
        menu =new SlidingMenu(this);
       menu.setMenu(R.layout.left_slidingmenu);
        menu.setMode(SlidingMenu.LEFT);
//        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        menu.setShadowDrawable(R.mipmap.ic_launcher);
        menu.setBehindWidth(350);
        menu.attachToActivity(MainActivity.this,SlidingMenu.SLIDING_CONTENT);
        menu.setFadeDegree(0.35f);

        home = new LeftFragment();
        content = new LeftFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.home_fragment, home, "home").commit();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.home_fragment, content, "content").commit();

    }

    private void initTab() {
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {

                Fragment fragment = null;
                switch (position){
                    case 0:
                        fragment = new RecommendFragment();
                        break;
                    case 1:
                        fragment = new RecommendFragment();
                        break;
                    case 2:
                        fragment = new BenDiFragment();
                        break;
                    case 3:
                        fragment = new RecommendFragment();
                        break;
                    case 4:
                        fragment = new RecommendFragment();
                        break;
                    case 5:
                        fragment = new RecommendFragment();
                        break;
                    case 6:
                        fragment = new RecommendFragment();
                        break;
                    case 7:
                        fragment = new RecommendFragment();
                        break;
                    case 8:
                        fragment = new RecommendFragment();
                        break;
                    case 9:
                        fragment = new RecommendFragment();
                        break;
                    case 10:
                        fragment = new RecommendFragment();
                        break;
                    case 11:
                        fragment = new RecommendFragment();
                        break;
                    case 12:
                        fragment = new RecommendFragment();
                        break;
                    case 13:
                        fragment = new RecommendFragment();
                        break;
                    case 14:
                        fragment = new RecommendFragment();
                        break;

                    case 15:
                        fragment = new RecommendFragment();
                        break;
                    case 16:
                        fragment = new RecommendFragment();
                        break;
                    case 17:
                        fragment = new RecommendFragment();
                        break;
                    case 18:
                        fragment = new RecommendFragment();
                        break;
                    case 19:
                        fragment = new RecommendFragment();
                        break;
                }
                return fragment;
            }

            @Override
            public int getCount() {
                return title.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return title[position];
            }
        });
        tabLayout.setupWithViewPager(pager);
    }

    //qq登录
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_qq:
                UmengHeper.getAuthQQ(MainActivity.this,api,MainActivity.this);
                break;
            case R.id.image_weibo:
                UmengHeper.getAuthXL(MainActivity.this,api,MainActivity.this);
                break;
            case R.id.tv_nigth:
                Toast.makeText(MainActivity.this,"dsd",Toast.LENGTH_LONG).show();
                theme = (theme == R.style.AppTheme) ? R.style.NightAppTheme : R.style.AppTheme;
//                tvnight.setCompoundDrawables(null,getResources().getDrawable(R.drawable.dayicon_leftdrawer_pressed_night),null,null);
                recreate();
                break;
            case R.id.download:
                Intent intent = new Intent(MainActivity.this,DownLoadActivity.class);
                startActivity(intent);
                break;
            case R.id.tvs:
                jsonstr = sp.getString("setting_str",null);
                if(jsonstr==null){
                    list= new ArrayList<ChannelBean>();

                    for (int i = 0; i <15 ; i++) {
                        ChannelBean bean = null;
                        if(i<5){
                            bean = new ChannelBean("item-"+i,true);
                        }else{
                            bean = new ChannelBean("item-"+i,false);
                        }
                        list.add(bean);
                    }
                    ChannelActivity.startChannelActivity(MainActivity.this,list);
                }else{
                    ChannelActivity.startChannelActivity(MainActivity.this,jsonstr);
                }
                break;
            case R.id.weititle:

                break;
        }

    }
    //新浪
//    public void onClickSMS(View v){
//
//    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("theme", theme);
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        theme = savedInstanceState.getInt("theme");
    }
    public void tou(View v){
       menu.toggle();
    }
    public void foot(View v){
        menu1.toggle();
    }


}
