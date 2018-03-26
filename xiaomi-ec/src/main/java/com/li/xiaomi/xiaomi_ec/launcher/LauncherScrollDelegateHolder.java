package com.li.xiaomi.xiaomi_ec.launcher;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.li.xiaomi.xiaomi_core.utils.glideUtils.GlidePicUtils;

/**
 * 作者：dell or Xiaomi Li
 * 时间： 2018/3/26
 * 内容：
 * 最后修改：
 */

public class LauncherScrollDelegateHolder implements Holder<Integer> {
    ImageView mImageView;

    @Override
    public View createView(Context context) {
        mImageView = new ImageView(context);
        mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return mImageView;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void UpdateUI(Context context, int position, Integer data) {
        GlidePicUtils.LoadRequest(context, mImageView, data);
    }
}
