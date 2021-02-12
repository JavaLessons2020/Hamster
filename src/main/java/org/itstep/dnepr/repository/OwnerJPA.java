package org.itstep.dnepr.repository;

import org.itstep.dnepr.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OwnerJPA extends JpaRepository<Owner, Long> {


    Owner getOwnerByName(String name);
}
