package org.itstep.dnepr.repository;

import org.itstep.dnepr.model.Hamster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HamsterJPA extends JpaRepository<Hamster, Long> {

    Hamster findByName(String name);

}
