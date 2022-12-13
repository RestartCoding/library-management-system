package com.xb.library.management.system.service;

import com.xb.library.management.system.api.vo.UserPageReq;
import com.xb.library.management.system.domain.ApiPage;
import com.xb.library.management.system.domain.PageInfo;
import com.xb.library.management.system.domain.User;
import com.xb.library.management.system.exception.BusinessException;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;

/**
 * @author xiabiao
 * @date 2022-12-05
 */
public interface UserService {

    /**
     * 保存用户
     *
     * @param user user
     */
    void saveUser(User user);

    /**
     * 删除用户
     *
     * @param username username
     * @throws BusinessException BusinessException
     */
    void deleteUser(String username) throws BusinessException;

    /**
     * 登录
     *
     * @param username username
     * @param password password
     * @return user
     * @throws BusinessException BusinessException
     */
    @NonNull
    User login(String username, String password) throws BusinessException;

    /**
     * 注册用户
     *
     * @param user user
     */
    void register(User user);

    /**
     * 用户分页查询
     *
     * @param pageInfo pageInfo
     * @param req      req
     * @return 用户分页
     */
    Page<User> page(PageInfo pageInfo, UserPageReq req);
}
