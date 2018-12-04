package io.ram.openbanking.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class PaymentResponse implements Serializable {

    @JsonProperty("balance")
    private Double balance;


    public PaymentResponse(){
        //default for Json constructor
    }

    public PaymentResponse(Double balance){
        //default for Json constructor
        this.balance = balance;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
