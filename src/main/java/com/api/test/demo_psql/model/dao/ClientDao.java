package com.api.test.demo_psql.model.dao;

import com.api.test.demo_psql.model.entity.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientDao extends CrudRepository<Client, Integer> {

}
