package com.solverpeng.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * <pre>
 *      author: solverpeng
 *      blog  : http://solverpeng.com
 *      time  : 2017/2/22
 *      desc  : 异常工具类
 * </pre>
 */
public abstract class ExceptionUtil {

    /**
     * 将CheckedException转换为UncheckedException
     *
     * @param e 需要转换的异常对象
     * @return 运行时异常
     */
    public static RuntimeException unchecked(Exception e) {
        if (e instanceof RuntimeException) {
            return (RuntimeException) e;
        } else {
            return new RuntimeException(e);
        }
    }

    /**
     * 将ErrorStack转化为String
     *
     * @param e 需要转换的异常对象
     * @return 异常信息的字符串
     */
    public static String getStackTraceAsString(Exception e) {
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    /**
     * 判断异常是否由某些底层的异常引起.
     *
     * @param ex                    要判断的异常
     * @param causeExceptionClasses 是否是引起要判断异常的异常类
     * @return 判断结果
     */
    @SuppressWarnings("unchecked")
    public static boolean isCausedBy(Exception ex, Class<? extends Exception>... causeExceptionClasses) {
        Throwable cause = ex.getCause();
        while (cause != null) {
            for (Class<? extends Exception> causeClass : causeExceptionClasses) {
                if (causeClass.isInstance(cause)) {
                    return true;
                }
            }
            cause = cause.getCause();
        }
        return false;
    }
}
