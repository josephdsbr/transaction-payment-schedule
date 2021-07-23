package com.shaman.transactionpaymentschedule.entity.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
public class ClientInfoWithPlans {
    private UUID uuid;
    private String name;
    private PlanDetails plan;
}
