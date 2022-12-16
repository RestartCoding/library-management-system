package com.xb.library.management.system.repository;

import com.xb.library.management.system.domain.BorrowInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * @author jack
 */
public interface BorrowInfoRepository extends JpaRepository<BorrowInfo, Long>, JpaSpecificationExecutor<BorrowInfo> {

    /**
     * 用户名和isbn查找
     *
     * @param username username
     * @param isbn     isbn
     * @return borrow info
     */
    Optional<BorrowInfo> findByUsernameAndIsbn(String username, String isbn);

    /**
     * 根据 isbn 和 status 查找
     *
     * @param isbn isbn
     * @return borrow info
     */
    Optional<BorrowInfo> findByIsbnAndReturnTimeIsNull(String isbn);
}
