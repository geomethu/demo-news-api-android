package com.newsapp.base.methu.demo_news_app.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.newsapp.base.methu.demo_news_app.R;

public class MainActivity extends AppCompatActivity {
    private WebView webview;
    private static final String TAG = "Main";
    private ProgressDialog progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bundle = getIntent().getExtras();
        Intent intent = getIntent();
        String image_path = getIntent().getExtras().getString("image");
        TextView tvTitle = (TextView) findViewById(R.id.tv_Title);
        TextView tvAuthor = (TextView) findViewById(R.id.tv_author);
        TextView tvDate = (TextView) findViewById(R.id.tv_published);
        TextView tvdescription = (TextView) findViewById(R.id.tv_description);
        tvTitle.setText(bundle.getString("title"));
        tvAuthor.setText("By "+bundle.getString("author"));
        tvDate.setText(bundle.getString("publishedAt"));
        tvdescription.setText(bundle.getString("description"));

        ImageView image = (ImageView) findViewById(R.id.news_image);
        Glide.with(MainActivity.this).load(image_path)
                .into(image);

        this.webview = (WebView)findViewById(R.id.webview);

        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(false);
        webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);


        webview.loadUrl(bundle.getString("url"));

    }
}
