package pers.hfl.hflblog.controller;

import cn.hutool.core.map.MapUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pers.hfl.hflblog.model.dto.UserDTO;
import pers.hfl.hflblog.model.vo.Results;
import pers.hfl.hflblog.service.UserService;

import javax.validation.Valid;
import java.util.Map;

/**
 * @ClassName UserController
 * @Author HFL
 * @Date 2020/10/30/030 16:02
 * @Version V1.0
 **/
@Api("与用户相关的Api接口")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public Results<Map<String, Object>> login(
            @ApiParam(name = "用户登录信息", value = "传入json格式", required = true)
            @RequestBody
            @Valid UserDTO userDTO) {
        String token = userService.checkUsernamePassword(userDTO);
        return Results.ok("登录成功", MapUtil.of("token", token));
    }
}
