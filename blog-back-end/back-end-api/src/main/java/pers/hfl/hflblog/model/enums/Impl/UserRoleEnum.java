package pers.hfl.hflblog.model.enums.Impl;

/**
 * @author HFL
 * @date 2020/10/30/030 15:56
 * @description
 */
public enum UserRoleEnum {
    ADMIN("ADMIN");

    private final String value;

    UserRoleEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
