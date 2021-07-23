package com.shaman.transactionpaymentschedule.entity.domain;

import com.shaman.transactionpaymentschedule.message.Messages;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@Validated
public class RegisterClientWithPlanAssociated {
    @NotNull(message = Messages.FIELD_SHOULD_NOT_BE_NULL)
    String name;
    @Valid
    @NotNull(message = Messages.FIELD_SHOULD_NOT_BE_NULL)
    PlanDetails plan;
}
