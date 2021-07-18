package com.shaman.transactionpaymentschedule.entity.builders;

import com.shaman.transactionpaymentschedule.entity.Client;
import com.shaman.transactionpaymentschedule.entity.Receivable;
import com.shaman.transactionpaymentschedule.entity.Transaction;
import com.shaman.transactionpaymentschedule.entity.domain.TransactionDetails;

public class TransactionDetailsBuilder {
    public static Transaction toEntity(TransactionDetails domain, Client client) {
        return Transaction.builder()
                .amount(domain.getAmount())
                .installment(domain.getInstallments())
                .client(client)
                .build();
    }

    public static TransactionDetails fromEntity(Receivable entity) {
        return TransactionDetails.builder().build();
    }
}
