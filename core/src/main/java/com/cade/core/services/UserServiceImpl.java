package com.cade.core.services;

import com.cade.core.domain.User;
import com.cade.core.ports.driven.UserRepository;
import com.cade.core.ports.driver.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User create(final User user) {
        log.info("USER SERVICE CREATE");

        return userRepository.create(user);
    }

}
