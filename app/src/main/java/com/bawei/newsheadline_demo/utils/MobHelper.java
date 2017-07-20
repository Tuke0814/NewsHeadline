package com.bawei.newsheadline_demo.utils;

import android.app.Activity;
import android.content.Context;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

/**
 * 创建人: tuwenxiao
 * 类描述:
 * 创建时间： 2017/7/13 on 15:15.
 */
public class MobHelper {
    Activity activity;

    public MobHelper(Activity activity) {
        this.activity = activity;
    }

    public static void MobMSM(Context context){
        RegisterPage registerPage = new RegisterPage();
        registerPage.setRegisterCallback(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
// 解析注册结果
                if (result == SMSSDK.RESULT_COMPLETE) {
                    @SuppressWarnings("unchecked")
                    HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
                    String country = (String) phoneMap.get("country");
                    String phone = (String) phoneMap.get("phone");

// 提交用户信息（此方法可以不调用）
//                    registerUser(country, phone);
                }
            }


        });
        registerPage.show(context);
//        ContactsPage contactsPage = new ContactsPage();
//        contactsPage.show(context);
    }

}
