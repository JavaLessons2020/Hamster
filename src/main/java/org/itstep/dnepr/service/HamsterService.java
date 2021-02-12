package org.itstep.dnepr.service;


import org.itstep.dnepr.dao.HibernateHamsterDAO;
import org.itstep.dnepr.model.Hamster;
import org.itstep.dnepr.repository.HamsterJPA;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HamsterService {

    private final HibernateHamsterDAO hamsterDAO;

    private final HamsterJPA jpa;

    public HamsterService(HibernateHamsterDAO hamsterDAO, HamsterJPA jpa) {
        this.hamsterDAO = hamsterDAO;
        this.jpa = jpa;
    }

    public List<Hamster> getAll() {
        //return hamsterDAO.getAll();

        return jpa.findAll();
    }

    public Hamster getByID(Long id) {
        return hamsterDAO.getHamster(id);
    }

    public void save(Hamster hamster) {
        //hamsterDAO.addHamster(hamster);
        jpa.save(hamster);
    }


    public Hamster findByName(String name){
        return jpa.findByName(name);
    }
}
