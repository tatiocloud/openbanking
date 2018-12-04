package io.ram.openbanking.controller;

import io.ram.openbanking.json.AbstractJsonBuilder;
import io.ram.openbanking.model.*;
import io.ram.openbanking.service.BalanceService;
import io.ram.openbanking.service.CardService;
import io.ram.openbanking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/payment")
public class PaymentController extends AbstractJsonBuilder {

    @Autowired
    BalanceService balanceService;

    @Autowired
    UserService userService;

    @Autowired
    CardService cardService;

    @PostMapping("/charge")
    public @ResponseBody PaymentResponse charge(@RequestBody String paymentRequest) throws IOException {

        PaymentRequest payReq = super.mapFromJson(paymentRequest, PaymentRequest.class);
        User queriedUserByName = userService.findByUserName(payReq.getUsername());
        Card cardByNumberAndCvv = cardService.findCardByNumberAndCvv(payReq.getNumber(), payReq.getCvv());
        UserBalance charge = balanceService.charge(queriedUserByName, cardByNumberAndCvv, payReq.getAmount());
        return super.mapFromJson(Double.toString(charge.getBalance()), PaymentResponse.class);
    }

    @PostMapping("/credit")
    public @ResponseBody PaymentResponse credit(@RequestBody String paymentRequest) throws IOException {

        PaymentRequest payReq = super.mapFromJson(paymentRequest, PaymentRequest.class);
        User user = userService.findByUserName(payReq.getUsername());
        Card cardByNumberAndCvv = cardService.findCardByNumberAndCvv(payReq.getNumber(), payReq.getCvv());
        UserBalance credit = balanceService.credit(user, cardByNumberAndCvv, payReq.getAmount());
        return super.mapFromJson(Double.toString(credit.getBalance()), PaymentResponse.class);
    }

}
