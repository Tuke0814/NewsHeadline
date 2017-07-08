package com.bawei.newsheadline_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.umeng.socialize.UMShareAPI;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @ViewInject(R.id.tablayout)
    private TabLayout tabLayout;

    @ViewInject(R.id.pager)
    private ViewPager pager;
    private String[] title = {"主页","推荐","新闻","视频","搞笑","军事","历史"};
    private SlidingMenu menu;
    private UMShareAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initTab();
        initMenu();
        initUMeng();
    }

    private void initUMeng() {
        MyApplication app = (MyApplication) getApplication();
        api = app.getUmShareAPI();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        api.onActivityResult(requestCode,resultCode,data);
    }

    private void initMenu() {
        menu = new SlidingMenu(this);
        menu.setMenu(R.layout.left_slidingmenu);

        menu.setMode(SlidingMenu.LEFT);
//        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        menu.setShadowDrawable(R.mipmap.ic_launcher);
        menu.setBehindWidth(300);
        menu.attachToActivity(MainActivity.this,SlidingMenu.SLIDING_CONTENT);

        menu.setFadeDegree(0.35f);


    }

    private void initTab() {
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                NewFragment fragment = new NewFragment();
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

    @Override
    public void onClick(View v) {
        UmengHeper.getAuthQQ(MainActivity.this,api,MainActivity.this);
    }
    public void onClick1(View v){
        UmengHeper.getAuthWEIXIN(MainActivity.this,api,MainActivity.this);
    }

}
