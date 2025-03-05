package model.services;

import java.time.LocalDate;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {

    private OnlinePaymentService onlinePaymentService;


    public ContractService(OnlinePaymentService onlinePaymentService) {
        this.onlinePaymentService = onlinePaymentService;
    }

    
    public void processContract(Contract contract, Integer months){
        double basicQuota = contract.getTotalvalue()/months;
       for (int i = 1; i <= months; i++) {
        LocalDate duedaDate = contract.getDate().plusMonths(i);
        double interest = onlinePaymentService.interest(basicQuota, i);
        double fee = onlinePaymentService.peymentFee(basicQuota + interest);
        double quota = basicQuota + interest + fee;
        contract.getInstallments().add(new Installment(duedaDate, quota));

       }
    }

}
