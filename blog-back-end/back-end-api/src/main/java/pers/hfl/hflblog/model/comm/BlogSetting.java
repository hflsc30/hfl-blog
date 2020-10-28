package pers.hfl.hflblog.model.comm;

import cn.hutool.setting.Setting;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName BlogSetting
 * @Author HFL
 * @Date 2020/10/28/028 10:51
 * @Version V1.0
 **/
@Data
public class BlogSetting {
    private String title;
    private String desc;
    private List<String> covers;
    private String avatar;
    private String nickname;
    private Integer totalClickCount;
    private Integer articleCount;

    public static BlogSetting fromSetting(Setting setting) {
        return (BlogSetting) setting.toBean(new BlogSetting());
    }
}
