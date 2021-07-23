package com.shaman.transactionpaymentschedule.entity;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TypeDef(
        name = "list-array",
        typeClass = ListArrayType.class
)
public class Plan extends DomainBaseEntity<Plan> {
    @Type(type = "list-array")
    @Column(name = "installments", columnDefinition = "integer[]")
    private List<Integer> installments;

    @Type(type = "list-array")
    @Column(name = "modalities", columnDefinition = "integer[]")
    private List<Integer> modalities;

    @Type(type = "list-array")
    @Column(name = "fees", columnDefinition = "numeric[]")
    private List<BigDecimal> fees;

    @Override
    public void setUpdatableFields(Plan entity) {
        this.setFees(entity.getFees());
        this.setModalities(entity.getModalities());
        this.setInstallments(entity.getInstallments());
    }
}
