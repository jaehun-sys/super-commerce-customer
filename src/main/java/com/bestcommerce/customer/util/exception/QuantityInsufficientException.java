package com.bestcommerce.customer.util.exception;

public class QuantityInsufficientException extends QuantityException{

    private final static String errorMessage = "주문 하신 수량이 남은 재고 수량을 초과합니다.";

    public QuantityInsufficientException(){

    }

    @Override
    public String getMessage(){
        return errorMessage;
    }
}
