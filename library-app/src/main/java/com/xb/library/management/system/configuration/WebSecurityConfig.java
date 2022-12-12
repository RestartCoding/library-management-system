package com.xb.library.management.system.configuration;

import com.xb.library.management.system.domain.UserRole;
import com.xb.library.management.system.repository.UserRoleRepository;
import com.xb.library.management.system.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xiabiao
 * @date 2022-12-05
 */
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserService userService;

    @Resource
    private UserRoleRepository userRoleRepository;

    @Resource
    private ApplicationContext ac;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.logout().addLogoutHandler(new LogoutRecorder()).invalidateHttpSession(true);

        http.formLogin().loginPage("/login.html").loginProcessingUrl("/user/login").permitAll();

        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.authorizeRequests().antMatchers("/user/signUp", "/signUp.html").permitAll();
        http.authorizeRequests().anyRequest().authenticated();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return authentication -> {
            if (authentication instanceof UsernamePasswordAuthenticationToken) {
                UsernamePasswordAuthenticationToken token =
                        (UsernamePasswordAuthenticationToken) authentication;
                String username = (String) token.getPrincipal();
                String password = (String) token.getCredentials();
                userService.login(username, password);
                // 认证成功，设置角色
                List<UserRole> userRoles = userRoleRepository.findAllByUsername(username);
                String[] roles =
                        userRoles.stream().map(e -> "ROLE_" + e.getRoleName()).toArray(String[]::new);
                return new UsernamePasswordAuthenticationToken(
                        username, password, AuthorityUtils.createAuthorityList(roles));
            }
            return authentication;
        };
    }
}
