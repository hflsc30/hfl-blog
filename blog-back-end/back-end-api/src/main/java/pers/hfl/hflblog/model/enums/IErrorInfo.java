package pers.hfl.hflblog.model.enums;

/**
 * @auther HFL
 * @date 2020/10/23/023 12:05
 * @description
 */
public interface IErrorInfo {
    /**
     * 返回信息
     *
     * @return 信息内容
     */
    String getMsg();

    /**
     * 结果码
     *
     * @return 结果码
     */
    int getCode();
}
