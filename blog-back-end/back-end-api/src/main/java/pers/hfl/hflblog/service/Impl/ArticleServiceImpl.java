package pers.hfl.hflblog.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.hfl.hflblog.dao.ArticleMapper;
import pers.hfl.hflblog.exception.BlogException;
import pers.hfl.hflblog.model.dto.ArticleDTO;
import pers.hfl.hflblog.model.entity.ArticlePO;
import pers.hfl.hflblog.model.enums.Impl.ErrorInfoEnum;
import pers.hfl.hflblog.model.vo.ArticleVO;
import pers.hfl.hflblog.model.vo.PageVO;
import pers.hfl.hflblog.model.vo.TimelineVO;
import pers.hfl.hflblog.service.ArticleService;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName ArticleServiceImpl
 * @Author HFL
 * @Date 2020/10/27/027 17:43
 * @Version V1.0
 **/
@Service
@Slf4j
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
    public ArticleVO getById(Integer id) {
        ArticlePO articlePO = articleMapper.selectById(id);
        if (Objects.isNull(articlePO)) {
            throw new BlogException(ErrorInfoEnum.INVALID_ID);
        }
        articlePO.setClickCount(articlePO.getClickCount() + 1);
        articleMapper.updateById(articlePO);
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

    @Override
    public Set<String> findTags() {
        QueryWrapper<ArticlePO> wrapper = new QueryWrapper<>();
        wrapper.select("tags");
        List<ArticlePO> articles = articleMapper.selectList(wrapper);
        Set<String> tags = articles.stream()
                .map(ArticlePO::getTags)
                .flatMap(s -> Arrays.stream(s.split(",")))
                .collect(Collectors.toSet());
        log.info("tags: {}", tags);
        return tags.isEmpty() ? new HashSet<>() : tags;
    }

    @Override
    public PageVO<ArticleVO> findArticleByTag(String tagName, Integer page, Integer limit) {
        QueryWrapper<ArticlePO> wrapper = new QueryWrapper<>();
        wrapper.select(ArticlePO.class, i -> !"article_content".equals(i.getColumn()))
                .like("tags", tagName);
        Page<ArticlePO> res = articleMapper.selectPage(new Page<>(page, limit), wrapper);
        List<ArticleVO> articleVOS = res.getRecords().stream()
                .map(ArticleVO::fromArticlePO)
                .collect(Collectors.toList());
        PageVO<ArticleVO> pv = PageVO.<ArticleVO>builder()
                .records(articleVOS.isEmpty() ? new ArrayList<>() : articleVOS)
                .total(res.getTotal())
                .current(res.getCurrent())
                .size(res.getSize())
                .build();
        return pv;
    }

    @Override
    public List<TimelineVO> timeline() {
        ArrayList<TimelineVO> res = new ArrayList<>();
        QueryWrapper<ArticlePO> wrapper = new QueryWrapper<>();
        wrapper.select("id", "article_title", "insert_time");
        List<Map<String, Object>> maps = articleMapper.selectMaps(wrapper);
        maps.stream().map(m -> TimelineVO.Item.builder()
                .id((Integer) m.get("id"))
                .insertTime((Date) m.get("insert_time"))
                .title((String) m.get("article_title"))
                .build())
                .collect(Collectors.groupingBy(item -> {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    String[] arr = format.format(item.getInsertTime()).split("-");
                    return arr[0];
                })).forEach((k, v) -> res.add(TimelineVO.builder().year(k).items(v).build()));
        res.sort(Comparator.comparing(TimelineVO::getYear).reversed());
        return res;
    }
}
