package pers.hfl.hflblog.model.dto;/**
 * @auther HFL
 * @date 2020/10/27/027 15:47
 * @description
 */

import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import pers.hfl.hflblog.model.entity.ArticlePO;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * @ClassName ArticleDTO
 * @Author HFL
 * @Date 2020/10/27/027 15:47
 * @Version V1.0
 **/
@Data
@ApiModel(value = "文章类", description = "前端传入的文章信息")
public class ArticleDTO {
    @NotEmpty(message = "文章作者不能为空")
    @ApiModelProperty(notes = "文章作者", example = "莫言")
    private String author;
    @NotEmpty(message = "文章标题不能为空")
    @ApiModelProperty(notes = "文章标题", example = "三体")
    private String articleTitle;
    @NotEmpty(message = "标签不能为空")
    @ApiModelProperty(notes = "标签", example = "科幻,长篇小说")
    private String tags;
    @NotEmpty(message = "关键词不能为空")
    @ApiModelProperty(notes = "关键词", example = "黑洞,维度,未来")
    private String keywords;
    @NotEmpty(message = "摘要不能为空")
    @ApiModelProperty(notes = "摘要", example = "三体是描绘......")
    private String digest;
    @NotEmpty
    @ApiModelProperty(notes = "文章内容", example = "二十三世纪......")
    private String articleContent;
    @Range(min = 1, message = "排序号最小为1")
    @ApiModelProperty(notes = "排序号", example = "1")
    private Integer orderNum;

    /**
     * 将DTO转换为PO
     *
     * @param isUpdate 此对象是否为更新对象
     * @return 转换结果
     */
    public ArticlePO toArticlePO(boolean isUpdate) {
        ArticlePO po = new Converter().convertToVO(this);
        po.setInsertTime(isUpdate ? null : po.getUpdateTime());
        return po;
    }

    private static class Converter implements IConverter<ArticleDTO, ArticlePO> {

        @Override
        public ArticlePO convertToVO(ArticleDTO articleDTO) {
            ArticlePO po = new ArticlePO();
            po.setClickCount(0);
            po.setUpdateTime(new Date(System.currentTimeMillis()));
            BeanUtil.copyProperties(articleDTO, po);
            return po;
        }
    }
}
