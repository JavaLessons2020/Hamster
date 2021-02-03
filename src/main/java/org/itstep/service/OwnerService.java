package org.itstep.service;

import org.itstep.model.Hamster;
import org.itstep.model.Owner;
import org.itstep.repository.OwnerJPA;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService  {



    private final OwnerJPA jpa;

    public OwnerService(OwnerJPA jpa) {
        this.jpa = jpa;
    }

    public List<Owner> getAll() {
        return jpa.findAll();
    }

    public Owner getByID(Long id) {
        return jpa.findById(id).orElse(new Owner());
    }

    public void save(Owner owner) {
        jpa.save(owner);
    }
    public Owner geyByName(String name){
        return jpa.getOwnerByName(name);
    }



//    public Hamster findByName(String name){
//        return jpa.findByName(name);
//    }
}
