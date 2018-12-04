package io.ram.openbanking.service;

import io.ram.openbanking.model.Card;
import io.ram.openbanking.model.User;
import io.ram.openbanking.model.UserBalance;

public interface CardService {

    Card addNewCard(Card card);

    Card findCardByNumberAndCvv(Long number, Integer cvv);

}
