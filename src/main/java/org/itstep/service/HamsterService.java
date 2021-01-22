package org.itstep.service;


import org.itstep.dao.HibernateHamsterDAO;
import org.itstep.model.Hamster;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HamsterService {

    private final HibernateHamsterDAO hamsterDAO;

    public HamsterService(HibernateHamsterDAO hamsterDAO) {
        this.hamsterDAO = hamsterDAO;
    }

    public List<Hamster> getAll() {
        return hamsterDAO.getAll();
    }

    public Hamster getByID(Long id) {
        return hamsterDAO.getHamster(id);
    }

    public void save(Hamster hamster) {
        hamsterDAO.addHamster(hamster);
    }
}
