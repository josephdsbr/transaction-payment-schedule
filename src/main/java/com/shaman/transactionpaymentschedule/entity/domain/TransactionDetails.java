package com.shaman.transactionpaymentschedule.entity.domain;

import com.shaman.transactionpaymentschedule.message.Messages;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class TransactionDetails {
    @NotNull(message = Messages.FIELD_SHOULD_NOT_BE_NULL)
    @Positive(message = Messages.FIELD_SHOULD_BE_POSITIVE)
    private Double amount;

    @NotNull(message = Messages.FIELD_SHOULD_NOT_BE_NULL)
    private UUID clientId;

    @NotNull(message = Messages.FIELD_SHOULD_NOT_BE_NULL)
    @Min(value = 0, message = Messages.FIELD_MIN_VALUE_CONSTRAINTS)
    @Max(value = 12, message = Messages.FIELD_MAX_VALUE_CONSTRAINTS)
    private Long installments;
}
