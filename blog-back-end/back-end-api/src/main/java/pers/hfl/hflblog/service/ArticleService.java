package pers.hfl.hflblog.service;

import pers.hfl.hflblog.model.dto.ArticleDTO;
import pers.hfl.hflblog.model.vo.ArticleVO;
import pers.hfl.hflblog.model.vo.PageVO;
import pers.hfl.hflblog.model.vo.TimelineVO;

import java.util.List;
import java.util.Set;

/**
 * @author HFL
 * @date 2020/10/27 17:47
 * @description 文章接口
 */
public interface ArticleService {

    /**
     * 文章列表
     *
     * @param page  页数
     * @param limit 一页显示的行数
     * @return 返回文章
     */
    PageVO<ArticleVO> getArticles(int page, int limit);

    /**
     * 新建文章
     *
     * @param articleDTO 前端获取的文章信息
     * @return 返回插入信息
     */
    String insArticle(ArticleDTO articleDTO);

    /**
     * 根据id查找文章
     *
     * @param id 文章id
     * @return 返回文章
     */
    ArticleVO getById(Integer id);

    /**
     * 根据id删除文章
     *
     * @param id 文章id
     */
    void deleteArticle(Integer id);

    /**
     * 修改文章
     *
     * @param articleDTO 前端获取的文章信息
     * @param id         已存在的文章id
     */
    void updateArticle(ArticleDTO articleDTO, Integer id);

    /**
     * 获取文章标签
     *
     * @return 返回标签集合
     */
    Set<String> findTags();

    /**
     * 根据标签名获取文章列表
     *
     * @param tagName 标签名
     * @param page    页数
     * @param limit   每页存放的文章数
     * @return 返回文章列表
     */
    PageVO<ArticleVO> findArticleByTag(String tagName, Integer page, Integer limit);

    /**
     * 时间线功能
     *
     * @return 返回时间线
     */
    List<TimelineVO> timeline();

}
