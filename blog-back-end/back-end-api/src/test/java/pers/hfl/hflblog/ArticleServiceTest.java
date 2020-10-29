package pers.hfl.hflblog;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pers.hfl.hflblog.dao.ArticleMapper;
import pers.hfl.hflblog.model.entity.ArticlePO;

import java.util.Date;

/**
 * @ClassName ArticleServiceTest
 * @Author HFL
 * @Date 2020/10/29/029 16:53
 * @Version V1.0
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BlogApplication.class})
public class ArticleServiceTest {
    @Autowired
    private ArticleMapper articleMapper;

    @Test
    public void insArticle() {
        for (int i = 0; i < 20; i++) {
            ArticlePO article = articleMapper.selectById(i + 7);
            article.setInsertTime(new Date(System.currentTimeMillis()));
            articleMapper.updateById(article);
        }
    }
}
