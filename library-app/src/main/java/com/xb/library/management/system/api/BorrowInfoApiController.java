package com.xb.library.management.system.api;

import com.xb.library.management.system.domain.ApiPage;
import com.xb.library.management.system.domain.ApiResponse;
import com.xb.library.management.system.domain.BorrowInfo;
import com.xb.library.management.system.domain.PageInfo;
import com.xb.library.management.system.service.BorrowInfoService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author jack
 */
@RestController
public class BorrowInfoApiController implements BorrowInfoApi {

    @Resource
    private BorrowInfoService borrowInfoService;

    @Override
    public ApiResponse<ApiPage<BorrowInfo>> page(PageInfo pageInfo) {
        ApiPage<BorrowInfo> page = borrowInfoService.page(pageInfo);
        return ApiResponse.<ApiPage<BorrowInfo>>builder().code(0).message("operate success").data(page).build();
    }
}
