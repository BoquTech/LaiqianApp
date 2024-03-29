package com.hboqu.laiqian.adapter;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerFragmentAdapter extends FragmentPagerAdapter {

    @SuppressWarnings("unused")
    private static final String TAG = "ViewPagerFragmentAdapter";

    private static final String[] TITLE = new String[] { "美股", "港股", "中概股",
        "阿里", "百度", "腾讯", "嘉实", "博趣" };

    private ArrayList<Fragment> mFragmentsList;

    public ViewPagerFragmentAdapter(FragmentManager fragmentManager, ArrayList<Fragment> fragmentList) {
        super(fragmentManager);
        mFragmentsList = fragmentList;
    }

    @Override
    public Fragment getItem(int arg0) {
        return mFragmentsList.get(arg0);
    }

    @Override
    public int getCount() {
        return mFragmentsList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLE[position % mFragmentsList.size()];
    }

}
