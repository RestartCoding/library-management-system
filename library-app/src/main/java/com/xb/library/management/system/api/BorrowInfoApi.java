package com.xb.library.management.system.api;

import com.xb.library.management.system.domain.ApiPage;
import com.xb.library.management.system.domain.ApiResponse;
import com.xb.library.management.system.domain.BorrowInfo;
import com.xb.library.management.system.domain.PageInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * @author jack
 */
@RequestMapping("/borrow-info")
public interface BorrowInfoApi {

    /**
     * 分页查询
     *
     * @param pageInfo pageInfo
     * @return page
     */
    @GetMapping("/page")
    ApiResponse<ApiPage<BorrowInfo>> page(@Valid PageInfo pageInfo);
}
