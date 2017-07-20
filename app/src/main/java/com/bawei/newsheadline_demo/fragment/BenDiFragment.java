package com.bawei.newsheadline_demo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.newsheadline_demo.R;
import com.example.city_picker.CityListActivity;

/**
 * 创建人: tuwenxiao
 * 类描述:
 * 创建时间： 2017/7/9 on 19:44.
 */
public class BenDiFragment extends Fragment {

    private TextView tvcity;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bendi_fragment,null);
        tvcity = (TextView) v.findViewById(R.id.city);
        tvcity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CityListActivity.startCityActivityForResult(getActivity());
            }
        });
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CityListActivity.REQUEST_CODE&& resultCode==CityListActivity.RESULT_CODE){
            String citys = data.getStringExtra(CityListActivity.RESULT_KEY);
            tvcity.setText(citys);
        }
    }
}
