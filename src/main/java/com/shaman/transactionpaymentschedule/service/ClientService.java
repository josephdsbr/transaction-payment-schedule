package com.shaman.transactionpaymentschedule.service;

import com.shaman.transactionpaymentschedule.entity.Client;
import com.shaman.transactionpaymentschedule.entity.builders.ClientInfoWithPlansBuilder;
import com.shaman.transactionpaymentschedule.entity.builders.RegisterClientWithPlanAssociatedBuilder;
import com.shaman.transactionpaymentschedule.entity.domain.ClientInfoWithPlans;
import com.shaman.transactionpaymentschedule.entity.domain.RegisterClientWithPlanAssociated;
import com.shaman.transactionpaymentschedule.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService extends BaseService<Client, ClientRepository> {
    @Override
    protected String getEntityName() {
        return Client.class.getSimpleName();
    }

    @Override
    protected Optional<Client> findEntityByProperty(Client entity) {
        return repository.findByUuidAndRemovedAtIsNull(entity.getUuid());
    }

    public ClientInfoWithPlans handleClientRegisterWithPlans(RegisterClientWithPlanAssociated registerClientWithPlanAssociated) {
        var client = RegisterClientWithPlanAssociatedBuilder.toEntity(registerClientWithPlanAssociated);
        var persistedClient = repository.save(client);
        return ClientInfoWithPlansBuilder.fromEntity(persistedClient);
    }
}
