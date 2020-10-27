package pers.hfl.hflblog.model.dto;

/**
 * @auther HFL
 * @date 2020/10/27/027 16:26
 * @description
 */
public interface IConverter<T, E> {
    /**
     * 将对应的 DTO 转换为 PO
     *
     * @param t 需要转换的 DTO 类
     * @return 转换结果
     */
    E convertToVO(T t);
}
