package pers.hfl.hflblog.model.vo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.Data;
import pers.hfl.hflblog.model.comm.BlogSetting;
import pers.hfl.hflblog.model.entity.ArticlePO;

import java.util.List;

/**
 * @ClassName BlogInfoVO
 * @Author HFL
 * @Date 2020/10/28/028 11:17
 * @Version V1.0
 **/
@Data
public class BlogInfoVO {
    private String title;
    private String desc;
    private List<String> covers;
    private String avatar;
    private String nickname;
    private Integer totalClickCount;
    private Integer articleCount;

    /**
     * 根据 BlogSetting 创建 VO 对象
     *
     * @param blogSetting 博客信息
     * @return 返回 VO 对象
     */
    public static BlogInfoVO fromSetting(BlogSetting blogSetting) {
        return new Converter().convertToVO(blogSetting);
    }

    /**
     * 实现接口方法
     */
    private static class Converter implements IConverter<BlogSetting, BlogInfoVO> {

        @Override
        public BlogInfoVO convertToVO(BlogSetting blogSetting) {
            final BlogInfoVO vo = new BlogInfoVO();
            BeanUtil.copyProperties(blogSetting, vo, CopyOptions.create().ignoreNullValue().ignoreError());
            vo.setTitle(blogSetting.getTitle());
            vo.setDesc(blogSetting.getDesc());
            vo.setCovers(blogSetting.getCovers());
            vo.setAvatar(blogSetting.getAvatar());
            vo.setNickname(blogSetting.getNickname());
            return vo;
        }
    }
}
