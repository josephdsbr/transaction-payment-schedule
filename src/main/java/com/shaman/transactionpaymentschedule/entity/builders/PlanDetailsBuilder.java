package com.shaman.transactionpaymentschedule.entity.builders;

import com.shaman.transactionpaymentschedule.entity.Plan;
import com.shaman.transactionpaymentschedule.entity.domain.PlanDetails;

public class PlanDetailsBuilder {
    public static Plan toEntity(PlanDetails domain) {
        return Plan.builder()
                .fees(domain.getFees())
                .installments(domain.getInstallments())
                .modalities(domain.getModalities())
                .build();
    }

    public static PlanDetails fromEntity(Plan entity) {
        return PlanDetails.builder()
                .fees(entity.getFees())
                .installments(entity.getInstallments())
                .modalities(entity.getModalities())
                .build();
    }
}
