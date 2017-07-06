package com.bawei.newsheadline_demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

/**
 * 创建人: tuwenxiao
 * 类描述:
 * 创建时间： 2017/7/6 on 19:21.
 */
@ContentView(R.layout.news_fragment)
public class NewFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        return x.view().inject(this,inflater,container);
    }
}
