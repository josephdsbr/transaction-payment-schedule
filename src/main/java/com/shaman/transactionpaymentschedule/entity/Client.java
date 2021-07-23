package com.shaman.transactionpaymentschedule.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Entity
public class Client extends DomainBaseEntity<Client> {
    private String name;
    @OneToOne(cascade= CascadeType.ALL)
    private Plan plan;

    @Override
    public void setUpdatableFields(Client entity) {
        this.setPlan(entity.getPlan());
        this.setName(entity.getName());
    }
}
