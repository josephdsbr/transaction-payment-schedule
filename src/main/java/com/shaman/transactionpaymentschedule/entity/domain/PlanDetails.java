package com.shaman.transactionpaymentschedule.entity.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shaman.transactionpaymentschedule.message.Messages;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
public class PlanDetails {
    @NotEmpty(message = Messages.FIELD_SHOULD_NOT_BE_EMPTY)
    private List<Integer> installments;
    @NotEmpty(message = Messages.FIELD_SHOULD_NOT_BE_EMPTY)
    private List<Integer> modalities;
    @NotEmpty(message = Messages.FIELD_SHOULD_NOT_BE_EMPTY)
    private List<BigDecimal> fees;

    @JsonIgnore
    public boolean isValid() {
        return installments.size() == modalities.size() && installments.size() == fees.size();
    }
}
