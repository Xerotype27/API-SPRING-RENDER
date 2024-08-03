package com.api.test.demo_psql.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.test.demo_psql.model.dto.ClientDto;
import com.api.test.demo_psql.model.entity.Client;
import com.api.test.demo_psql.model.payload.ResponseMessage;
import com.api.test.demo_psql.service.IClientService;

@RestController
@RequestMapping("/api/v1")
public class ClientController {

    @Autowired
    private IClientService clientService;

    @PostMapping("client")
    public ResponseEntity<?> create(@RequestBody ClientDto clientDto) {
        Client clientSave = null;
        try {
            clientSave = clientService.save(clientDto);
            clientDto = ClientDto.builder()
                    .clientId(clientSave.getClientId())
                    .name(clientSave.getName())
                    .email(clientSave.getEmail())
                    .build();
            return new ResponseEntity<>(ResponseMessage.builder()
                    .message("Guardado correctamente")
                    .object(clientDto)
                    .build(), HttpStatus.CREATED);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    ResponseMessage.builder()
                            .message(exDt.getMessage())
                            .object(null)
                            .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("client")
    public ResponseEntity<?> update(@RequestBody ClientDto clientDto) {
        Client clientUpdate = null;
        Client clientExist = clientService.findById(clientDto.getClientId());
        if (clientExist == null) {
            return new ResponseEntity<>(ResponseMessage.builder()
                    .message("El registro que se iintenta actualizar no existe en la base de datos")
                    .object(null)
                    .build(),
                    HttpStatus.NOT_FOUND);
        }
        try {

            clientUpdate = clientService.save(clientDto);
            clientDto = ClientDto.builder()
                    .clientId(clientUpdate.getClientId())
                    .name(clientUpdate.getName())
                    .email(clientUpdate.getEmail())
                    .build();
            return new ResponseEntity<>(ResponseMessage.builder()
                    .message("Guardado correctamente")
                    .object(clientDto)
                    .build(), HttpStatus.CREATED);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    ResponseMessage.builder()
                            .message(exDt.getMessage())
                            .object(null)
                            .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("client/{id}")
    // @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable Integer id) {

        try {
            Client clientDelete = clientService.findById(id);
            clientService.delete(clientDelete);
            return new ResponseEntity<>(clientDelete, HttpStatus.NO_CONTENT);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    ResponseMessage.builder()
                            .message(exDt.getMessage())
                            .object(null)
                            .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("client/{id}")
    public ResponseEntity<?> getClientById(@PathVariable Integer id) {
        Client client = clientService.findById(id);

        if (client == null) {
            return new ResponseEntity<>(
                    ResponseMessage.builder()
                            .message("Error buscando cliente")
                            .object(null)
                            .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .message(null)
                        .object(
                                ClientDto.builder()
                                        .clientId(client.getClientId())
                                        .name(client.getName())
                                        .email(client.getEmail())
                                        .build())
                        .build(),
                HttpStatus.OK);
    }
}
