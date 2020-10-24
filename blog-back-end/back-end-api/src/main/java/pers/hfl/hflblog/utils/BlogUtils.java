package pers.hfl.hflblog.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @ClassName BlogUtils
 * @Author HFL
 * @Date 2020/10/23/023 11:50
 * @Version V1.0
 **/
public class BlogUtils {
    /**
     * 以 java8 的方式获取当前时间字符串
     *
     * @return 当前时间格式化之后的字符串
     */
    public static String now() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
