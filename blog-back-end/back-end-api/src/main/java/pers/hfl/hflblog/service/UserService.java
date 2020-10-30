package pers.hfl.hflblog.service;

import pers.hfl.hflblog.model.dto.UserDTO;

/**
 * @author HFL
 * @date 2020/10/30/030 15:51
 * @description
 */
public interface UserService {
    /**
     * 校验用户名和密码
     *
     * @param userDTO 用户对象
     * @return 校验成功就返回token
     */
    String checkUsernamePassword(UserDTO userDTO);
}
