package com.li.xiaomi.xiaomitest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.alibaba.fastjson.JSON;
import com.li.xiaomi.xiaomi_core.app.Latte;
import com.li.xiaomi.xiaomi_core.dbdata.DBUser;
import com.li.xiaomi.xiaomi_core.dbdata.DBUserDao;
import com.li.xiaomi.xiaomi_core.delegates.LatteDelegate;
import com.li.xiaomi.xiaomi_core.net.RestClient;
import com.li.xiaomi.xiaomi_core.net.callback.IError;
import com.li.xiaomi.xiaomi_core.net.callback.IFailure;
import com.li.xiaomi.xiaomi_core.net.callback.IRequest;
import com.li.xiaomi.xiaomi_core.net.callback.ISuccess;
import com.li.xiaomi.xiaomi_core.ui.LoaderStyle;
import com.li.xiaomi.xiaomi_core.utils.CheckStringEmptyUtils;
import com.li.xiaomi.xiaomi_core.utils.LogUtils;
import com.li.xiaomi.xiaomi_core.utils.T;
import com.li.xiaomi.xiaomi_core.utils.file.FileUtil;
import com.li.xiaomi.xiaomi_core.utils.greendaoUtils.DBManager;
import com.li.xiaomi.xiaomitest.R;
import com.li.xiaomi.xiaomitest.bean.LoginBean;

import java.util.WeakHashMap;

/**
 * 作者：dell or Xiaomi Li
 * 时间： 2018/3/16
 * 内容：
 * 最后修改：
 */

public class ExampleDelegate extends LatteDelegate implements View.OnClickListener {
    private static final String TAG = ExampleDelegate.class.getSimpleName();
    private Button GetBut;
    private Button PostBut;
    private Button PostHeadBut;
    private Button uploadBut;
    private Button downBut;
    private Button interceportBut;//使用拦截器,返回raw/test.json目录下的数据在AppCication中配置

    private String head = "";


    private Button DBAddBtu;
    private Button DBUpdateBtu;
    private Button DBDeleteBtu;

    @Override
    public Object setLayout() {
        return R.layout.dekegate_example;
    }

