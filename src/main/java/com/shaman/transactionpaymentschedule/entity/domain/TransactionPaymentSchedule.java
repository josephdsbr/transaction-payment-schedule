package com.shaman.transactionpaymentschedule.entity.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class TransactionPaymentSchedule {
    private String client;
    private Double fee;
    private Long currentInstallment;
    private Long installments;
    private Double amount;
    private Double netAmount;
    private LocalDate expectedPaymentDate;
}
