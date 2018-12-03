package io.ram.openbanking.service;

import io.ram.openbanking.model.Card;
import io.ram.openbanking.model.User;
import io.ram.openbanking.model.UserBalance;

public interface BalanceService {

    Card addNewCard(Card card);
    User addNewUser(String username, String password);
    UserBalance charge(User user,Card card, Double amountToBeAdded);
    UserBalance credit(User user,Card card, Double amountToBeCredited);

}
