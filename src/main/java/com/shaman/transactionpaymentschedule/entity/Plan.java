package com.shaman.transactionpaymentschedule.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.List;

@Getter
@Setter
@Entity
@EqualsAndHashCode(callSuper = true)
public class Plan extends DomainBaseEntity<Plan> {
    @Type(type = "list-array")
    @Column(name = "installments", columnDefinition = "smallint[]")
    private List<Long> installments;

    @Type(type = "list-array")
    @Column(name = "modalities", columnDefinition = "smallint[]")
    private List<Long> modalities;

    @Type(type = "list-array")
    @Column(name = "fees", columnDefinition = "numeric[]")
    private List<Double> fees;

    @Override
    public void setUpdatableFields(Plan entity) {
        this.setFees(entity.getFees());
        this.setModalities(entity.getModalities());
        this.setInstallments(entity.getInstallments());
    }
}
