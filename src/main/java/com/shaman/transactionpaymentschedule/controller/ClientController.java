package com.shaman.transactionpaymentschedule.controller;

import com.shaman.transactionpaymentschedule.entity.builders.ClientInfoWithPlansBuilder;
import com.shaman.transactionpaymentschedule.entity.domain.ClientInfoWithPlans;
import com.shaman.transactionpaymentschedule.entity.domain.RegisterClientWithPlanAssociated;
import com.shaman.transactionpaymentschedule.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/clients")
@Validated
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ClientController {
    private final ClientService service;


    @PostMapping("plans")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ClientInfoWithPlans handleClientRegisterWithPlans(
            @Valid @RequestBody RegisterClientWithPlanAssociated registerClientWithPlanAssociated
    ) {
        return service.handleClientRegisterWithPlans(registerClientWithPlanAssociated);
    }

    @GetMapping("/{uuid}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ClientInfoWithPlans findByUUID(
            @PathVariable("uuid") UUID uuid
    ) {
        var entity = service.findByUid(uuid);
        return ClientInfoWithPlansBuilder.fromEntity(entity);
    }
}
