package com.newsapp.base.methu.demo_news_app.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.newsapp.base.methu.demo_news_app.R;
import com.newsapp.base.methu.demo_news_app.activities.DashboardActivity;
import com.newsapp.base.methu.demo_news_app.activities.MainActivity;
import com.newsapp.base.methu.demo_news_app.adapters.MySingleton;
import com.newsapp.base.methu.demo_news_app.adapters.News_Adapter;
import com.newsapp.base.methu.demo_news_app.globals.Globals;
import com.newsapp.base.methu.demo_news_app.models.News_Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Methu on 4/24/2017.
 */

public class Fragment_Articals extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    static ListView _ListViewNews;
    static News_Adapter news_adapter;
    static ArrayList<News_Model> _newsDataArray;
    private static SwipeRefreshLayout _swipeRefreshLayout;


    public static Fragment_Articals newInstance(){
        Fragment_Articals fa = new Fragment_Articals();

        return fa;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_all_articals, container, false);
        _ListViewNews = (ListView) rootView.findViewById(R.id.lv_news_dashboard);
        _swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_layout);

        _newsDataArray = new ArrayList<>();
        news_adapter = new News_Adapter(getActivity(), _newsDataArray);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        _ListViewNews.setAdapter(news_adapter);

        _ListViewNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                News_Model nm = _newsDataArray.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("image", nm.getUrlToImage());
                bundle.putInt("position", position);
                bundle.putString("title", nm.getTitle());
                bundle.putString("author", nm.getAuthor());
                bundle.putString("description", nm.getDescription());
                bundle.putString("publishedAt", nm.getPublishedAt());
                bundle.putString("urlToImage", nm.getUrlToImage());
                bundle.putString("url", nm.getUrl());

                Intent i = new Intent(getActivity(), MainActivity.class);
                i.putExtra("image", nm.getUrlToImage());
                i.putExtras(bundle);
                startActivity(i);
            }
        });
        _swipeRefreshLayout.post(new Runnable() {
                                     @Override
                                     public void run() {
              _swipeRefreshLayout.setRefreshing(true);
              fetchJsonResponse(Globals.articals);
           }
        });

        _swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void fetchJsonResponse(String path) {
        _swipeRefreshLayout.setRefreshing(true);
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, Globals.path+path, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Tag", "" + response);

                        JSONArray jArray = null;

                        if (response != null) {
                            try {
                                jArray = response.getJSONArray("articles");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            for (int i = 0; i < jArray.length(); i++) {
                                try {
                                    JSONObject jobj = jArray.getJSONObject(i);
                                    News_Model dm = new News_Model();
                                    dm.setAuthor(jobj.getString("author"));
                                    dm.setDescription(jobj.getString("description"));
                                    dm.setPublishedAt(jobj.getString("publishedAt"));
                                    dm.setTitle(jobj.getString("title"));
                                    dm.setUrlToImage(jobj.getString("urlToImage"));
                                    dm.setUrl(jobj.getString("url"));
                                    _newsDataArray.add(dm);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }

                        news_adapter.notifyDataSetChanged();
                        _swipeRefreshLayout.setRefreshing(false);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                _swipeRefreshLayout.setRefreshing(false);
                VolleyLog.e("Error: ", error.getMessage());
            }
        });

		/* Add your Requests to the RequestQueue to execute */
        MySingleton.getInstance(getActivity().getApplicationContext()).addToRequestQueue(req);
    }


    @Override
    public void onRefresh() {
        fetchJsonResponse(Globals.articals);
    }
}
