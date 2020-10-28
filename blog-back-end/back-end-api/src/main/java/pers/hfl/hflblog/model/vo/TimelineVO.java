package pers.hfl.hflblog.model.vo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ClassName TimelineVO
 * @Author HFL
 * @Date 2020/10/28/028 14:26
 * @Version V1.0
 **/
@Data
@Builder
public class TimelineVO {
    private String year;
    private List<Item> items;

    @Data
    @Builder
    public static class Item {
        private Integer id;
        private Date insertTime;
        private String title;
    }
}
