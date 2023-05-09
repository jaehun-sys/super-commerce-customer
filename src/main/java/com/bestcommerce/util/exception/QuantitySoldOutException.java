package com.bestcommerce.util.exception;

public class QuantitySoldOutException extends QuantityException{

    private final static String errorMessage = "품절 된 상품입니다.";

    public QuantitySoldOutException(){}

    @Override
    public String getMessage(){
        return errorMessage;
    }
}
