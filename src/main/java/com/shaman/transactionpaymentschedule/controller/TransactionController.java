package com.shaman.transactionpaymentschedule.controller;

import com.shaman.transactionpaymentschedule.entity.domain.TransactionDetails;
import com.shaman.transactionpaymentschedule.entity.domain.TransactionPaymentSchedule;
import com.shaman.transactionpaymentschedule.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/transactions")
@Validated
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class TransactionController {
    private final TransactionService service;

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public List<TransactionPaymentSchedule> handleTransactionPaymentSchedule(
            @Valid @RequestBody TransactionDetails transactionDetails
            ) {
        return service.handleTransactionPaymentSchedule(transactionDetails);
    }
}
