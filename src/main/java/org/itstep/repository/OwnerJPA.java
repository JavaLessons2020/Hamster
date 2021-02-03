package org.itstep.repository;

import org.itstep.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OwnerJPA extends JpaRepository<Owner, Long> {

    public Owner getOwnerByName(String name);
}
