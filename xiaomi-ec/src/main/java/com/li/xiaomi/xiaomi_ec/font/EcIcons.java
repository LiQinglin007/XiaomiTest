package com.li.xiaomi.xiaomi_ec.font;

import com.joanzapata.iconify.Icon;

/**
 * 作者：dell or Xiaomi Li
 * 时间： 2018/3/9
 * 内容：使用阿里巴巴矢量图
 * 最后修改：
 */

public enum EcIcons implements Icon {
    icon_scan('\ue602'),
    icon_ali_pay('\ue606');

    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
