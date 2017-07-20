package com.bawei.newsheadline_demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bawei.newsheadline_demo.Data.DownLoad;
import com.bawei.newsheadline_demo.R;

import java.util.ArrayList;
import java.util.List;

public class DownLoadActivity extends AppCompatActivity {
    private List<DownLoad> list;
    private String namelist[] = {"推荐","推荐","推荐","推荐","推荐","推荐","推荐","推荐",
            "推荐","推荐","推荐","推荐","推荐","推荐","推荐","推荐","推荐","推荐","推荐","推荐",};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_load);
        ListView lv = (ListView) findViewById(R.id.downlist);
        ImageView img = (ImageView) findViewById(R.id.lefticon);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DownLoadActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        list = new ArrayList<>();
        for (int i = 0; i<20 ; i++) {
            DownLoad beans = new DownLoad();
            beans.names = namelist[i]+"";
            list.add(beans);
        }
        lv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                ViewHolder holder = null;
                if(convertView==null){

                    convertView = View.inflate(DownLoadActivity.this,R.layout.download_item,null);
                    holder = new ViewHolder();
                    holder.tv = (TextView) convertView.findViewById(R.id.tvname);
                    holder.box = (CheckBox) convertView.findViewById(R.id.chebox);
                    convertView.setTag(holder);
                }else{
                    holder = (ViewHolder) convertView.getTag();
                }

                holder.tv.setText(list.get(position).names);
                holder.box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        list.get(position).ischeck=isChecked;
                    }
                });
                holder.box.setChecked(list.get(position).ischeck);


                return convertView;
            }
            class ViewHolder{
                TextView tv;
                CheckBox box;
            }
        });

    }
}
