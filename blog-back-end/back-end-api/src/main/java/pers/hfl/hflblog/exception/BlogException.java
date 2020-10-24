package pers.hfl.hflblog.exception;/**
 * @auther HFL
 * @date 2020/10/23/023 16:57
 * @description
 */

import pers.hfl.hflblog.model.enums.IErrorInfo;
import pers.hfl.hflblog.model.vo.Results;

/**
 * @ClassName BlogException
 * @Author HFL
 * @Date 2020/10/23/023 16:57
 * @Version V1.0
 **/
public class BlogException extends RuntimeException {
    private final IErrorInfo errorInfo;

    public BlogException(IErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }

    /**
     * 将异常转换为 ResultVO 对象返回给前端
     *
     * @return 返回异常
     */
    public Results toResultVO() {
        return Results.fromErrorInfo(errorInfo);
    }
}
