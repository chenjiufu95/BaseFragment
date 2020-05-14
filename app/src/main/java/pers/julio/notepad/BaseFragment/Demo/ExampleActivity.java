package pers.julio.notepad.BaseFragment.Demo;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.android.material.tabs.TabLayout;

import pers.julio.notepad.SuperUtils.StatusBarUtils;
import pers.julio.notepad.BaseFragment.Base.BaseFragmentActivity;

public class ExampleActivity extends BaseFragmentActivity implements View.OnClickListener {
    private TextView mTitle;
    private pers.julio.notepad.BaseFragment.Demo.FragRoot mFragRoot;
    private boolean mIsShowFrag;

    public int dp2px(int dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, this.getResources().getDisplayMetrics());
    }
    @Override
    public int ContainerViewId() { return R.id.frag_container; }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        initTopbar(false);
        initTabs();
        initTabLayout();
        mFragRoot = new FragRoot();
        AddFirstFragment(savedInstanceState, mFragRoot);
    }
    private TabLayout mTabLayout;
    private void initTabLayout() {
        mTabLayout = findViewById(R.id.tb_frag_modes);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setTabTextColors(Color.WHITE,Color.GREEN);
        mTabLayout.setSelectedTabIndicatorColor(Color.GREEN);
        mTabLayout.setSelectedTabIndicatorHeight(5);
        //mTabLayout.setupWithViewPager(mContentVp);

        /*int tab_size = TAB_NAMES.length;
        for (int i = 0; i < tab_size; i++) {
            TabLayout.Tab itemTab = mTabLayout.newTab();
            itemTab.setTag(i);
            itemTab.setText(TAB_NAMES[i]);
            //View view = LayoutInflater.from(activity).inflate(R.layout.tab_item_text, null);
            //itemTab.setCustomView(view);
            mTabLayout.addTab(itemTab);
            if (i == mTabIndex) itemTab.select();
        }*/
        mTabLayout.getTabAt(0).select();
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }
            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int index = mTabLayout.getSelectedTabPosition();
                switch (index){
                    case 0:  // Show/Hide 模式
                        mIsShowFrag = true;
                        break;
                    case 1:  // Show/Hide 模式
                        mIsShowFrag = false;
                        break;
                    case 2:  // Add/Remove 模式
                        mIsShowFrag = false;
                        break;
                }
            }
        });
    }

    public void initTopbar(boolean isWhiteTheme) {        // 白色主题 & 有色主题
        StatusBarUtils.setLightStatusBar(this, isWhiteTheme);   // 使状态栏 的文字可见
        findViewById(R.id.topbar_root).setBackgroundColor(Color.TRANSPARENT); // 沉浸式 TopBar 透明 由背景决定显示

        mTitle = findViewById(R.id.topbar_title);
        mTitle.setTextColor(isWhiteTheme ? Color.BLACK : Color.WHITE); // 白色主题用黑色字体，有色主题，用白色字体
        mTitle.setText("Frag_root");

        ImageView back = findViewById(R.id.topbar_left);
        back.setImageResource(isWhiteTheme ? R.drawable.topbar_back_black : R.drawable.topbar_back_white);
        back.setOnClickListener(this);
        ImageView setBtn = findViewById(R.id.topbar_right);
        setBtn.setImageResource(isWhiteTheme ? R.drawable.topbar_more_black : R.drawable.topbar_more_white);
        setBtn.setOnClickListener(this);
        //setBtn.setVisibility(View.INVISIBLE);      // View.GONE 隐藏会占据位置 导致标题不居中
    }
    public void SetTitle(String title){ mTitle.setText(title); }
    private void goBack() {
        if(mCurFragment instanceof FragRoot){
            finish();
        }else {
            HideFragment();
            ChangeTitle();
        }
    }
    @Override
    public void onBackPressed() { goBack(); }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.topbar_left:
                goBack();
                break;
            case R.id.topbar_right:
                break;
            case R.id.tv_tab01:
                SwitchFragment(1);
                break;
            case R.id.tv_tab02:
                SwitchFragment(2);
                break;
            case R.id.tv_tab03:
                SwitchFragment(3);
                break;
            case R.id.tv_tab04:
                SwitchFragment(4);
                break;
        }
    }

    private void SwitchFragment(int index) {
        setSelected(index);
        switch (index){
            case 1:
                if(mIsShowFrag){
                    ShowFragment(new FragTest01());
                }else {
                    ReplaceFragment(new FragTest01());
                }
                break;
            case 2:
                if(mIsShowFrag){
                    ShowFragment(new FragTest02());
                }else {
                    ReplaceFragment(new FragTest02());
                }
                break;
            case 3:
                if(mIsShowFrag){
                    ShowFragment(new FragTest03());
                }else {
                    ReplaceFragment(new FragTest03());
                }
                break;
            case 4:
                if(mIsShowFrag){
                    ShowFragment(new FragTest04());
                }else {
                    ReplaceFragment(new FragTest04());
                }
                break;
        }
        ChangeTitle();
    }

    private void ChangeTitle() {
        if(mCurFragment instanceof FragRoot){
            SetTitle("Frag_root");
        }else if(mCurFragment instanceof FragTest01){
            SetTitle("Frag_test_01");
        }else if(mCurFragment instanceof FragTest02){
            SetTitle("Frag_test_02");
        }else if(mCurFragment instanceof FragTest03){
            SetTitle("Frag_test_03");
        }else if(mCurFragment instanceof FragTest04){
            SetTitle("Frag_test_04");
        }
    }

    private int TAB_ICON_SIZE = 20; //ColorUtil.dp2px(this, 20);
    private final int[] COLORS = new int[]{Color.WHITE, Color.parseColor("#33bbff")};
    private TextView mTab1, mTab2, mTab3, mTab4;

    private void initTabs() {
        TAB_ICON_SIZE = dp2px(25);
        findViewById(R.id.ll_tabs_layout).setBackgroundColor(Color.TRANSPARENT);
        mTab1 = findViewById(R.id.tv_tab01);
        mTab2 = findViewById(R.id.tv_tab02);
        mTab3 = findViewById(R.id.tv_tab03);
        mTab4 = findViewById(R.id.tv_tab04);
        mTab1.setOnClickListener(this);
        mTab2.setOnClickListener(this);
        mTab3.setOnClickListener(this);
        mTab4.setOnClickListener(this);
        setSelected(0);
    }

    @SuppressLint("NewApi")
    public void setSelected(int index) {
        resetBackground();
        switch (index) {
            case 1:
                mTab1.setTextColor(COLORS[1]);
                Drawable drawableTop1 = getResources().getDrawable(R.drawable.icon_tab01_selected);
                drawableTop1.setBounds(0, 0, TAB_ICON_SIZE, TAB_ICON_SIZE);
                mTab1.setCompoundDrawables(null, drawableTop1, null, null);
                break;
            case 2:
                mTab2.setTextColor(COLORS[1]);
                Drawable drawableTop2 = getResources().getDrawable(R.drawable.icon_tab02_selected);
                drawableTop2.setBounds(0, 0, TAB_ICON_SIZE, TAB_ICON_SIZE);
                mTab2.setCompoundDrawables(null, drawableTop2, null, null);
                break;
            case 3:
                mTab3.setTextColor(COLORS[1]);
                Drawable drawableTop3 = getResources().getDrawable(R.drawable.icon_tab03_selected);
                drawableTop3.setBounds(0, 0, TAB_ICON_SIZE, TAB_ICON_SIZE);
                mTab3.setCompoundDrawables(null, drawableTop3, null, null);
                break;
            case 4:
                mTab4.setTextColor(COLORS[1]);
                Drawable drawableTop4 = getResources().getDrawable(R.drawable.icon_tab04_selected);
                drawableTop4.setBounds(0, 0, TAB_ICON_SIZE, TAB_ICON_SIZE);
                mTab4.setCompoundDrawables(null, drawableTop4, null, null);
                break;
        }
    }

    @SuppressLint("NewApi")
    public void resetBackground() {
        mTab1.setTextColor(COLORS[0]);
        Drawable drawableTop1 = getResources().getDrawable(R.drawable.icon_tab01);
        drawableTop1.setBounds(0, 0, TAB_ICON_SIZE, TAB_ICON_SIZE);
        mTab1.setCompoundDrawables(null, drawableTop1, null, null);

        mTab2.setTextColor(COLORS[0]);
        Drawable drawableTop2 = getResources().getDrawable(R.drawable.icon_tab02);
        drawableTop2.setBounds(0, 0, TAB_ICON_SIZE, TAB_ICON_SIZE);
        mTab2.setCompoundDrawables(null, drawableTop2, null, null);

        mTab3.setTextColor(COLORS[0]);
        Drawable drawableTop3 = getResources().getDrawable(R.drawable.icon_tab03);
        drawableTop3.setBounds(0, 0, TAB_ICON_SIZE, TAB_ICON_SIZE);
        mTab3.setCompoundDrawables(null, drawableTop3, null, null);

        mTab4.setTextColor(COLORS[0]);
        Drawable drawableTop4 = getResources().getDrawable(R.drawable.icon_tab04);
        drawableTop4.setBounds(0, 0, TAB_ICON_SIZE, TAB_ICON_SIZE);
        mTab4.setCompoundDrawables(null, drawableTop4, null, null);
    }
}
