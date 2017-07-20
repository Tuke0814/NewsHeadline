package com.bawei.newsheadline_demo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.newsheadline_demo.R;

/**
 * 创建人: tuwenxiao
 * 类描述:
 * 创建时间： 2017/7/9 on 19:24.
 */
public class LeftAdapter extends BaseAdapter {
    Context context;

    public LeftAdapter(Context context) {
        this.context = context;
    }

    private String[] names = {"好友动态", "反馈", "活动", "收藏", "我的话题" ,"商城"};
    int[] imgs = {R.drawable.menu_haoyoudongtai, R.drawable.menu_fanying,
            R.drawable.menu_huodong, R.drawable.menu_soucan,
            R.drawable.menu_wodehuati ,R.drawable.menu_shangchang};

    @Override
    public int getCount() {
        return names.length;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView ==null){
            convertView = View.inflate(context,R.layout.leftslidinglist,null);
            holder = new ViewHolder();
            holder.tvname = (TextView) convertView.findViewById(R.id.menu_names);
            holder.image = (ImageView) convertView.findViewById(R.id.menu_images);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvname.setText(names[position]);
        holder.image.setImageResource(imgs[position]);
        return convertView;
    }
    class ViewHolder{
        TextView tvname;
        ImageView image;
    }
}
