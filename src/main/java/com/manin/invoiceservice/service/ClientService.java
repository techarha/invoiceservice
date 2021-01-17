package com.manin.invoiceservice.service;

import com.manin.invoiceservice.model.Client;
import com.manin.invoiceservice.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Optional<Client> getClientById(Integer id) {
        return clientRepository.findById(id);
    }
}
