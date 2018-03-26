package com.li.xiaomi.xiaomi_ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.li.xiaomi.xiaomi_core.delegates.LatteDelegate;
import com.li.xiaomi.xiaomi_core.utils.FinalData;
import com.li.xiaomi.xiaomi_core.utils.PreferenceUtils;
import com.li.xiaomi.xiaomi_ec.R;

import java.util.ArrayList;

/**
 * 作者：dell or Xiaomi Li
 * 时间： 2018/3/26
 * 内容：
 * 最后修改：
 */

public class LauncherScrollDelegate extends LatteDelegate implements ViewPager.OnPageChangeListener {
    final String TAG = "LauncherScrollDelegate";
    private ConvenientBanner<Integer> mConvenientBanner;

    private final ArrayList<Integer> imgs = new ArrayList<>();

    private void initImgs() {
        imgs.clear();
        imgs.add(R.drawable.banner01);
        imgs.add(R.drawable.banner02);
        imgs.add(R.drawable.banner03);
        imgs.add(R.drawable.banner04);
        imgs.add(R.drawable.banner05);
    }

    private void initBanner() {
        PreferenceUtils.setBoolean(FinalData.IS_OPEN_APP, true);
        mConvenientBanner = new ConvenientBanner(getContext());
        mConvenientBanner.setPages(new CBViewHolderCreator<LauncherScrollDelegateHolder>() {
            @Override
            public LauncherScrollDelegateHolder createHolder() {
                return new LauncherScrollDelegateHolder();
            }
        }, imgs);
        mConvenientBanner.setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus});
        mConvenientBanner.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        mConvenientBanner.setPointViewVisible(true);
        mConvenientBanner.setOnPageChangeListener(this);
    }


    @Override
    public Object setLayout() {
        initImgs();
        initBanner();
        return mConvenientBanner;
    }

    @Override
    public void onBingView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
