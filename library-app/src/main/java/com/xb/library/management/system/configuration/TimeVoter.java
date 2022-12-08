package com.xb.library.management.system.configuration;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;

import java.time.LocalTime;
import java.util.Collection;

/**
 * @author xiabiao
 * @date 2022-12-08
 */
public class TimeVoter implements AccessDecisionVoter<Object> {
    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
        if (LocalTime.now().getMinute() % 2 != 0) {
            return AccessDecisionVoter.ACCESS_DENIED;
        }
        return AccessDecisionVoter.ACCESS_GRANTED;
    }
}
