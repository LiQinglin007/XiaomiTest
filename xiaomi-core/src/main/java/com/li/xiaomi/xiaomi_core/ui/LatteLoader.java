package com.li.xiaomi.xiaomi_core.ui;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.li.xiaomi.xiaomi_core.R;
import com.li.xiaomi.xiaomi_core.utils.ScreenUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * 作者：dell or Xiaomi Li
 * 时间： 2018/3/21
 * 内容：用来显示loading的对话框
 * 最后修改：
 */

public class LatteLoader {
    private static final int LOADER_SIZE_SCALE = 8;
    private static final int LOADER_OFFSET_SCALE = 10;

    private static final ArrayList<AppCompatDialog> DIALOGS = new ArrayList<>();
    //loading的默认样式
    public static final LoaderStyle DEFAULT_LOADER = LoaderStyle.BallClipRotatePulseIndicator;

    /**
     * 显示loading的dialog
     *
     * @param context
     * @param loaderStyle 对话框的样式
     */
    public static void showLoading(Context context, Enum<LoaderStyle> loaderStyle) throws IllegalAccessException, ClassNotFoundException, InstantiationException {


        final AppCompatDialog appCompatDialog = new AppCompatDialog(context, R.style.LoadingDialog);
        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(loaderStyle.name(), context);
        appCompatDialog.setContentView(avLoadingIndicatorView);
        int deviceWidth = ScreenUtils.getScreenWidth(context);
        int deviceHeight = ScreenUtils.getScreenHeight(context);

        final Window dialogWindow = appCompatDialog.getWindow();

        if (dialogWindow != null) {
            final WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = deviceWidth / LOADER_SIZE_SCALE;
            lp.height = deviceHeight / LOADER_SIZE_SCALE;
            lp.height = lp.height + deviceHeight / LOADER_OFFSET_SCALE;
            lp.gravity = Gravity.CENTER;
        }
        DIALOGS.add(appCompatDialog);
        appCompatDialog.show();

//        Window window = appCompatDialog.getWindow();
//        if (window != null) {
//            WindowManager.LayoutParams layoutParams = window.getAttributes();
//            layoutParams.width = ScreenUtils.getScreenWidth(context) / LOADER_SIZE_SCALE;
//            layoutParams.height = ScreenUtils.getScreenHeight(context) / LOADER_SIZE_SCALE;
//            layoutParams.height = layoutParams.height + ScreenUtils.getScreenHeight(context) / LOADER_OFFSET_SCALE;
//            layoutParams.gravity = Gravity.CENTER;
//        }
//        DIALOGS.add(appCompatDialog);
//        appCompatDialog.show();
    }

    /**
     * 显示默认样式的loading
     *
     * @param context
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */
    public static void showLoading(Context context) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        showLoading(context, DEFAULT_LOADER);
    }

    /**
     * 停止
     */
    public static void stopLoading() {
        if (DIALOGS != null && DIALOGS.size() != 0) {
            for (AppCompatDialog dialog : DIALOGS) {
                if (dialog.isShowing()) {
                    dialog.cancel();
                }
            }
        }
    }

}
