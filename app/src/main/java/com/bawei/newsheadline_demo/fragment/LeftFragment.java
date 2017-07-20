package com.bawei.newsheadline_demo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bawei.newsheadline_demo.R;
import com.bawei.newsheadline_demo.adapter.LeftAdapter;
import com.bawei.newsheadline_demo.utils.MobHelper;

/**
 * 创建人: tuwenxiao
 * 类描述:
 * 创建时间： 2017/7/10 on 16:49.
 */
public class LeftFragment extends Fragment {
    private int theme = R.style.AppTheme;
    private String[] names = {"好友动态", "反馈", "活动", "收藏", "我的话题" ,"商城"};



    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.left_slidingmenu,null);
        ListView lv = (ListView) v.findViewById(R.id.home_menu_lv);
        lv.setAdapter(new LeftAdapter(getActivity()));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),""+names[position],Toast.LENGTH_LONG).show();
            }
        });
        ImageView image_Msm = (ImageView) v.findViewById(R.id.image_shouji);
        image_Msm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MobHelper.MobMSM(getActivity());
            }
        });

        return v;
    }

}
