package com.nikitapopov.template.dao;

import com.nikitapopov.template.models.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
public class ClientDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public ClientDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Client> index() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("FROM Client", Client.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public Optional<Client> show(String email) {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("FROM Client WHERE email=:email", Client.class)
                .setParameter("email", email)
                .getResultList()
                .stream().findAny();
    }

    @Transactional(readOnly = true)
    public Client show(int id) {
        Session session = sessionFactory.getCurrentSession();

        return session.get(Client.class, id);
    }

    @Transactional
    public void save(Client client) {
        Session session = sessionFactory.getCurrentSession();

        session.persist(client);
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();

        Client clientToRemove = session.get(Client.class, id);
        session.remove(clientToRemove);
    }

    @Transactional
    public void update(int id, Client client) {
        Session session = sessionFactory.getCurrentSession();

        Client clientToUpdate = session.get(Client.class, id);

        clientToUpdate.setFullName(client.getFullName());
        clientToUpdate.setEmail(client.getEmail());
        clientToUpdate.setVisitingDate(client.getVisitingDate());
        clientToUpdate.setDentistFullName(client.getDentistFullName());
        clientToUpdate.setPhone(client.getPhone());
    }
}