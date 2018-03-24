package com.li.xiaomi.xiaomi_ec.font;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

/**
 * 作者：dell or Xiaomi Li
 * 时间： 2018/3/9
 * 内容：用阿里巴巴矢量图自定义module
 * 博客地址：https://www.jianshu.com/p/91a7fdb54b68
 * 最后修改：
 */

public class FontEcModule implements IconFontDescriptor {
    @Override
    public String ttfFileName() {
        return "iconfont.ttf";
    }

    @Override
    public Icon[] characters() {
        return EcIcons.values();
    }
}
