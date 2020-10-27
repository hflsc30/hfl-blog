package pers.hfl.hflblog.model.vo;/**
 * @auther HFL
 * @date 2020/10/27/027 15:00
 * @description
 */

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @ClassName PageVO
 * @Author HFL
 * @Date 2020/10/27/027 15:00
 * @Version V1.0
 **/
@Data
@Builder
public class PageVO<T> {
    protected List<T> records;
    protected long total;
    protected long size;
    protected long current;
}
