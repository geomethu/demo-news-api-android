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

import java.util.List;

/**
 * Created by Methu on 4/23/2017.
 */

public class News_Adapter extends BaseAdapter {
    Context context;

    protected List<News_Model> listC;
    LayoutInflater inflater;

    public News_Adapter(Context context, List<News_Model> listComments) {
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
            convertView = this.inflater.inflate(R.layout.single_line_news_item,
                    parent, false);

            holder.tv_Title = (TextView) convertView
                    .findViewById(R.id.tv_Title);
            holder.imgPic = (ImageView) convertView
                    .findViewById(R.id.imgPic);
            holder.tv_news_source = (TextView) convertView
                    .findViewById(R.id.tv_news_source);
            holder.tv_news_date = (TextView) convertView
                    .findViewById(R.id.tv_news_date);



            convertView.setTag(holder);


        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        News_Model c = listC.get(position);
        String pic_path = c.getUrlToImage();


        holder.tv_Title.setText(c.getTitle().toString().toUpperCase());
        holder.tv_news_source.setText(c.getAuthor());
        Log.e("#######@@@@@@@@   ", "   "+pic_path);
        Glide
                .with(context)
                .load(pic_path)
                .into(holder.imgPic);

        return convertView;
    }

    private class ViewHolder {
        TextView tv_Title, tv_news_source, tv_news_date;
        ImageView imgPic;
    }
}
