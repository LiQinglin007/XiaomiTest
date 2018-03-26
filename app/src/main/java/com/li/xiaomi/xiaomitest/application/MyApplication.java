package com.li.xiaomi.xiaomitest.application;

import android.app.Activity;
import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.li.xiaomi.xiaomi_core.app.Configurator;
import com.li.xiaomi.xiaomi_core.app.Latte;
import com.li.xiaomi.xiaomi_core.net.interceptor.interceptors.DebugInterceptor;
import com.li.xiaomi.xiaomi_ec.font.FontEcModule;
import com.li.xiaomi.xiaomitest.R;


/**
 * 作者：dell or Xiaomi Li
 * 时间： 2018/3/9
 * 内容：
 * 最后修改：
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this).
                withIcon(new FontAwesomeModule()).//配置字体文字
                withIcon(new FontEcModule()).
                withApiHost("http://127.0.0.1/").//配置网络请求地址
                withSharedPreferences(getSharedPreferences(Configurator.PREFRENCE_NAME, Activity.MODE_PRIVATE)).//配置SharedPreference
                withDBGreenDao(Configurator.DATABASE_NAME).//配置数据库
                withInterceptors(new DebugInterceptor("test", R.raw.test)).//配置拦截器,配置请求的关键字
                configure();
        //初始化数据库
        Latte.initDB();
    }
}
