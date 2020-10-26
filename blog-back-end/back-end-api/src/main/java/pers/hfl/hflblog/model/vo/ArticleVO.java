package pers.hfl.hflblog.model.vo;/**
 * @auther HFL
 * @date 2020/10/26/026 11:41
 * @description
 */

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.Data;
import pers.hfl.hflblog.model.entity.ArticlePO;

import java.util.Date;

/**
 * @ClassName ArticleVO
 * @Author HFL
 * @Date 2020/10/26/026 11:41
 * @Version V1.0
 **/
@Data
public class ArticleVO {
    private Integer id;
    private Integer catalog;
    private Integer articleType;
    private String author;
    private String articleTitle;
    private String articleSubtitle;
    private String tags;
    private String keywords;
    private String digest;
    private String imageList;
    private Boolean isShowReleaseTime;
    private Boolean isTop;
    private Integer topNum;
    private Date topEndTime;
    private Boolean isRecommend;
    private Integer recommendNum;
    private Date recommendEndTime;
    private Boolean isCommentable;
    private String articleContent;
    private String source;
    private String sourceUrl;
    private Integer orderNum;
    private Integer clickCount;
    private Integer agreeCount;
    private Integer shareCount;
    private Date releaseTime;
    private Integer releaseUser;
    private Boolean isDelete;
    private Date insertTime;
    private Date updateTime;

    /**
     * 根据 PO 创建 VO 对象
     *
     * @param articlePO PO对象
     * @return VO对象
     */
    public static ArticleVO fromArticlePO(ArticlePO articlePO) {
        return new Converter().convertToVO(articlePO);
    }

    /**
     * 实现接口方法
     */
    private static class Converter implements IConverter<ArticlePO, ArticleVO> {

        @Override
        public ArticleVO convertToVO(ArticlePO article) {
            final ArticleVO vo = new ArticleVO();
            BeanUtil.copyProperties(article, vo, CopyOptions.create().ignoreNullValue().ignoreError());
            vo.setTags(article.getTags());
            return vo;
        }
    }
}
