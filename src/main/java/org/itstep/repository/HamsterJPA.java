package org.itstep.repository;

import org.itstep.model.Hamster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HamsterJPA extends JpaRepository<Hamster, Long> {

    Hamster findByName(String name);

}
