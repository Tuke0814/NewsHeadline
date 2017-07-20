package com.bawei.newsheadline_demo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.newsheadline_demo.MyData.ReData;
import com.bawei.newsheadline_demo.R;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * 创建人: tuwenxiao
 * 类描述:
 * 创建时间： 2017/7/15 on 8:15.
 */
public class ReAdapter  extends BaseAdapter{

    Context context;
    private List<ReData.DataBean> list;

    public ReAdapter(Context context, List<ReData.DataBean> list) {
        this.context = context;
        this.list = list;
    }
  final   int TYPE0 = 0;
  final   int TYPE3= 1;
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
    public int getItemViewType(int position) {
        return list.get(position).getImage_list().size()==0?0:1;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        ViewHolder holder = null;
        if(convertView==null){
            holder = new ViewHolder();
            switch (type){
                case TYPE0:
                    convertView = View.inflate(context, R.layout.tuijan_item,null);
                    break;
                case TYPE3:
                    convertView = View.inflate(context, R.layout.tuijian_item3,null);
                    holder.image = (ImageView) convertView.findViewById(R.id.image);
                    holder.image2 = (ImageView) convertView.findViewById(R.id.image2);
                    holder.image3 = (ImageView) convertView.findViewById(R.id.image3);
                    break;
            }
            holder.title = (TextView)convertView.findViewById(R.id.title);
            holder.source = (TextView)convertView.findViewById(R.id.source);
            holder.com = (TextView)convertView.findViewById(R.id.comment);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        switch (type){
            case TYPE3:
                Glide.with(context).load(list.get(position).getImage_list().get(0).getUrl()).into(holder.image);
                Glide.with(context).load(list.get(position).getImage_list().get(1).getUrl()).into(holder.image2);
                Glide.with(context).load(list.get(position).getImage_list().get(2).getUrl()).into(holder.image3);
                break;
        }
        holder.title.setText(list.get(position).getTitle());
        holder.source.setText(list.get(position).getSource());
        holder.com.setText(String.valueOf(list.get(position).getComment_count()));
        return convertView;
    }
    class ViewHolder{
        TextView title,source,com;
        ImageView image,image2,image3;
    }
}
