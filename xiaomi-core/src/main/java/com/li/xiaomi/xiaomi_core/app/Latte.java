package com.li.xiaomi.xiaomi_core.app;

import android.content.Context;

import com.li.xiaomi.xiaomi_core.utils.greendaoUtils.DBManager;

/**
 * 作者：dell or Xiaomi Li
 * 时间： 2018/3/9
 * 内容：
 * 最后修改：
 */

public final class Latte {
    public static Configurator init(Context context) {
        Configurator.getInstance()
                .getLatteConfigs()
                .put(ConfigType.APPLICATIO_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static final void initDB() {
        //初始化数据库
        DBManager.getInstance().init(getApplicationContext());
    }

    public static <T> T getConfiguration(Object key) {
        return Configurator.getInstance().getConfiguration(key);
    }


    public static Context getApplicationContext() {
        return getConfiguration(ConfigType.APPLICATIO_CONTEXT);
    }

}
