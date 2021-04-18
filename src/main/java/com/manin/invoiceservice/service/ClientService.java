package com.manin.invoiceservice.service;

import com.manin.invoiceservice.exceptions.DummyException;
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

    public Optional<Client> getClientById(String id) {
        Optional<Client> client = clientRepository.findById(id);
        if (!client.isPresent()) {
            throw new DummyException("INV-1", "Could not find Client");
        }
        return clientRepository.findById(id);
    }
}
