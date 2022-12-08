package com.xb.library.management.system;

import com.xb.library.management.system.domain.SysConstant;
import com.xb.library.management.system.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * @author xiabiao
 * @date 2022-12-07
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AclTest {

    @Resource
    private MutableAclService aclService;

    @Transactional
    @Test
    public void test() {
        ObjectIdentity oi = new ObjectIdentityImpl(User.class, 1);
        Sid sid = new PrincipalSid("admin");
        Permission p = BasePermission.ADMINISTRATION;
        MutableAcl acl = null;
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken("admin", "admin", AuthorityUtils.commaSeparatedStringToAuthorityList(SysConstant.ROLE_ADMINISTRATOR));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        try {
            acl = (MutableAcl) aclService.readAclById(oi);
        } catch (NotFoundException nfe) {
            acl = aclService.createAcl(oi);
        }

        acl.insertAce(acl.getEntries().size(), p, sid, true);
        aclService.updateAcl(acl);
    }
}
