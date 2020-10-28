package pers.hfl.hflblog.service.Impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.setting.Setting;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.hfl.hflblog.dao.ArticleMapper;
import pers.hfl.hflblog.model.comm.BlogSetting;
import pers.hfl.hflblog.model.entity.ArticlePO;
import pers.hfl.hflblog.model.vo.BlogInfoVO;
import pers.hfl.hflblog.service.CommService;

import java.util.List;
import java.util.Map;

/**
 * @ClassName CommServiceImpl
 * @Author HFL
 * @Date 2020/10/28/028 11:37
 * @Version V1.0
 **/
@Service
public class CommServiceImpl implements CommService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private Setting setting;

    @Override
    public BlogInfoVO getBlogInfo() {
        BlogSetting blogSetting = BlogSetting.fromSetting(setting);
        BlogInfoVO vo = BlogInfoVO.fromSetting(blogSetting);
        QueryWrapper<ArticlePO> wrapper = new QueryWrapper<>();
        wrapper.select("sum(click_count) as total_click_count");
        List<Map<String, Object>> maps = articleMapper.selectMaps(wrapper);
        int totalClickCount = 0;
        if (!maps.isEmpty()) {
            totalClickCount = Convert.toInt(maps.get(0).get("total_click_count"), 0);
        }
        Integer articleCount = articleMapper.selectCount(null);
        vo.setTotalClickCount(totalClickCount);
        vo.setArticleCount(articleCount);
        return vo;
    }
}
