package com.li.xiaomi.xiaomi_core.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;


import com.li.xiaomi.xiaomi_core.R;
import com.li.xiaomi.xiaomi_core.delegates.LatteDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * 作者：dell or Xiaomi Li
 * 时间： 2018/3/16
 * 内容：activity的基类，注意这里使用单个Activity的模式，在onDestory方法中进行垃圾回收，在其他项目中可以在MainActivity中进行这个操作
 * 最后修改：
 */

public abstract class ProxyActivity extends SupportActivity {

    public abstract LatteDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState) {
        final ContentFrameLayout contentFrameLayout = new ContentFrameLayout(this);
        contentFrameLayout.setId(R.id.delegate_content);
        setContentView(contentFrameLayout);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.delegate_content, setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //垃圾回收
        //告诉垃圾收集器打算进行垃圾收集，而垃圾收集器进不进行收集是不确定的
        System.gc();
        //强制调用已经失去引用的对象的finalize方法
        System.runFinalization();
    }
}
