package pers.hfl.hflblog.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName ArticlePO
 * @Author HFL
 * @Date 2020/10/26/026 10:47
 * @Version V1.0
 **/
@Data
@TableName("t_article")
public class ArticlePO implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private Integer catalogId;
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
}
