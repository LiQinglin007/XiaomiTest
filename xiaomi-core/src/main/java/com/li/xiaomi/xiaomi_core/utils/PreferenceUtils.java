package com.li.xiaomi.xiaomi_core.utils;

import android.content.SharedPreferences;

import com.li.xiaomi.xiaomi_core.app.ConfigType;
import com.li.xiaomi.xiaomi_core.app.Latte;


/**
 * 类描述：SharedPreferences  工具类
 * 作  者：李清林
 * 时  间：2016/10/11.
 * 修改备注：
 * 使用说明：1、需要在Application中声明
 * 2、在PublicStatic 中有静态变量
 * <p>
 * <p>
 * SharedPreference提交数据时,尽量使用 Editor.apply(),而非Editor.commit()。
 * 一般来讲,仅当需要确定提交结果,并据此有后续操作时,才使用Editor.commit()。
 * 说明：
 * SharedPreference相关修改使用 apply 方法进行提交会先写入内存，然后异步写入
 * 磁盘,commit 方法是直接写入磁盘.如果频繁操作的话 apply 的性能会优于 commit
 * <p>
 * apply 会将最后修改内容写入磁盘。但是如果希望立刻获取存储操作的结果，并据此
 * 做相应的其他操作，应当使用 commit。
 */

public class PreferenceUtils {

    private static SharedPreferences getSharedPreferences() {
        SharedPreferences configuration = Latte.getConfiguration(ConfigType.SHARED_PREFERENCES);
        return configuration;
    }

    private static SharedPreferences.Editor getSharedPreferencesEditor() {
        if (getSharedPreferences() != null) {
            return getSharedPreferences().edit();
        } else {
            return null;
        }
    }

    /**
     * 存Float
     *
     * @param key   键
     * @param value 值
     */
    public static void setFloat(String key, float value) {
        if (getSharedPreferencesEditor() != null) {
            getSharedPreferencesEditor().putFloat(key, value);
            getSharedPreferencesEditor().commit();
        }
    }

    /**
     * 取Float
     *
     * @param key     键
     * @param devalue 默认值
     * @return
     */
    public static Float getFloat(String key, float devalue) {
        if (getSharedPreferences() != null) {
            return getSharedPreferences().getFloat(key, devalue);
        } else {
            return null;
        }
    }

    /**
     * 存Float
     *
     * @param key   键
     * @param value 值
     */
    public static void setFloatAsync(String key, float value) {
        if (getSharedPreferencesEditor() != null) {
            getSharedPreferencesEditor().putFloat(key, value);
            getSharedPreferencesEditor().apply();
        }
    }

    /**
     * 存Long
     *
     * @param key   键
     * @param value 值
     */
    public static void setLong(String key, long value) {
        if (getSharedPreferencesEditor() != null) {
            getSharedPreferencesEditor().putLong(key, value);
            getSharedPreferencesEditor().commit();
        }
    }

    /**
     * 存Long
     *
     * @param key   键
     * @param value 值
     */
    public static void setLongAsync(String key, long value) {
        if (getSharedPreferencesEditor() != null) {
            getSharedPreferencesEditor().putLong(key, value);
            getSharedPreferencesEditor().apply();
        }
    }

    /**
     * 取Long
     *
     * @param key     键
     * @param devalue 默认值
     * @return
     */
    public static Long getLong(String key, long devalue) {
        if (getSharedPreferences() != null) {
            return getSharedPreferences().getLong(key, devalue);
        } else {
            return null;
        }
    }

    /**
     * 存String
     *
     * @param key   键
     * @param value 值
     */
    public static void setString(String key, String value) {
        if (getSharedPreferencesEditor() != null) {
            getSharedPreferencesEditor().putString(key, value);
            getSharedPreferencesEditor().commit();
        }
    }

    /**
     * 存String
     *
     * @param key   键
     * @param value 值
     */
    public static void setStringAsync(String key, String value) {
        if (getSharedPreferencesEditor() != null) {
            getSharedPreferencesEditor().putString(key, value);
            getSharedPreferencesEditor().apply();
        }
    }

    /**
     * 取String
     *
     * @param key     键
     * @param devalue 默认值
     * @return
     */
    public static String getString(String key, String devalue) {
        if (getSharedPreferences() != null) {
            return getSharedPreferences().getString(key, devalue);
        } else {
            return null;
        }
    }

    /**
     * 取boolean
     *
     * @param key     键
     * @param devalue 默认值
     * @return
     */
    public static boolean getBoolean(String key, boolean devalue) {
        if (getSharedPreferences() != null) {
            return getSharedPreferences().getBoolean(key, devalue);
        } else {
            return devalue;
        }
    }

    /**
     * 存boolean
     *
     * @param key   键
     * @param value 值
     */
    public static void setBoolean(String key, boolean value) {
        if (getSharedPreferencesEditor() != null) {
            getSharedPreferencesEditor().putBoolean(key, value);
            getSharedPreferencesEditor().commit();
        }
    }

    /**
     * 存boolean
     *
     * @param key   键
     * @param value 值
     */
    public static void setBooleanAsync(String key, boolean value) {
        if (getSharedPreferencesEditor() != null) {
            getSharedPreferencesEditor().putBoolean(key, value);
            getSharedPreferencesEditor().apply();
        }
    }

    /**
     * 取int
     *
     * @param key     键
     * @param devalue 默认值
     * @return
     */
    public static int getInt(String key, int devalue) {
        if (getSharedPreferences() != null) {
            return getSharedPreferences().getInt(key, devalue);
        } else {
            return devalue;
        }
    }

    /**
     * 存int
     *
     * @param key   键
     * @param value 值
     */
    public static void setInt(String key, int value) {
        if (getSharedPreferencesEditor() != null) {
            getSharedPreferencesEditor().putInt(key, value);
            getSharedPreferencesEditor().commit();
        }
    }

    /**
     * 存int
     *
     * @param key   键
     * @param value 值
     */
    public static void setIntAsync(String key, int value) {
        if (getSharedPreferencesEditor() != null) {
            getSharedPreferencesEditor().putInt(key, value);
            getSharedPreferencesEditor().apply();
        }
    }

    /**
     * 清空一个
     *
     * @param key
     */
    public static void remove(String key) {
        if (getSharedPreferencesEditor() != null) {
            getSharedPreferencesEditor().remove(key);
            getSharedPreferencesEditor().commit();
        }
    }

    /**
     * 清空一个
     *
     * @param key
     */
    public static void removeAsync(String key) {
        if (getSharedPreferencesEditor() != null) {
            getSharedPreferencesEditor().remove(key);
            getSharedPreferencesEditor().apply();
        }
    }

}
