package com.bawei.newsheadline_demo;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

/**
 * 创建人: tuwenxiao
 * 类描述:
 * 创建时间： 2017/7/8 on 10:35.
 */
public class UmengHeper {
    Activity activity;

    public UmengHeper(Activity activity) {
        this.activity = activity;
    }

    public static void getAuthWEIXIN(final Context context, UMShareAPI api,Activity activity){
        if(api.isInstall(activity, SHARE_MEDIA.WEIXIN)){
            Toast.makeText(context, "已经安装微信", Toast.LENGTH_SHORT).show();
            api.doOauthVerify(activity, SHARE_MEDIA.WEIXIN, new UMAuthListener() {
                @Override
                public void onStart(SHARE_MEDIA share_media) {

                }

                @Override
                public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                    Toast.makeText(context, "微信登陆成功", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                    Toast.makeText(context, "微信登陆错误", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancel(SHARE_MEDIA share_media, int i) {
                    Toast.makeText(context, "用户取消登陆", Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            Toast.makeText(context, "没有安装微信", Toast.LENGTH_SHORT).show();
        }
    }


    public static void getAuthQQ(final Context context, UMShareAPI api,Activity activity){
        if(api.isInstall(activity, SHARE_MEDIA.QQ)){
            Toast.makeText(context, "已经安装QQ", Toast.LENGTH_SHORT).show();
            api.doOauthVerify(activity, SHARE_MEDIA.WEIXIN, new UMAuthListener() {
                @Override
                public void onStart(SHARE_MEDIA share_media) {

                }

                @Override
                public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                    Toast.makeText(context, "QQ登陆成功", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                    Toast.makeText(context, "QQ登陆错误", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancel(SHARE_MEDIA share_media, int i) {
                    Toast.makeText(context, "用户取消登陆", Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            Toast.makeText(context, "没有安装QQ", Toast.LENGTH_SHORT).show();
        }
    }
}
