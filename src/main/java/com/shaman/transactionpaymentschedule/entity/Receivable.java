package com.shaman.transactionpaymentschedule.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Receivable extends DomainBaseEntity<Receivable> {
    private Double fee;
    private Long installment;
    private Double amount;
    private Double netAmount;
    private LocalDate expectedPaymentDate;

    @OneToOne
    private Client client;
    @OneToOne
    private Transaction transaction;

    @Override
    public void setUpdatableFields(Receivable entity) {
        this.setFee(entity.getFee());
        this.setInstallment(entity.getInstallment());
        this.setAmount(entity.getAmount());
        this.setNetAmount(entity.getNetAmount());
        this.setExpectedPaymentDate(entity.getExpectedPaymentDate());
    }
}
