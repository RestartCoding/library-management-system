package com.xb.library.management.system.service;

import com.xb.library.management.system.domain.ApiPage;
import com.xb.library.management.system.domain.BorrowInfo;
import com.xb.library.management.system.domain.PageInfo;

/**
 * @author jack
 */
public interface BorrowInfoService {

    /**
     * 分页查询
     *
     * @param pageInfo pageInfo
     * @return borrow info
     */
    ApiPage<BorrowInfo> page(PageInfo pageInfo);
}
