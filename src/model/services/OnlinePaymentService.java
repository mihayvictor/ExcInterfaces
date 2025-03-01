package model.services;

public interface OnlinePaymentService {

    public Double peymentFee(Double amount);

    public Double interest(Double amaunt, Integer months);
}
