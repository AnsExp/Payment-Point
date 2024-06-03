package com.paymentpoint.data.utils;

import com.paymentpoint.data.access.ISource;
import com.paymentpoint.data.access.source.BalanceSource;
import com.paymentpoint.data.access.source.ContractSource;
import com.paymentpoint.data.access.source.PaymentSource;
import com.paymentpoint.data.access.source.PlanSource;
import com.paymentpoint.data.entities.Contract;
import com.paymentpoint.data.entities.Payment;
import com.paymentpoint.data.entities.Plan;
import com.paymentpoint.data.entities.State;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

public final class ContractUtils {

    @org.jetbrains.annotations.Contract(pure = true)
    private ContractUtils() {
    }

    public static void createPayments(@NotNull Contract contract) {

        var sourcePlan = new PlanSource();
        var sourcePayment = new PaymentSource();
        Plan plan = sourcePlan.get(contract.getIdPlan());

        Date firstMon = DateUtils.getFirstMondayFromDate(contract.getDateStart());

        for (int i = 0; i < plan.getSteps(); i++) {
            Payment payment = new Payment();
            payment.setAmountMandatory(plan.getPayment());
            payment.setIdContract(contract.getIdContract());
            payment.setDateMandatory(firstMon);
            payment.setState(State.PENDING);
            sourcePayment.save(payment);
            firstMon = DateUtils.getFirstMondayFromDate(firstMon);
        }
    }

    @org.jetbrains.annotations.Contract("null -> fail")
    public static void removePayments(Contract contract) {
        if (contract == null)
            throw new NullPointerException();
        ISource<Payment> repository = new PaymentSource();
        repository.removeBy(pay -> pay.getIdContract() == contract.getIdContract());
    }

    public static void addAmountContract(int idContract, float amount) {
        ISource<Contract> finder = new ContractSource();
        Contract contract = finder.get(idContract);
        if (contract == null) return;
        addAmountContract(contract, amount);
    }

    @org.jetbrains.annotations.Contract("null, _ -> fail")
    public static void addAmountContract(Contract contract, float amount) {

        if (contract == null)
            throw new NullPointerException();

        contract.setAmountPaid(contract.getAmountPaid() + amount);
        ISource<Contract> source = new ContractSource();
        source.save(contract);
        BalanceSource.debitAmount(contract.getIdClient(), amount);
    }
}
