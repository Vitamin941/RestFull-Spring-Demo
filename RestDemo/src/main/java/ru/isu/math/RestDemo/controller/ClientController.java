package ru.isu.math.RestDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.isu.math.RestDemo.service.ClientService;
import ru.isu.math.RestDemo.model.Client;

import java.util.List;

@RestController
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping(value = "/clients")
    public ResponseEntity<?> create(@RequestBody Client client) {
        clientService.create(client);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/clients")
    public ResponseEntity<List<Client>> read() {
        List<Client> clientList = clientService.readAll();

        if (clientList != null && !clientList.isEmpty()) {
            return new ResponseEntity<>(clientList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping(value = "/clients/{id}")
    public ResponseEntity<Client> read(@PathVariable(name = "id") Integer id) {
        Client client = clientService.read(id);

        if (client != null) {
            return new ResponseEntity<>(client, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/client/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Integer id,
                                         @RequestBody Client client) {
        if (clientService.update(client, id))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/client/{id}")
    public ResponseEntity<?> delete (@PathVariable(name = "id") Integer id) {
        if (clientService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
