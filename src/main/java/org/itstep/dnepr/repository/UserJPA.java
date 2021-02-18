package org.itstep.dnepr.repository;

import org.itstep.dnepr.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJPA extends JpaRepository<User, Long> {

    public User getById(Long id);

    public User getByEmail(String email);


}
