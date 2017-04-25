package com.newsapp.base.methu.demo_news_app.fragments;

/**
 * Created by Methu on 7/3/2015.
 */

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Capture on 6/25/2015.
 */
public class MenuPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[] { "All Articals", "Technology" };
    private Context context;
    private String f;

    public MenuPagerAdapter(FragmentManager fm, Context context, String f) {
        super(fm);
        this.f = f;
        this.context = context;
    }



//    public SampleFragmentPagerAdapter(FragmentManager fm, Context context) {
//        super(fm);
//        this.context = context;
//    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position)
    {
        if (position == 0) {
            return Fragment_Articals.newInstance();
        }
        else
        {
            return Fragment_Technology.newInstance();
        }

    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}