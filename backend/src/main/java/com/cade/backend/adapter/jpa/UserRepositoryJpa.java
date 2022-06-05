package com.cade.backend.adapter.jpa;

import com.cade.core.domain.UserDomain;
import com.cade.core.ports.driven.UserRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepositoryJpa implements UserRepository {

    @Override
    public UserDomain getUser() {
        var usr = new UserDomain();
        usr.email = "ab@bb.com";
        return usr;
    }

}
