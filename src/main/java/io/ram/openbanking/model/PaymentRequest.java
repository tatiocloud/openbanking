package io.ram.openbanking.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

//@JsonIgnoreProperties({"cvv"})
public class PaymentRequest implements Serializable {

    @JsonProperty("username")
    private String username;

    @JsonProperty("number")
    private Long number;

    @JsonProperty("cvv")
    private Integer cvv;

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("balance")
    private Double balance;


    public PaymentRequest(){
        //default for Json constructor

    }

    public PaymentRequest(String username, Long number, Integer cvv, Double amount, Double balance) {
        this.username = username;
        this.number = number;
        this.cvv = cvv;
        this.amount = amount;
        this.balance = balance;
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

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
