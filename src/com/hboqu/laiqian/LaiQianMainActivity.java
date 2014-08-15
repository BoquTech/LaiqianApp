package com.hboqu.laiqian;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

@SuppressWarnings("deprecation")
public class LaiQianMainActivity extends Activity implements
        OnCheckedChangeListener, OnTabChangeListener {

    public static String TAG = "LaiQianActivity";

    public static final String TAB_COMMUNITY = "tab_community";
    public static final String TAB_TRADE = "tab_trade";
    public static final String TAB_EXERISE = "tab_exerise";
    public static final String TAB_SETTING = "tab_setting";

    public static final String PREFS_LAIQIAN_DEFAULT = "laiqian_default";
    public static final String PREFS_SELECTED_TAB = "selected_tab";

    private TabHost mTabHost;
    private String mSelectedTab = "";
    private RadioButton mCommunityBar;
    private RadioButton mExeriseBar;
    private RadioButton mTradeBar;
    private RadioButton mSettingBar;
    Resources mResource;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.laiqian_main_layout);
//        ViewServer.get(this).addWindow(this);
        mResource = this.getResources();
        mTabHost = (TabHost) this.findViewById(R.id.tabhost);
        LocalActivityManager groupActivity = new LocalActivityManager(this, false);
        groupActivity.dispatchCreate(savedInstanceState);
        mTabHost.setup(groupActivity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initRadioButtonTabs();
        initTabHost();
        initSelectedTab();
    }

    private void initTabHost() {

        mTabHost.addTab(mTabHost.newTabSpec(TAB_COMMUNITY).setIndicator(TAB_COMMUNITY).setContent(
                new Intent(this, CommunityActivity.class)));
        mTabHost.addTab(mTabHost.newTabSpec(TAB_TRADE).setIndicator(TAB_TRADE).setContent(
                new Intent(this, TradeActivity.class)));
        mTabHost.addTab(mTabHost.newTabSpec(TAB_EXERISE).setIndicator(TAB_EXERISE).setContent(
                new Intent(this, ExeriseActivity.class)));
        mTabHost.addTab(mTabHost.newTabSpec(TAB_SETTING).setIndicator(TAB_SETTING).setContent(
                new Intent(this, SettingActivity.class)));
        mTabHost.setOnTabChangedListener(this);
    }

    private void initSelectedTab() {
        SharedPreferences prefs = getSharedPreferences(PREFS_LAIQIAN_DEFAULT, MODE_PRIVATE);
        mSelectedTab = prefs.getString(PREFS_SELECTED_TAB, TAB_COMMUNITY);
        if (TAB_COMMUNITY.equals(mSelectedTab)) {
            mTabHost.setCurrentTabByTag(TAB_COMMUNITY);
            mCommunityBar.setChecked(true);
        } else if (TAB_TRADE.equals(mSelectedTab)) {
            mTabHost.setCurrentTabByTag(TAB_TRADE);
            mTradeBar.setChecked(true);
        } else if (TAB_EXERISE.equals(mSelectedTab)) {
            mTabHost.setCurrentTabByTag(TAB_EXERISE);
            mExeriseBar.setChecked(true);
        } else if (TAB_SETTING.equals(mSelectedTab)) {
            mTabHost.setCurrentTabByTag(TAB_SETTING);
            mSettingBar.setChecked(true);
        }
    }

    private void initRadioButtonTabs() {
        mCommunityBar = (RadioButton) findViewById(R.id.tab_community);
        mExeriseBar = (RadioButton) findViewById(R.id.tab_exerise);
        mTradeBar = (RadioButton) findViewById(R.id.tab_trade);
        mSettingBar = (RadioButton) findViewById(R.id.tab_setting);
        mCommunityBar.setOnCheckedChangeListener(this);
        mExeriseBar.setOnCheckedChangeListener(this);
        mTradeBar.setOnCheckedChangeListener(this);
        mSettingBar.setOnCheckedChangeListener(this);
    }

    @Override
    protected void onDestroy() {
        SharedPreferences prefs = getSharedPreferences(PREFS_LAIQIAN_DEFAULT,
                MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PREFS_SELECTED_TAB, mSelectedTab);
        editor.commit();
        super.onDestroy();
    }

    @Override
    public void onCheckedChanged(CompoundButton radioButtonTab, boolean isChecked) {
        if (isChecked) {
            switch (radioButtonTab.getId()) {
                case R.id.tab_community: {
                    mSelectedTab = TAB_COMMUNITY;
                    mTabHost.setCurrentTabByTag(TAB_COMMUNITY);
                    break;
                }
                case R.id.tab_trade: {
                    mSelectedTab = TAB_TRADE;
                    mTabHost.setCurrentTabByTag(TAB_TRADE);
                    break;
                }
                case R.id.tab_exerise: {
                    mSelectedTab = TAB_EXERISE;
                    mTabHost.setCurrentTabByTag(TAB_EXERISE);
                    break;
                }
                case R.id.tab_setting: {
                    mSelectedTab = TAB_SETTING;
                    mTabHost.setCurrentTabByTag(TAB_SETTING);
                    break;
                }
                default:
                    break;
            }
        }
    }

    @Override
    public void onTabChanged(String selectedTab) {
        if (TAB_COMMUNITY.equals(selectedTab)) {
            mTabHost.setCurrentTabByTag(TAB_COMMUNITY);
        } else if (TAB_TRADE.equals(selectedTab)) {
            mTabHost.setCurrentTabByTag(TAB_TRADE);
        } else if (TAB_EXERISE.equals(selectedTab)) {
            mTabHost.setCurrentTabByTag(TAB_EXERISE);
        } else if (TAB_SETTING.equals(selectedTab)) {
            mTabHost.setCurrentTabByTag(TAB_SETTING);
        }
    }
}