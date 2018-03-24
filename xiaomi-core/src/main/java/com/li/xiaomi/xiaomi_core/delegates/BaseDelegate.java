package com.li.xiaomi.xiaomi_core.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.li.xiaomi.xiaomi_core.activitys.ProxyActivity;

import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * 作者：dell or Xiaomi Li
 * 时间： 2018/3/16
 * 内容：fragment的基类
 * 最后修改：
 */

public abstract class BaseDelegate extends SwipeBackFragment {
    String TAG = "BaseDelegate";

    public abstract Object setLayout();

    public abstract void onBingView(@Nullable Bundle savedInstanceState, View rootView);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //这里可以用一个view来放一个布局   也可以用一个布局id来当一个布局
        View rootView = null;
        if (setLayout() instanceof Integer) {//判断传进来的是不是布局文件的id
            rootView = inflater.inflate((Integer) setLayout(), container, false);
        } else if (setLayout() instanceof View) {//或者是一个view
            rootView = (View) setLayout();
        } else {
            throw new RuntimeException("setLayout type must be view or int ！");
        }
        if (rootView != null) {
            onBingView(savedInstanceState, rootView);
        } else {
            throw new RuntimeException("rootView must be not null ！");
        }
        return rootView;
    }


    public final ProxyActivity getProxyActivity() {
        return (ProxyActivity) _mActivity;
    }

}
