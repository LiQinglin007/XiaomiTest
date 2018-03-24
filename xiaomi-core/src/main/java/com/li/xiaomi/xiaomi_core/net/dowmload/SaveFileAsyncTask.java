package com.li.xiaomi.xiaomi_core.net.dowmload;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.li.xiaomi.xiaomi_core.app.Latte;
import com.li.xiaomi.xiaomi_core.net.callback.IRequest;
import com.li.xiaomi.xiaomi_core.net.callback.ISuccess;
import com.li.xiaomi.xiaomi_core.ui.LatteLoader;
import com.li.xiaomi.xiaomi_core.utils.CheckStringEmptyUtils;
import com.li.xiaomi.xiaomi_core.utils.file.FileUtil;

import java.io.File;

import okhttp3.ResponseBody;

/**
 * 作者：dell or Xiaomi Li
 * 时间： 2018/3/22
 * 内容：保存文件
 * 最后修改：
 */

public class SaveFileAsyncTask extends AsyncTask<Object, Void, File> {

    ISuccess mISuccess;
    IRequest mIRequest;

    public SaveFileAsyncTask(ISuccess ISuccess, IRequest IRequest) {
        mISuccess = ISuccess;
        mIRequest = IRequest;
    }

    @Override
    protected File doInBackground(Object... objects) {
        String downloadFileName = (String) objects[0];//文件名
        String downloadFilePath = (String) objects[1];//下载地址
        String downloadFileExtensionName = (String) objects[2];//扩展名
        final ResponseBody mResponseBody = (ResponseBody) objects[3];

        if (CheckStringEmptyUtils.IsEmpty(downloadFilePath)) {
            downloadFilePath = "down_loads";
        }
        if (CheckStringEmptyUtils.IsEmpty(downloadFileExtensionName)) {
            downloadFileExtensionName = "";
        }

        if (!CheckStringEmptyUtils.IsEmpty(downloadFileName)) {//文件名不是空的，就用文件名来构建一个文件
            return FileUtil.writeToDisk(mResponseBody.byteStream(), downloadFilePath, downloadFileName);
        } else {
            //输入流、下载地址、文件的扩展名转换成大写、扩展名
            return FileUtil.writeToDisk(mResponseBody.byteStream(), downloadFilePath, downloadFileExtensionName.toUpperCase(), downloadFileExtensionName);
        }
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (mISuccess != null) {
            mISuccess.onSuccess(file.getPath());
        }
        if (mIRequest != null) {
            LatteLoader.stopLoading();
            mIRequest.onRequestEnd();
        }
        autoInstallApk(file);
    }

    /**
     * 检查是不是app，然后去安装
     */
    private void autoInstallApk(File file) {
        if (FileUtil.getExtension(file.getPath()).equals("apk")) {
            final Intent install = new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            Latte.getApplicationContext().startActivity(install);
        }
    }
}
