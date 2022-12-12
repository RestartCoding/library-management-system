package com.xb.library.management.system.service.impl;

import com.xb.library.management.system.PasswordEncryptor;
import com.xb.library.management.system.domain.User;
import com.xb.library.management.system.exception.BusinessException;
import com.xb.library.management.system.repository.UserRepository;
import com.xb.library.management.system.service.UserService;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
}
