package com.xb.library.management.system.repository;

import com.xb.library.management.system.domain.BorrowInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author jack
 */
public interface BorrowInfoRepository extends JpaRepository<BorrowInfo, Long>, JpaSpecificationExecutor<BorrowInfo> {
}
