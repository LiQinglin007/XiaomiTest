package com.li.xiaomi.xiaomi_core.app;

import android.content.SharedPreferences;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * 作者：dell or Xiaomi Li
 * 时间： 2018/3/8
 * 内容：这里用来管理配置，获取/存储 配置
 * 最后修改：
 */

public class Configurator {

    //sharePrefrence的文件名称
    public static final String PREFRENCE_NAME = "MyApplication";
    //数据库的文件名称
    public static final String DATABASE_NAME = "MyApplication.db";


    //在不使用的时候，会及时的回收，在优化内存方面比价好
//    public final static WeakHashMap<String, Object> LATTE_CONFIGS = new WeakHashMap<>();
    //这里不适用WeakHashMap的原因，因为这个配置文件是始终要存在的，不能被回收。
    public final static HashMap<Object, Object> LATTE_CONFIGS = new HashMap<>();
    //这里用一个list来存储字体图像等
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    //okhttp的拦截器
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

    private Configurator() {
        LATTE_CONFIGS.put(ConfigType.CONFIGREADY, false);//配置是否完成的标志位
    }

//    private static Configurator mConfiguration = null;

    /**
     * 使用静态内部类的方式来实现单例模式
     * 1、线程安全
     * 2、延时加载，在最开始不会创建，只有在调用getInstance()方法的时候才会调用
     * 3、避免加锁带来的性能问题
     *
     * @return
     */
    public static Configurator getInstance() {
        return SingletonHolder.instance;
//        if (mConfiguration == null) {                         //Single Checked
//            synchronized (Configurator.class) {
//                if (mConfiguration == null) {                 //Double Checked
//                    mConfiguration = new Configurator();
//                }
//            }
//        }
//        return mConfiguration;
    }

    private static class SingletonHolder {
        //静态初始化器，由JVM来保证线程安全
        //这里使用final 关键字 ，因为这个只在这里使用，其他的情况也不会变，为了以后不会误操作发生错误
        private static final Configurator instance = new Configurator();
    }

    //    final HashMap<String, Object> getLatteConfigs() {
//        return LATTE_CONFIGS;
//    }
    final HashMap<Object, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    /**
     * 完成初始化，更新标志位
     */
    public final void configure() {
        initIcons();
        LATTE_CONFIGS.put(ConfigType.CONFIGREADY, true);
    }

    /**
     * 检查初始化状态是否完成
     *
     * @return
     */
    private static final void checkConfiguration() {
        final boolean asd = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIGREADY);
        if (!asd) {//如果没有完成，但是要着急去做下边的操作，就在这里抛出一个异常
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    /**
     * 配置host_api
     *
     * @param apiHost
     * @return
     */
    public final Configurator withApiHost(String apiHost) {
        LATTE_CONFIGS.put(ConfigType.HOST_API, apiHost);
        return this;
    }

    /**
     * 初始化图像字体
     */
    private void initIcons() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }


    /**
     * 配置图像等
     *
     * @param descriptor
     * @return
     */
    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    /**
     * 配置拦截器
     *
     * @param interceptor
     * @return
     */
    public final Configurator withInterceptors(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        LATTE_CONFIGS.put(ConfigType.INTERCEPTOR, INTERCEPTORS);
        return this;
    }


    public final Configurator withInterceptors(ArrayList<Interceptor> interceptorList) {
        INTERCEPTORS.addAll(interceptorList);
        LATTE_CONFIGS.put(ConfigType.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    /**
     * 配置SharedPreferences
     * @param sharedPreferences
     * @return
     */
    public final Configurator withSharedPreferences(SharedPreferences sharedPreferences) {
        LATTE_CONFIGS.put(ConfigType.SHARED_PREFERENCES, sharedPreferences);
        return this;
    }

    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key);
    }


}
