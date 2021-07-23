package com.shaman.transactionpaymentschedule.entity.builders;

import com.shaman.transactionpaymentschedule.entity.Client;
import com.shaman.transactionpaymentschedule.entity.domain.RegisterClientWithPlanAssociated;

public class RegisterClientWithPlanAssociatedBuilder {
    public static Client toEntity(RegisterClientWithPlanAssociated domain) {
        var plan = PlanDetailsBuilder.toEntity(domain.getPlan());
        return Client.builder()
                .name(domain.getName())
                .plan(plan)
                .build();
    }
}
