package pers.hfl.hflblog.model.vo;

/**
 * @auther HFL
 * @date 2020/10/26/026 11:38
 * @description
 */
public interface IConverter<T, E> {
    /**
     * VO 转换函数
     *
     * @param t 目标对象
     * @return 转换结果
     */
    E convertToVO(T t);
}
