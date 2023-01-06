package com.xb.library.management.system.service.impl;

import com.xb.library.management.system.PasswordEncryptor;
import com.xb.library.management.system.api.vo.UserPageReq;
import com.xb.library.management.system.domain.PageInfo;
import com.xb.library.management.system.domain.User;
import com.xb.library.management.system.exception.BusinessException;
import com.xb.library.management.system.repository.UserRepository;
import com.xb.library.management.system.service.UserService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author xiabiao
 * @date 2022-12-05
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(User user) {
        user.setPassword(PasswordEncryptor.encrypt(user.getPassword()));
        userRepository.saveAndFlush(user);
    }

    @Override
    public void deleteUser(String username) throws BusinessException {
        int rows = userRepository.deleteByUsername(username);
        if (rows == 0) {
            throw new BusinessException("The use does not exist.");
        }
    }

    @NonNull
    @Override
    public User login(String username, String password) {
        Optional<User> optionalUser =
                userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            boolean matches = passwordEncoder.matches(password, optionalUser.get().getPassword());
            if (!matches) {
                throw new BusinessException("Login error.");
            }
        }
        return optionalUser.orElseThrow(() -> new BusinessException("Login error."));
    }

    @Override
    public void register(User user) {
        Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());
        if (optionalUser.isPresent()) {
            throw new BusinessException("The user already exists.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.saveAndFlush(user);
    }

    @Override
    public Page<User> page(PageInfo pageInfo, UserPageReq req) {
        User user = new User();
        user.setUsername(req.getUsername());

        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("username", ExampleMatcher.GenericPropertyMatcher::exact);
        Example<User> example = Example.of(user, matcher);

        return userRepository.findAll(example, PageRequest.of(pageInfo.getPageNum() - 1, pageInfo.getPageSize()));
    }

    @Override
    public User profile() {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Optional<User> optionalUser = userRepository.findByUsername(username);
        Assert.isTrue(optionalUser.isPresent());
        return optionalUser.get();
    }
}
