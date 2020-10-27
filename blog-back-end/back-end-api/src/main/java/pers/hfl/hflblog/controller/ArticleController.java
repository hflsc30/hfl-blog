package pers.hfl.hflblog.controller;

import cn.hutool.core.map.MapUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.hfl.hflblog.model.dto.ArticleDTO;
import pers.hfl.hflblog.model.entity.ArticlePO;
import pers.hfl.hflblog.model.vo.ArticleVO;
import pers.hfl.hflblog.model.vo.PageVO;
import pers.hfl.hflblog.model.vo.Results;
import pers.hfl.hflblog.service.ArticleService;

import javax.validation.Valid;
import java.util.Map;

/**
 * @ClassName ArticleController
 * @Author HFL
 * @Date 2020/10/27/027 15:17
 * @Version V1.0
 **/
@Api("与文章相关的api接口")
@RestController
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @ApiOperation("批量获取文章")
    @GetMapping("/articles")
    public Results<PageVO> getArticles(
            @ApiParam("页码")
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @ApiParam("每页存放的记录数")
            @RequestParam(required = false, defaultValue = "5") Integer limit) {
        return Results.ok(articleService.getArticles(page, limit));
    }

    @PostMapping("/articles")
    @ApiOperation("新增文章")
    public Results postArticles(
            @ApiParam(name = "文章信息", value = "传入json格式", required = true)
            @RequestBody
            @Valid
                    ArticleDTO articleDTO) {
        String info = articleService.insArticle(articleDTO);
        return Results.ok(MapUtil.of("info", info));
    }

    @GetMapping("/article/{id}")
    @ApiOperation("根据id查询文章信息")
    @ApiImplicitParam(name = "id", value = "文章id", required = true, dataType = "Integer", paramType = "path")
    public Results<ArticleVO> getArticle(@PathVariable Integer id) {
        ArticleVO articleVO = articleService.findById(id);
        return Results.ok(articleVO);
    }

    @DeleteMapping("/article/{id}")
    @ApiOperation("根据id删除文章信息")
    @ApiImplicitParam(name = "id", value = "文章id", required = true, dataType = "Integer", paramType = "path")
    public Results deleteArticle(@PathVariable Integer id) {
        articleService.deleteArticle(id);
        return Results.ok("删除成功", null);
    }

    @PutMapping("/article/{id}")
    @ApiOperation("根据id修改文章")
    @ApiImplicitParam(name = "id", value = "文章id", required = true, dataType = "Integer", paramType = "path")
    public Results<Map<String, Object>> putArticle(
            @ApiParam(name = "要修改的文章信息", value = "传入json格式", required = true)
            @RequestBody ArticleDTO articleDTO,
            @PathVariable Integer id) {
        articleService.updateArticle(articleDTO, id);
        return Results.ok("更新成功", MapUtil.of("id", id));
    }
}