package pers.hfl.hflblog.config;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.setting.Setting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.hfl.hflblog.model.comm.BlogSetting;

import java.io.File;

/**
 * @author HFL
 * @ClassName BlogSettingConfig
 * @Date 2020/10/28/028 10:54
 * @Version V1.0
 **/
@Slf4j
@Configuration
public class BlogSettingConfig {
    @Value("${setting.path}")
    private String settingFilePath;

    @Bean
    public Setting setting() {
        File file = new File(settingFilePath);
        Setting setting;
        if (!file.exists()) {
            setting = new Setting(file, CharsetUtil.CHARSET_UTF_8, false);
            setting.set("title", "HFL博客");
            setting.set("desc", "万事胜意");
            setting.set("covers", "https://tvax1.sinaimg.cn/mw1024/bfe05ea9ly1fxgu8jys3fj21hc0u0k0j.jpg,https://tvax1.sinaimg.cn/large/bfe05ea9ly1fxgunx09dtj21hc0u0q81.jpg,https://tvax1.sinaimg.cn/large/bfe05ea9ly1fxgv2t92yyj21hc0u0qb9.jpg");
            setting.set("avatar", "https://portrait.gitee.com/uploads/avatars/user/772/2317865_qianyucc_1589023575.png");
            setting.set("nickname", "HFLSC");
            setting.store(file.getAbsolutePath());
        }
        setting = new Setting(file, CharsetUtil.CHARSET_UTF_8, false);
        setting.autoLoad(true);
        log.info(BlogSetting.fromSetting(setting).toString());
        return setting;
    }
}
