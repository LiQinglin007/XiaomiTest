package com.li.xiaomi.xiaomitest.activity;

import com.li.xiaomi.xiaomi_core.activitys.ProxyActivity;
import com.li.xiaomi.xiaomi_core.delegates.LatteDelegate;
import com.li.xiaomi.xiaomi_ec.launcher.LauncherDelegate;
import com.li.xiaomi.xiaomitest.fragment.ExampleDelegate;

public class MainActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();//例子
//        return new LauncherDelegate();//启动页
    }
}
