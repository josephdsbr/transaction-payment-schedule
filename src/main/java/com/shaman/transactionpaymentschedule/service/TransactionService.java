package com.shaman.transactionpaymentschedule.service;

import com.shaman.transactionpaymentschedule.entity.Transaction;
import com.shaman.transactionpaymentschedule.repository.TransactionRepository;

import java.util.Optional;

public class TransactionService extends BaseService<Transaction, TransactionRepository> {
    @Override
    protected String getEntityName() {
        return Transaction.class.getSimpleName();
    }

    @Override
    protected Optional<Transaction> findEntityByProperty(Transaction entity) {
        return repository.findByUuidAndRemovedAtIsNull(entity.getUuid());
    }
}
