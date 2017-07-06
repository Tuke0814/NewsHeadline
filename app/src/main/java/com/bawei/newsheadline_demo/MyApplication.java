package com.bawei.newsheadline_demo;

import android.app.Application;

import org.xutils.x;

/**
 * 创建人: tuwenxiao
 * 类描述:
 * 创建时间： 2017/7/6 on 19:40.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(false);
    }
}
