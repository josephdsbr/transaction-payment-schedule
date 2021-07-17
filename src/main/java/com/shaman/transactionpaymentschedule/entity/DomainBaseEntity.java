package com.shaman.transactionpaymentschedule.entity;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.TypeDef;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
@TypeDef(
        name = "list-array",
        typeClass = ListArrayType.class
)
public abstract class DomainBaseEntity<T> extends BaseEntity {
    public abstract void setUpdatableFields(T entity);
}
