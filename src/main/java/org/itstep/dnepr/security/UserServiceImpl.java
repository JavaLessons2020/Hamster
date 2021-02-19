package org.itstep.dnepr.security;

import org.itstep.dnepr.model.User;
import org.itstep.dnepr.repository.UserJPA;
import org.itstep.dnepr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserDetailsService {

    private UserJPA userJPA;

    @Autowired
    public void setUserJPA(UserJPA userJPA) {
        this.userJPA = userJPA;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userJPA.getByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomSecurityUser(user);
    }
}
