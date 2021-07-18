package com.shaman.transactionpaymentschedule.service;

import com.shaman.transactionpaymentschedule.entity.Plan;
import com.shaman.transactionpaymentschedule.repository.PlanRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlanService extends BaseService<Plan, PlanRepository> {
    @Override
    protected String getEntityName() {
        return Plan.class.getSimpleName();
    }

    @Override
    protected Optional<Plan> findEntityByProperty(Plan entity) {
        return repository.findByUuidAndRemovedAtIsNull(entity.getUuid());
    }
}
