package com.li.xiaomi.xiaomi_core.app;

/**
 * 作者：dell or Xiaomi Li
 * 时间： 2018/3/8
 * 内容：
 * 最后修改：
 */

public enum ConfigType {
    SHARED_PREFERENCES,
    HOST_API,//网络请求的地址
    CONFIGREADY,//是否完成初始化的标志位
    APPLICATIO_CONTEXT,//全局上下文
    ICON,
    INTERCEPTOR,//okhttp的拦截器
    DBNAME//数据库名
}