    @Override
    public void onBingView(@Nullable Bundle savedInstanceState, View rootView) {
        GetBut = rootView.findViewById(R.id.http_get_but);
        PostBut = rootView.findViewById(R.id.http_post_but);
        PostHeadBut = rootView.findViewById(R.id.http_posthead_but);
        uploadBut = rootView.findViewById(R.id.http_upload_but);
        downBut = rootView.findViewById(R.id.http_download_but);
        interceportBut = rootView.findViewById(R.id.http_interceport_but);

        GetBut.setOnClickListener(this);
        PostBut.setOnClickListener(this);
        PostHeadBut.setOnClickListener(this);
        uploadBut.setOnClickListener(this);
        downBut.setOnClickListener(this);
        interceportBut.setOnClickListener(this);

        DBAddBtu = rootView.findViewById(R.id.db_add_but);
        DBUpdateBtu = rootView.findViewById(R.id.db_update_but);
        DBDeleteBtu = rootView.findViewById(R.id.db_delete_but);

        DBAddBtu.setOnClickListener(this);
        DBUpdateBtu.setOnClickListener(this);
        DBDeleteBtu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.http_get_but:
                doGet();
                break;
            case R.id.http_post_but:
                doPost();
                break;
            case R.id.http_posthead_but:
                if (CheckStringEmptyUtils.IsEmpty(head)) {
                    T.shortToast(Latte.getApplicationContext(), "请先执行post请求");
                    return;
                }
                doPostHead();
                break;
            case R.id.http_upload_but:
                break;
            case R.id.http_download_but:
                downLoad();
                break;
            case R.id.http_interceport_but:
                doGetInterceport();
                break;

            case R.id.db_add_but:
                dbadd();
                break;
            case R.id.db_update_but:
                dbupdate();
                break;
            case R.id.db_delete_but:
                dbdelete();
                break;
            default:
                break;
        }
    }


    private void dbdelete() {
        DBUserDao dbUserDao = DBManager.getInstance().getDaoSession().getDBUserDao();
        DBUser unique1 = dbUserDao.queryBuilder().where(DBUserDao.Properties.UserName.eq("小米")).unique();
        if (unique1 != null) {
            LogUtils.Loge(TAG, "修改之后的结果" + JSON.toJSONString(unique1));
            dbUserDao.delete(unique1);
        }
    }

    private void dbupdate() {
        DBUserDao dbUserDao = DBManager.getInstance().getDaoSession().getDBUserDao();
        DBUser unique = dbUserDao.queryBuilder().where(DBUserDao.Properties.UserName.eq("小米")).unique();

        if (unique != null) {
            LogUtils.Loge(TAG, "查询结果" + JSON.toJSONString(unique));
            unique.setUserEmail("这是一个假的邮箱");
            dbUserDao.update(unique);
        }
        DBUser unique1 = dbUserDao.queryBuilder().where(DBUserDao.Properties.UserName.eq("小米")).unique();
        if (unique1 != null) {
            LogUtils.Loge(TAG, "修改之后的结果" + JSON.toJSONString(unique1));
        }
    }

    private void dbadd() {
        DBUserDao dbUserDao = DBManager.getInstance().getDaoSession().getDBUserDao();
        DBUser mUser = new DBUser();
        mUser.setUserId(null);
        mUser.setUserName("小米");
        mUser.setUserEmail("531192557@qq.com");
        mUser.setUserPassword("123456");
        mUser.setUserPhone("15284224244");

        DBUser mUser1 = new DBUser();
        mUser1.setUserId(null);
        mUser1.setUserName("小米1");
        mUser1.setUserEmail("531192558@qq.com");
        mUser1.setUserPassword("1234567");
        mUser1.setUserPhone("15284224245");

        dbUserDao.insert(mUser);
        dbUserDao.insert(mUser1);

    }

    private void doGetInterceport() {
        try {
            RestClient.
                    builder().
                    url("http://www.baidu.com/test").
                    success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            LogUtils.Loge(TAG, "doGetInterceport:success" + response);
                        }
                    }).
                    loadStyle(getContext(), LoaderStyle.BallBeatIndicator).
                    error(new IError() {
                        @Override
                        public void onError(int code, String msg) {
                            LogUtils.Loge(TAG, "doGetInterceport:error、code:" + code + "msg:" + msg);
                        }
                    }).
                    failure(new IFailure() {
                        @Override
                        public void onFailure(Throwable t) {
                            LogUtils.Loge(TAG, "doGetInterceport:onFailure:" + t.toString());
                        }
                    }).
                    request(new IRequest() {
                        @Override
                        public void onRequestStart() {
                            LogUtils.Loge(TAG, "doGetInterceport:onRequestStart");
                        }

                        @Override
                        public void onRequestEnd() {
                            LogUtils.Loge(TAG, "doGetInterceport:onRequestEnd");
                        }
                    }).
                    build().
                    get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doGet() {
        try {
            RestClient.
                    builder().
                    url("http://www.baidu.com").
                    success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            LogUtils.Loge(TAG, "doGet:success" + response);
                        }
                    }).
                    loadStyle(getContext(), LoaderStyle.BallBeatIndicator).
                    error(new IError() {
                        @Override
                        public void onError(int code, String msg) {
                            LogUtils.Loge(TAG, "doGet:error、code:" + code + "msg:" + msg);
                        }
                    }).
                    failure(new IFailure() {
                        @Override
                        public void onFailure(Throwable t) {
                            LogUtils.Loge(TAG, "doGet:onFailure:" + t.toString());
                        }
                    }).
                    request(new IRequest() {
                        @Override
                        public void onRequestStart() {
                            LogUtils.Loge(TAG, "doGet:onRequestStart");
                        }

                        @Override
                        public void onRequestEnd() {
                            LogUtils.Loge(TAG, "doGet:onRequestEnd");
                        }
                    }).
                    build().
                    get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doPost() {
        WeakHashMap<String, Object> weakHashMap = new WeakHashMap<>();
        weakHashMap.put("LoginName", "15284224244");
        weakHashMap.put("LoginPwd", "123456");
        weakHashMap.put("DevId", "");
        weakHashMap.put("SimId", "");
        RestClient.
                builder().
                url("http://home.hbhanzhi.com:8888/api/UserOpen/Login").
                params(weakHashMap).
                success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        LogUtils.Loge(TAG, "doPost:success" + response);

                        LoginBean loginBean = JSON.parseObject(response, LoginBean.class);
                        if (loginBean.getCode() == 0) {
                            head = loginBean.getData().getToken();
                        } else {
                            head = "";
                        }
                    }
                }).
                loadStyle(getContext(), LoaderStyle.BallBeatIndicator).
                error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        LogUtils.Loge(TAG, "doPost:error、code:" + code + "msg:" + msg);
                    }
                }).
                failure(new IFailure() {
                    @Override
                    public void onFailure(Throwable t) {
                        LogUtils.Loge(TAG, "doPost:onFailure:" + t.toString());
                    }
                }).
                request(new IRequest() {
                    @Override
                    public void onRequestStart() {
                        LogUtils.Loge(TAG, "doPost:onRequestStart");
                    }

                    @Override
                    public void onRequestEnd() {
                        LogUtils.Loge(TAG, "doPost:onRequestEnd");
                    }
                }).
                build().
                post();
    }

    private void doPostHead() {
        RestClient.
                builder().
                url("http://home.hbhanzhi.com:8888/api/Pattern/GetPatternlist").
                head("Authorization", head).
                param("LastTime", "0").
                success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        LogUtils.Loge(TAG, "doPostHead:success" + response);
                    }
                }).
                loadStyle(getContext(), LoaderStyle.BallBeatIndicator).
                error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        LogUtils.Loge(TAG, "doPostHead:error、code:" + code + "msg:" + msg);
                    }
                }).
                failure(new IFailure() {
                    @Override
                    public void onFailure(Throwable t) {
                        LogUtils.Loge(TAG, "doPostHead:onFailure:" + t.toString());
                    }
                }).
                request(new IRequest() {
                    @Override
                    public void onRequestStart() {
                        LogUtils.Loge(TAG, "doPostHead:onRequestStart");
                    }

                    @Override
                    public void onRequestEnd() {
                        LogUtils.Loge(TAG, "doPostHead:onRequestEnd");
                    }
                }).
                build().
                post();
    }

    private void downLoad() {
        try {
            RestClient.
                    builder().
                    url("https://qd.myapp.com/myapp/qqteam/AndroidQQ/mobileqq_android.apk").
                    downloadFilePath(FileUtil.DOWNLOAD_FILE_DIR).
                    downloadFileName("testDownload.apk").
                    downloadFileExtensionName(".apk").
                    success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            LogUtils.Loge(TAG, "downLoad:success" + response);
                        }
                    }).
                    loadStyle(getContext(), LoaderStyle.BallBeatIndicator).
                    error(new IError() {
                        @Override
                        public void onError(int code, String msg) {
                            LogUtils.Loge(TAG, "downLoad:error、code:" + code + "msg:" + msg);
                        }
                    }).
                    failure(new IFailure() {
                        @Override
                        public void onFailure(Throwable t) {
                            LogUtils.Loge(TAG, "downLoad:onFailure:" + t.toString());
                        }
                    }).
                    request(new IRequest() {
                        @Override
                        public void onRequestStart() {
                            LogUtils.Loge(TAG, "downLoad:onRequestStart");
                        }

                        @Override
                        public void onRequestEnd() {
                            LogUtils.Loge(TAG, "downLoad:onRequestEnd");
                        }
                    }).
                    build().
                    download();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
