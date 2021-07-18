package com.shaman.transactionpaymentschedule.service;

import com.shaman.transactionpaymentschedule.entity.Receivable;
import com.shaman.transactionpaymentschedule.repository.ReceivableRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReceivableService extends BaseService<Receivable, ReceivableRepository> {
    @Override
    protected String getEntityName() {
        return Receivable.class.getSimpleName();
    }

    @Override
    protected Optional<Receivable> findEntityByProperty(Receivable entity) {
        return repository.findByUuidAndRemovedAtIsNull(entity.getUuid());
    }
}
