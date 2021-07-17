package com.shaman.transactionpaymentschedule.service;

import com.shaman.transactionpaymentschedule.entity.Client;
import com.shaman.transactionpaymentschedule.repository.ClientRepository;

import java.util.Optional;

public class ClientService extends BaseService<Client, ClientRepository> {
    @Override
    protected String getEntityName() {
        return Client.class.getSimpleName();
    }

    @Override
    protected Optional<Client> findEntityByProperty(Client entity) {
        return repository.findByUuidAndRemovedAtIsNull(entity.getUuid());
    }
}
