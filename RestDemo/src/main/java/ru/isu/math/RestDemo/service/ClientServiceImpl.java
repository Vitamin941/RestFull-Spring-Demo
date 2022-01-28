package ru.isu.math.RestDemo.service;

import org.springframework.stereotype.Service;
import ru.isu.math.RestDemo.model.Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ClientServiceImpl implements ClientService {

    private static final Map<Integer, Client> CLIENT_MAP = new HashMap<>();
    private static final AtomicInteger CLIENT_ID_HOLDER = new AtomicInteger();


    @Override
    public void create(Client client) {
        int id = CLIENT_ID_HOLDER.incrementAndGet();
        client.setId(id);
        CLIENT_MAP.put(id, client);
    }

    @Override
    public List<Client> readAll() {
        return new ArrayList<>(CLIENT_MAP.values());
    }

    @Override
    public Client read(int id) {
        return CLIENT_MAP.get(id);
    }

    @Override
    public boolean update(Client client, int id) {
        if (CLIENT_MAP.containsKey(id)) {
            client.setId(id);
            CLIENT_MAP.put(id, client);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        if (CLIENT_MAP.containsKey(id)) {
            CLIENT_MAP.remove(id);
            return true;
        }
        return false;
    }
}
