package com.shaman.transactionpaymentschedule.service;

import com.shaman.transactionpaymentschedule.entity.Client;
import com.shaman.transactionpaymentschedule.entity.Plan;
import com.shaman.transactionpaymentschedule.entity.Receivable;
import com.shaman.transactionpaymentschedule.entity.Transaction;
import com.shaman.transactionpaymentschedule.entity.builders.TransactionDetailsBuilder;
import com.shaman.transactionpaymentschedule.entity.builders.TransactionPaymentScheduleBuilder;
import com.shaman.transactionpaymentschedule.entity.domain.TransactionDetails;
import com.shaman.transactionpaymentschedule.entity.domain.TransactionPaymentSchedule;
import com.shaman.transactionpaymentschedule.exception.ApplicationException;
import com.shaman.transactionpaymentschedule.message.Messages;
import com.shaman.transactionpaymentschedule.repository.TransactionRepository;
import com.shaman.transactionpaymentschedule.util.PricingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class TransactionService extends BaseService<Transaction, TransactionRepository> {
    @Autowired
    private ClientService clientService;

    @Autowired
    private ReceivableService receivableService;

    @Override
    protected String getEntityName() {
        return Transaction.class.getSimpleName();
    }

    @Override
    protected Optional<Transaction> findEntityByProperty(Transaction entity) {
        return repository.findByUuidAndRemovedAtIsNull(entity.getUuid());
    }

    public Transaction persist(TransactionDetails transactionDetails) {
        var client = this.clientService.findById(transactionDetails.getClientId()).orElseThrow(
                () -> new ApplicationException(
                        String.format(Messages.ENTITY_NOT_FOUND, Client.class)
                ));
        var transaction = TransactionDetailsBuilder.toEntity(transactionDetails, client);
        return this.insert(transaction);
    }

    public List<TransactionPaymentSchedule> handleTransactionPaymentSchedule(TransactionDetails transactionDetails) {
        var transaction = this.persist(transactionDetails);
        var client = transaction.getClient();
        var plan = client.getPlan();
        var totalInstallments = transaction.getInstallment();
        var startAt = totalInstallments == 0 ? 0 : 1;
        var endAt = Math.toIntExact(transactionDetails.getInstallments()) + 1;

        return IntStream.range(startAt, endAt)
                .mapToObj(installment -> this.transactionInstallmentToSchedule(installment, transaction, plan, client))
                .map(receivableService::insert)
                .map(TransactionPaymentScheduleBuilder::fromEntity)
                .collect(Collectors.toList());
    }

    public Receivable transactionInstallmentToSchedule(int installment, Transaction transaction, Plan plan, Client client) {
        var totalInstallment = Math.toIntExact(transaction.getInstallment());
        var fee = plan.getFees().get(totalInstallment).doubleValue();
        var modality = plan.getModalities().get(installment);
        var totalAmount = transaction.getAmount();
        var amount = PricingUtil.defineAmount(totalInstallment, installment, totalAmount).doubleValue();
        var expectedPaymentAt = transaction.getCreatedAt().toLocalDate().plusDays(modality);
        var totalNetAmount = PricingUtil.defineNetAmount(totalAmount, fee).doubleValue();
        var netAmount = PricingUtil.defineAmount(totalInstallment, installment, totalNetAmount).doubleValue();

        return Receivable.builder()
                .amount(amount)
                .installment((long) installment)
                .expectedPaymentDate(expectedPaymentAt)
                .fee(fee)
                .netAmount(netAmount)
                .client(client)
                .transaction(transaction)
                .build();
    }
}
