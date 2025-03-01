package model.services;

public class PaypalService implements OnlinePaymentService {

    @Override
    public Double peymentFee(Double amount) {
       return amount * 0.02;
    }

    @Override
    public Double interest(Double amaunt, Integer months) {
       return (amaunt *= 0.01) * months;
    }

}
