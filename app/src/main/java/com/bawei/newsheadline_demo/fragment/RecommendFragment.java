package com.bawei.newsheadline_demo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.bawei.newsheadline_demo.MyData.ReData;
import com.bawei.newsheadline_demo.R;
import com.bawei.newsheadline_demo.activity.DetailsActivity;
import com.bawei.newsheadline_demo.adapter.ReAdapter;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import me.maxwin.view.XListView;

/**
 * 创建人: tuwenxiao
 * 类描述:
 * 创建时间： 2017/7/6 on 19:21.
 */

public class RecommendFragment extends Fragment implements XListView.IXListViewListener{
int count = 1;
    private String urlpath = "http://ic.snssdk.com/2/article/v25/stream/?" +
            "min_behot_time=1455521444&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82&bd_latitude=40.049317&" +
            "bd_longitude=116.296499&bd_loc_time=1455521401&loc_mode=5&lac=4527&" +
            "cid=28883&iid=3642583580&device_id=11131669133&ac=wifi&channel=baidu&aid=13&" +
            "app_name=news_article&version_code=460&device_platform=android&" +
            "device_type=SCH-I919U&os_api=19&os_version=4.4.2&uuid=285592931621751&" +
            "openudid=AC9E172CE2490000&count="+count;

    private List<ReData.DataBean> list = new ArrayList<>();
    private XListView lv;
    private ReAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.news_fragment,null);
        lv = (XListView) v.findViewById(R.id.listviewss);
        initData();
        adapter = new ReAdapter(getActivity(),list);
        lv.setAdapter(adapter);
        lv.setPullLoadEnable(true);
        lv.setXListViewListener(this);
       lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent = new Intent(getActivity(), DetailsActivity.class);
               intent.putExtra("url",list.get(position-1).getUrl());
               startActivity(intent);
           }
       });
        return v;
    }

    private void initData() {
        RequestParams params = new RequestParams(urlpath);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("TAG",""+result);
                Gson gson = new Gson();
                ReData data  = gson.fromJson(result, ReData.class);
                list.addAll(data.getData());
                Stops();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Override
    public void onRefresh() {
            list.clear();
            count=1;
            initData();
            Stops();
    }

    public void Stops(){
        lv.stopLoadMore();
        lv.stopRefresh();

    }

    @Override
    public void onLoadMore() {
        count++;
        initData();
        Stops();
    }
}
