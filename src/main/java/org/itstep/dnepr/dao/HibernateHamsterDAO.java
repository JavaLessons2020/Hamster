package org.itstep.dnepr.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.itstep.dnepr.model.Hamster;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HibernateHamsterDAO {

    private final SessionFactory sessionFactory;

    public HibernateHamsterDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public Session currentSession() {
        return sessionFactory.openSession();
    }


    public void addHamster(Hamster hamster) {
        Transaction transaction = currentSession().beginTransaction();
        currentSession().save(hamster);
        currentSession().close();
        transaction.commit();
    }

    public Hamster getHamster(Long id){
        Hamster hamster = currentSession().get(Hamster.class, id);
        return hamster;
    }


    public List<Hamster> getAll()
    {
        return currentSession().createQuery("SELECT h From Hamster h", Hamster.class).list();
    }
}

