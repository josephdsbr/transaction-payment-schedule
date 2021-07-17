package com.shaman.transactionpaymentschedule.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
@EqualsAndHashCode(callSuper = true)
public class Transaction extends DomainBaseEntity<Transaction> {
    private Long amount;
    private Long installment;
    @OneToOne
    private Client client;

    @Override
    public void setUpdatableFields(Transaction entity) {
        this.setAmount(entity.getAmount());
        this.setInstallment(entity.getInstallment());
    }
}
