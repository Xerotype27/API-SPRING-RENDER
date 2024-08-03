package com.api.test.demo_psql.service;

import com.api.test.demo_psql.model.dto.ClientDto;
import com.api.test.demo_psql.model.entity.Client;

public interface IClientService {
    Client save(ClientDto client);

    Client findById(Integer id);

    void delete(Client client);

    boolean existsById(Integer id);
}
