package com.shaman.transactionpaymentschedule.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Transaction extends DomainBaseEntity<Transaction> {
    private Double amount;
    private Long installment;
    @OneToOne
    private Client client;

    @Override
    public void setUpdatableFields(Transaction entity) {
        this.setAmount(entity.getAmount());
        this.setInstallment(entity.getInstallment());
    }
}
