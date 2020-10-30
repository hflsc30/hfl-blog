package pers.hfl.hflblog.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @ClassName UserDTO
 * @Author HFL
 * @Date 2020/10/30/030 15:52
 * @Version V1.0
 **/
@Data
@ApiModel(value = "用户登录信息类",description = "前端传入的用户登录信息")
public class UserDTO {
    @NotEmpty(message = "用户名不能为空")
    @ApiModelProperty(notes = "用户名",example = "admin")
    private String username;
    @NotEmpty(message = "用户密码不能为空")
    @ApiModelProperty(notes = "用户密码",example = "admin")
    private String password;
}
