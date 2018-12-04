package io.ram.openbanking.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.ram.openbanking.json.AbstractJsonBuilder;

import java.io.Serializable;

public class PaymentRequest extends AbstractJsonBuilder{

    @JsonProperty("username")
    private String username;

    @JsonProperty("number")
    private Long number;

    @JsonProperty("cvv")
    private Integer cvv;

    @JsonProperty("amount")
    private Double amount;

    public PaymentRequest(){
        //default for Json constructor

    }

    public PaymentRequest(String username, Long number, Integer cvv, Double amount) {
        this.username = username;
        this.number = number;
        this.cvv = cvv;
        this.amount = amount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getNumber() {
        return number;
    }


    public void setNumber(Long number) {
        this.number = number;
    }

    @JsonIgnore
    public Integer getCvv() {
        return cvv;
    }


    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override public String toString() {
        return "PaymentRequest{" +
               "username='" + username + '\'' +
               ", number=" + number +
               ", cvv=" + cvv +
               ", amount=" + amount +
               '}';
    }
}
