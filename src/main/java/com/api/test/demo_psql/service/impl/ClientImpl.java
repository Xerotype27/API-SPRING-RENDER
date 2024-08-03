package com.api.test.demo_psql.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.test.demo_psql.model.dao.ClientDao;
import com.api.test.demo_psql.model.dto.ClientDto;
import com.api.test.demo_psql.model.entity.Client;
import com.api.test.demo_psql.service.IClientService;

@Service
public class ClientImpl implements IClientService {

    @Autowired
    private ClientDao clientDao;

    @Transactional
    @Override
    public Client save(ClientDto clientDto) {
        // TODO Auto-generated method stub
        Client client = Client.builder()
                .clientId(clientDto.getClientId())
                .name(clientDto.getName())
                .email(clientDto.getEmail())
                .build();
        return clientDao.save(client);

    }

    @Transactional(readOnly = true)
    @Override
    public Client findById(Integer id) {
        // TODO Auto-generated method stub
        return clientDao.findById(id).orElse(null);

    }

    @Transactional
    @Override
    public void delete(Client client) {

        clientDao.delete(client);
    }

    public boolean existsById(Integer id) {
        return clientDao.existsById(id);
    }
}
