package pers.hfl.hflblog.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.hfl.hflblog.dao.ArticleMapper;
import pers.hfl.hflblog.exception.BlogException;
import pers.hfl.hflblog.model.dto.ArticleDTO;
import pers.hfl.hflblog.model.entity.ArticlePO;
import pers.hfl.hflblog.model.enums.Impl.ErrorInfoEnum;
import pers.hfl.hflblog.model.vo.ArticleVO;
import pers.hfl.hflblog.model.vo.PageVO;
import pers.hfl.hflblog.service.ArticleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @ClassName ArticleServiceImpl
 * @Author HFL
 * @Date 2020/10/27/027 17:43
 * @Version V1.0
 **/
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public PageVO<ArticleVO> getArticles(int page, int limit) {
        QueryWrapper<ArticlePO> qw = new QueryWrapper<>();
        qw.select(ArticlePO.class, i -> !"content".equals(i.getColumn()));
        Page<ArticlePO> res = articleMapper.selectPage(new Page<>(page, limit), qw);
        List<ArticleVO> articleVOS = res.getRecords().stream()
                .map(ArticleVO::fromArticlePO)
                .collect(Collectors.toList());
        PageVO<ArticleVO> pageVO = PageVO.<ArticleVO>builder()
                .records(articleVOS.isEmpty() ? new ArrayList<>() : articleVOS)
                .total(res.getTotal())
                .current(res.getCurrent())
                .size(res.getSize())
                .build();
        return pageVO;
    }

    @Override
    public String insArticle(ArticleDTO articleDTO) {
        ArticlePO po = articleDTO.toArticlePO(false);
        articleMapper.insert(po);
        return "success insert";
    }

    @Override
    public ArticleVO findById(Integer id) {
        ArticlePO articlePO = articleMapper.selectById(id);
        if (Objects.isNull(articlePO)) {
            throw new BlogException(ErrorInfoEnum.INVALID_ID);
        }
        return ArticleVO.fromArticlePO(articlePO);
    }

    @Override
    public void deleteArticle(Integer id) {
        int isSuccess = articleMapper.deleteById(id);
        if (isSuccess <= 0) {
            throw new BlogException(ErrorInfoEnum.INVALID_ID);
        }
    }

    @Override
    public void updateArticle(ArticleDTO articleDTO, Integer id) {
        ArticlePO dbArticle = articleMapper.selectById(id);
        if (Objects.isNull(dbArticle)) {
            throw new BlogException(ErrorInfoEnum.INVALID_ID);
        }
        ArticlePO articlePO = articleDTO.toArticlePO(true);
        articlePO.setId(id);
        articleMapper.updateById(articlePO);
    }
}
