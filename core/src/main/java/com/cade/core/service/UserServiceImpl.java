package com.cade.core.service;

import com.cade.core.domain.UserDomain;
import com.cade.core.ports.driven.UserRepository;
import com.cade.core.ports.driver.UserService;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDomain getUser() {
        return userRepository.getUser();
    }

}
