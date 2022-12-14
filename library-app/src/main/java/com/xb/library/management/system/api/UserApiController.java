package com.xb.library.management.system.api;

import com.xb.library.management.system.api.vo.UserPageReq;
import com.xb.library.management.system.domain.ApiPage;
import com.xb.library.management.system.domain.ApiResponse;
import com.xb.library.management.system.domain.PageInfo;
import com.xb.library.management.system.domain.User;
import com.xb.library.management.system.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author xiabiao
 * @date 2022-12-05
 */
@RestController
public class UserApiController implements UserApi {

    private static final String USER_ATTR_NAME = "user";

    /**
     * about 30 days
     */
    private static final int SESSION_EXPIRE_SECONDS = 60 * 60 * 24 * 30;

    @Resource
    private UserService userService;

    @Resource
    private HttpServletRequest request;

    @Resource
    private UserTokenManager userTokenManager;

    @Override
    public ApiResponse<User> saveUser(User user) {
        userService.saveUser(user);
        return ApiResponse.<User>builder().code(0).data(user).build();
    }

    @Override
    public ApiResponse<Void> delete(String username) {
        userService.deleteUser(username);
        return ApiResponse.<Void>builder().code(0).message("operation success.").build();
    }

    @Override
    public ApiResponse<Void> register(User user) {
        userService.register(user);
        return ApiResponse.<Void>builder().code(0).build();
    }

    @Override
    public ApiResponse<ApiPage<User>> page(@Valid PageInfo pageInfo, @Valid UserPageReq userPageReq) {
        Page<User> page = userService.page(pageInfo, userPageReq);
        ApiPage<User> apiPage = ApiPage.fromPage(page);
        return ApiResponse.<ApiPage<User>>builder().code(0).data(apiPage).build();
    }

    @Override
    public ApiResponse<User> profile() {
        User user = userService.profile();
        return ApiResponse.<User>builder().code(0).data(user).build();
    }
}
