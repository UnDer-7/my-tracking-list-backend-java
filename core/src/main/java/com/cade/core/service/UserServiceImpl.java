package com.cade.core.service;

import com.cade.core.domain.UserDomain;
import com.cade.core.ports.driven.UserRepository;
import com.cade.core.ports.driver.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    @Inject UserRepository userRepository;

    @Override
    public UserDomain getUser() {
        return userRepository.getUser();
    }

}
