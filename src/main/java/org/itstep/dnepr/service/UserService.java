package org.itstep.dnepr.service;


import org.itstep.dnepr.model.Role;
import org.itstep.dnepr.model.User;
import org.itstep.dnepr.repository.UserJPA;
import org.itstep.dnepr.security.CustomSecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService{

    private UserJPA userJPA;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserJPA(UserJPA userJPA) {
        this.userJPA = userJPA;
    }

    public User getById(Long id) {
        return userJPA.getById(id);
    }

    public User getByEmail(String email) {
        return userJPA.getByEmail(email);
    }

    public Boolean save(User user) {
        User userFromDB = userJPA.getByEmail(user.getEmail());
        if (userFromDB != null) { // если существует
            return false;
        }
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userJPA.save(user);
        return true;
    }

    public List<User> allUsers() {
        return userJPA.findAll();
    }
}
