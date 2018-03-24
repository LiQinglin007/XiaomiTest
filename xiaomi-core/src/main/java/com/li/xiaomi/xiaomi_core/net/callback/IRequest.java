package com.li.xiaomi.xiaomi_core.net.callback;

/**
 * 作者：dell or Xiaomi Li
 * 时间： 2018/3/20
 * 内容：请求开始和结束的接口
 * 最后修改：
 */

public interface IRequest {
    void onRequestStart();

    void onRequestEnd();
}
