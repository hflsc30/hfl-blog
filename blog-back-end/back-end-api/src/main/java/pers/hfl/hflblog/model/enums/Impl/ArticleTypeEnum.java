package pers.hfl.hflblog.model.enums.Impl;

/**
 * @auther HFL
 * @date 2020/10/27/027 14:51
 * @description
 */
public enum ArticleTypeEnum {
    /**
     * 文章类型: 原创
     */
    ORYGINAL("原创", 1),
    /**
     * 文章类型: 转载
     */
    REPRINT("转载", 0);
    private String notes;
    private int flag;

    public String getNotes() {
        return notes;
    }

    public int getFlag() {
        return flag;
    }

    ArticleTypeEnum(String notes, int flag) {
        this.notes = notes;
        this.flag = flag;
    }
}
