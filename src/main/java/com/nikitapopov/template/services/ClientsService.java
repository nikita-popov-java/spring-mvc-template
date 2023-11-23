package com.nikitapopov.template.services;

import com.nikitapopov.template.models.Client;
import com.nikitapopov.template.repositories.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ClientsService {

    private final ClientsRepository clientsRepository;

    @Autowired
    public ClientsService(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    public List<Client> findAll() {
        return clientsRepository.findAll();
    }

    public Client find(int id) {
        return clientsRepository.findById(id).orElse(null);
    }

    public Optional<Client> find(String email) {
        return clientsRepository.find(email);
    }

    @Transactional
    public void save(Client client) {
        clientsRepository.save(client);
    }

    @Transactional
    public void update(int id, Client client) {
        client.setId(id);
        clientsRepository.save(client);
    }

    @Transactional
    public void delete(int id) {
        clientsRepository.deleteById(id);
    }
}