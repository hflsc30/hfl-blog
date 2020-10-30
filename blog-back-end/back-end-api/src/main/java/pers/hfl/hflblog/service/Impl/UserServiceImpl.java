package pers.hfl.hflblog.service.Impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.crypto.digest.MD5;
import cn.hutool.setting.Setting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.hfl.hflblog.exception.BlogException;
import pers.hfl.hflblog.model.dto.UserDTO;
import pers.hfl.hflblog.model.enums.Impl.ErrorInfoEnum;
import pers.hfl.hflblog.model.enums.Impl.UserRoleEnum;
import pers.hfl.hflblog.service.UserService;
import pers.hfl.hflblog.utils.JwtUtils;

import java.util.Objects;

/**
 * @ClassName UserServiceImpl
 * @Author HFL
 * @Date 2020/10/30/030 15:59
 * @Version V1.0
 **/
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private Setting setting;

    @Override
    public String checkUsernamePassword(UserDTO userDTO) {
        String username = setting.getStr("username");
        String password = setting.getStr("password");
        if (Objects.equals(username, userDTO.getUsername()) &&
                Objects.equals(password, MD5.create().digestHex(userDTO.getPassword()))) {
            return JwtUtils.createToken(username, CollUtil.newArrayList(UserRoleEnum.ADMIN.getValue()));
        } else {
            throw new BlogException(ErrorInfoEnum.USERNAME_PASSWORD_ERROR);
        }
    }
}
