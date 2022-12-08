package com.xb.library.management.system.configuration;

import com.xb.library.management.system.domain.SysConstant;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.acls.domain.*;
import org.springframework.security.acls.jdbc.BasicLookupStrategy;
import org.springframework.security.acls.jdbc.JdbcMutableAclService;
import org.springframework.security.acls.jdbc.LookupStrategy;
import org.springframework.security.acls.model.AclCache;
import org.springframework.security.acls.model.AclService;
import org.springframework.security.acls.model.PermissionGrantingStrategy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import javax.sql.DataSource;

/**
 * @author xiabiao
 * @date 2022-12-07
 */
@Configuration
public class AclConfig {

    @Bean
    public AclService aclService(DataSource dataSource) {
        AclAuthorizationStrategyImpl authorizationStrategy = new AclAuthorizationStrategyImpl(AuthorityUtils.createAuthorityList(SysConstant.ROLE_ADMINISTRATOR, SysConstant.ROLE_ADMINISTRATOR, SysConstant.ROLE_ADMINISTRATOR).toArray(new GrantedAuthority[0]));
        AuditLogger auditLogger = new ConsoleAuditLogger();
        PermissionGrantingStrategy permissionGrantingStrategy = new DefaultPermissionGrantingStrategy(auditLogger);
        AclCache cache = new SpringCacheBasedAclCache(new ConcurrentMapCache("aclCache"), permissionGrantingStrategy, authorizationStrategy);
        LookupStrategy lookupStrategy = new BasicLookupStrategy(dataSource, cache, authorizationStrategy, auditLogger);
        return new JdbcMutableAclService(dataSource, lookupStrategy, cache);
    }
}
