package com.newsapp.base.methu.demo_news_app.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.newsapp.base.methu.demo_news_app.R;
import com.newsapp.base.methu.demo_news_app.models.News_Model;
import com.newsapp.base.methu.demo_news_app.models.Source_Model;

import java.util.List;

/**
 * Created by Methu on 4/23/2017.
 */

public class Source_Adapter extends BaseAdapter {
    Context context;

    protected List<Source_Model> listC;
    LayoutInflater inflater;

    public Source_Adapter(Context context, List<Source_Model> listComments) {
        this.listC = listComments;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }
    @Override
    public int getCount() {
        return listC.size();
    }

    @Override
    public Object getItem(int position) {
        return listC.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {

            holder = new ViewHolder();
            convertView = this.inflater.inflate(R.layout.single_source_news_item,
                    parent, false);

            holder.tv_name = (TextView) convertView
                    .findViewById(R.id.name);
            holder.tv_description = (TextView) convertView
                    .findViewById(R.id.description);
            holder.tv_news_source = (TextView) convertView
                    .findViewById(R.id.tv_news_source);
            holder.tv_news_id = (TextView) convertView
                    .findViewById(R.id.tv_news_id);



            convertView.setTag(holder);


        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Source_Model c = listC.get(position);



        holder.tv_name.setText(c.getName().toString().toUpperCase());
        holder.tv_description.setText(c.getDescription());
        holder.tv_news_source.setText(c.getCategory());
        holder.tv_news_id.setText(c.getUrl());

        return convertView;
    }

    private class ViewHolder {
        TextView tv_name, tv_description, tv_news_id, tv_news_source;
        ImageView imgPic;
    }
}
