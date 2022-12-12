package com.xb.library.management.system.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.annotation.Jsr250Voter;
import org.springframework.security.access.expression.method.ExpressionBasedPreInvocationAdvice;
import org.springframework.security.access.prepost.PreInvocationAuthorizationAdviceVoter;
import org.springframework.security.access.vote.*;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiabiao
 * @date 2022-12-08
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomizerMethodSecurityConfiguration extends GlobalMethodSecurityConfiguration {

    @Bean
    @Override
    protected AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<?>> decisionVoters = new ArrayList<>();

        ExpressionBasedPreInvocationAdvice expressionAdvice =
                new ExpressionBasedPreInvocationAdvice();
        expressionAdvice.setExpressionHandler(getExpressionHandler());
        decisionVoters
                .add(new PreInvocationAuthorizationAdviceVoter(expressionAdvice));
        decisionVoters.add(new Jsr250Voter());
        RoleVoter roleVoter = new RoleVoter();
        roleVoter.setRolePrefix("ROLE_");
        decisionVoters.add(roleVoter);
        decisionVoters.add(new AuthenticatedVoter());

//        decisionVoters.add(new TimeVoter());

        return new UnanimousBased(decisionVoters);
    }


}
