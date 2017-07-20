package com.bawei.newsheadline_demo.application;

import com.mob.MobApplication;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import org.xutils.x;

import cn.jpush.android.api.JPushInterface;

/**
 * 创建人: tuwenxiao
 * 类描述:
 * 创建时间： 2017/7/6 on 19:40.
 */
public class MyApplication extends MobApplication{

    private UMShareAPI umShareAPI;

    {
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
        PlatformConfig.setQQZone("1106198509","Z9wLCcdD7dvmypub");
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(false);
        JPushInterface.init(this);
        umShareAPI = UMShareAPI.get(this);
    }

    public UMShareAPI getUmShareAPI(){
        return umShareAPI;
    }

}
