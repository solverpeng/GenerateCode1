package com.solverpeng.common.config;

import com.solverpeng.common.utils.PropertiesLoaderUtil;

/**
 * <pre>
 *      author: solverpeng
 *      blog  : http://solverpeng.com
 *      time  : 2017/2/22
 *      desc  : 全局配置类
 * </pre>
 */
public class Global {
    /**
     * 属性文件加载对象
     */
    private static PropertiesLoaderUtil PropertiesLoaderUtil;

    /**
     * 获取配置
     */
    public static String getConfig(String key) {
        if (PropertiesLoaderUtil == null) {
            PropertiesLoaderUtil = new PropertiesLoaderUtil("application.properties");
        }
        return PropertiesLoaderUtil.getProperty(key);
    }

    /**
     * 获取配置
     */
    public static String getMessageConfig(String key) {
        if (PropertiesLoaderUtil == null) {
            PropertiesLoaderUtil = new PropertiesLoaderUtil("application.properties");
        }
        return PropertiesLoaderUtil.getProperty(key);
    }

    /**
     * 获取项目根路径
     */
    public static String getAdminPath() {
        return getConfig("adminPath");
    }

    /**
     * 获取Url前缀
     */
    public static String getUrlSuffix() {
        return getConfig("urlSuffix");
    }

    /**
     * 获取上传文件相对路径跟路径
     */
    public static String getUploadPath() {
        return getConfig("web.file.upload");
    }

    /**
     * 获取上文文件绝对根路径
     */
    public static String getBasePath() {
        return getConfig("web.file.base");
    }

    /**
     * 获取jsp页面根路径
     */
    public static String getViewPath() {
        return getConfig("web.view.prefix");
    }

}