package pers.hfl.hflblog.controller.comm;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.hfl.hflblog.exception.BlogException;
import pers.hfl.hflblog.model.enums.Impl.ErrorInfoEnum;
import pers.hfl.hflblog.model.vo.BlogInfoVO;
import pers.hfl.hflblog.model.vo.Results;
import pers.hfl.hflblog.service.CommService;

/**
 * @ClassName CommController
 * @Author HFL
 * @Date 2020/10/23/023 14:27
 * @Version V1.0
 **/
@Api("通用接口")
@RestController
public class CommController {

    @Autowired
    private CommService commService;

    @ApiOperation("检查服务端是否正常")
    @GetMapping("/ping")
    public Results ping() {
        throw new BlogException(ErrorInfoEnum.MISSING_PARAMETERS);
    }

    @ApiOperation("获取博客信息")
    @GetMapping("/info")
    public Results<BlogInfoVO> info() {
        BlogInfoVO blogInfo = commService.getBlogInfo();
        return Results.ok(blogInfo);
    }
}
