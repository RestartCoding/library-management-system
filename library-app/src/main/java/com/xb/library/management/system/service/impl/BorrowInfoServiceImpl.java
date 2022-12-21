package com.xb.library.management.system.service.impl;

import com.xb.library.management.system.domain.ApiPage;
import com.xb.library.management.system.domain.BorrowInfo;
import com.xb.library.management.system.domain.PageInfo;
import com.xb.library.management.system.repository.BorrowInfoRepository;
import com.xb.library.management.system.service.BorrowInfoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author jack
 */
@Service
public class BorrowInfoServiceImpl implements BorrowInfoService {

    @Resource
    private BorrowInfoRepository borrowInfoRepository;

    @Override
    public ApiPage<BorrowInfo> page(PageInfo pageInfo) {
        Page<BorrowInfo> page = borrowInfoRepository.findAll(PageRequest.of(pageInfo.getPageNum() - 1, pageInfo.getPageSize()));
        return ApiPage.fromPage(page);
    }
}
