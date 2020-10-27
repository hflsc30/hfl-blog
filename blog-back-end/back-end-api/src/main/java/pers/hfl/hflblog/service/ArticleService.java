package pers.hfl.hflblog.service;

import org.springframework.stereotype.Service;
import pers.hfl.hflblog.model.dto.ArticleDTO;
import pers.hfl.hflblog.model.vo.ArticleVO;
import pers.hfl.hflblog.model.vo.PageVO;

/**
 * @auther HFL
 * @date 2020/10/27/027 17:47
 * @description
 */
public interface ArticleService {
    PageVO<ArticleVO> getArticles(int page, int limit);

    String insArticle(ArticleDTO articleDTO);

    ArticleVO findById(Integer id);

    void deleteArticle(Integer id);

    void updateArticle(ArticleDTO articleDTO, Integer id);
}
