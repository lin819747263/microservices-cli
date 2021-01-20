package com.linmsen.dubbo.core.router;

/**
 * Dubbo 路由 Tag 的上下文
 *
 */
public class DubboRouterTagContextHolder {

    private static ThreadLocal<String> tagContext = new ThreadLocal<>();

    public static void setTag(String tag) {
        tagContext.set(tag);
    }

    public static String getTag() {
        return tagContext.get();
    }

    public static void clear() {
        tagContext.remove();
    }

}
