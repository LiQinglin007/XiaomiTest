package com.li.xiaomi.xiaomi_ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.li.xiaomi.xiaomi_core.delegates.LatteDelegate;
import com.li.xiaomi.xiaomi_core.utils.FinalData;
import com.li.xiaomi.xiaomi_core.utils.LogUtils;
import com.li.xiaomi.xiaomi_core.utils.PreferenceUtils;
import com.li.xiaomi.xiaomi_core.utils.T;
import com.li.xiaomi.xiaomi_core.utils.timer.BaseTimerTask;
import com.li.xiaomi.xiaomi_core.utils.timer.ITimerListener;
import com.li.xiaomi.xiaomi_ec.R;

import java.text.MessageFormat;
import java.util.Timer;

/**
 * 作者：dell or Xiaomi Li
 * 时间： 2018/3/23
 * 内容：启动页
 * 最后修改：
 */

public class LauncherDelegate extends LatteDelegate implements View.OnClickListener, ITimerListener {
    final String TAG = "LauncherDelegate";
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
            T.shortToast(getContext(), "打开过，要去首页了");
            LogUtils.Loge(TAG, "打开过");
        } else {
            T.shortToast(getContext(), "没有打开过，要去轮播图了");
            LogUtils.Loge(TAG, "没有打开过，这是第一次");
        }
    }
}
