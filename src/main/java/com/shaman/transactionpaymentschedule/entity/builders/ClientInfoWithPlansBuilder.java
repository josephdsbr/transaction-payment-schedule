package com.shaman.transactionpaymentschedule.entity.builders;

import com.shaman.transactionpaymentschedule.entity.Client;
import com.shaman.transactionpaymentschedule.entity.domain.ClientInfoWithPlans;

public class ClientInfoWithPlansBuilder {
    public static ClientInfoWithPlans fromEntity(Client entity) {
        var plan = PlanDetailsBuilder.fromEntity(entity.getPlan());
        return ClientInfoWithPlans.builder()
                .uuid(entity.getUuid())
                .name(entity.getName())
                .plan(plan)
                .build();
    }
}
