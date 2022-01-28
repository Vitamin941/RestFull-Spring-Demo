package ru.isu.math.RestDemo.service;

import ru.isu.math.RestDemo.model.Client;

import java.util.List;

public interface ClientService {

    void create(Client client);

    List<Client> readAll();

    Client read(int id);

    boolean update(Client client, int id);

    boolean delete(int id);
}
