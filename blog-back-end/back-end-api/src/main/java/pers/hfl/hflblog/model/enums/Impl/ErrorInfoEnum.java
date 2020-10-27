package pers.hfl.hflblog.model.enums.Impl;

import pers.hfl.hflblog.model.enums.IErrorInfo;

/**
 * @auther HFL
 * @date 2020/10/23/023 12:07
 * @description
 */
public enum ErrorInfoEnum implements IErrorInfo {
    // 成功
    SUCCESS(0, "操作成功"),
    // 参数缺失
    MISSING_PARAMETERS(4004, "参数缺失"),
    // 未知错误
    UNKNOWN_ERROR(5000, "出现未知错误"),
    // id找不到
    INVALID_ID(4008, "你的id不合法"),
    // 找不到相应资源
    RESOURCES_NOT_FOUND(4003, "找不到相应资源");

    private final int code;
    private final String msg;

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    ErrorInfoEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
