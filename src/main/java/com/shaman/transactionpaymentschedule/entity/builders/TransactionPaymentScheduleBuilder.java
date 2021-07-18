package com.shaman.transactionpaymentschedule.entity.builders;

import com.shaman.transactionpaymentschedule.entity.Receivable;
import com.shaman.transactionpaymentschedule.entity.domain.TransactionPaymentSchedule;

public class TransactionPaymentScheduleBuilder {
    public static TransactionPaymentSchedule fromEntity(Receivable entity) {
        var client = entity.getClient();
        var transaction = entity.getTransaction();
        return TransactionPaymentSchedule.builder()
                .client(client.getName())
                .netAmount(entity.getNetAmount())
                .fee(entity.getFee())
                .expectedPaymentDate(entity.getExpectedPaymentDate())
                .currentInstallment(entity.getInstallment())
                .installments(transaction.getInstallment())
                .amount(entity.getAmount())
                .build();
    }
}
