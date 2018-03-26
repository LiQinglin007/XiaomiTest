package com.li.xiaomi.xiaomi_ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.li.xiaomi.xiaomi_core.delegates.LatteDelegate;
import com.li.xiaomi.xiaomi_core.utils.FinalData;
import com.li.xiaomi.xiaomi_core.utils.PreferenceUtils;
import com.li.xiaomi.xiaomi_core.utils.timer.BaseTimerTask;
import com.li.xiaomi.xiaomi_core.utils.timer.ITimerListener;
import com.li.xiaomi.xiaomi_ec.R;
import com.li.xiaomi.xiaomi_ec.sign.SignInDelegate;

import java.text.MessageFormat;
import java.util.Timer;

/**
 * 作者：dell or Xiaomi Li
 * 时间： 2018/3/23
 * 内容：启动页
 * 最后修改：
 */

public class LauncherDelegate extends LatteDelegate implements View.OnClickListener, ITimerListener {
    private static final String TAG = LauncherDelegate.class.getSimpleName();
    AppCompatTextView mTimerTv;
    private Timer mTimer = null;
    int mCount = 5;

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    private void init() {
        mTimer = new Timer();
        final BaseTimerTask baseTimerTask = new BaseTimerTask(this);
        mTimer.schedule(baseTimerTask, 0, 1000);
    }

    @Override
    public void onBingView(@Nullable Bundle savedInstanceState, View rootView) {
        mTimerTv = rootView.findViewById(R.id.tv_launcher_timer);
        mTimerTv.setOnClickListener(this);
        init();
    }

    @Override
    public void onClick(View v) {
        if (v == mTimerTv) {
            if (mTimer != null) {
                mTimer.cancel();
                mTimer = null;
                checkIsShowScroll();
            }
        }
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTimerTv != null) {
                    mTimerTv.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount <= 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }

    //判断是否显示滑动启动页
    private void checkIsShowScroll() {

        if (PreferenceUtils.getBoolean(FinalData.IS_OPEN_APP, false)) {
            startWithPop(new SignInDelegate());//去登录页面
//            T.shortToast(getContext(), "打开过，要去首页了");
//            LogUtils.Loge(TAG, "打开过");
        } else {
//            // 启动新的Fragment，启动者和被启动者是在同一个栈的
//            start(SupportFragment fragment)
//            // 以某种启动模式，启动新的Fragment
//            start(SupportFragment fragment, int launchMode)
//            // 启动新的Fragment，并能接收到新Fragment的数据返回
//            startForResult(SupportFragment fragment, int requestCode)
//            // 启动目标Fragment，并关闭当前Fragment；不要尝试pop()+start()，动画会有问题
//            startWithPop(SupportFragment fragment)


//            // 当前Fragment出栈(在当前Fragment所在栈内pop)
//            pop();
//            // 出栈某一个Fragment栈内之上的所有Fragment
//            popTo(Class fragmentClass/String tag, boolean includeSelf);
//            // 出栈某一个Fragment栈内之上的所有Fragment。如果想出栈后，紧接着.beginTransaction()开始一个新事务，
//            //请使用下面的方法， 防止多事务连续执行的异常
//            popTo(Class fragmentClass, boolean includeSelf, Runnable afterTransaction);


//            // 获取所在栈内的栈顶Fragment
//            getTopFragment();
//            // 获取当前Fragment所在栈内的前一个Fragment
//            getPreFragment();
//            // 获取所在栈内的某个Fragment，可以是xxxFragment.Class，也可以是tag
//            findFragment(Class fragmentClass/String tag);

            startWithPop(new LauncherScrollDelegate());

        }
    }
}
