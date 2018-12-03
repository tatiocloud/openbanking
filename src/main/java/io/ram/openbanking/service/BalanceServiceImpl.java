package io.ram.openbanking.service;

import io.ram.openbanking.model.Card;
import io.ram.openbanking.model.User;
import io.ram.openbanking.model.UserBalance;
import io.ram.openbanking.repository.CardRepository;
import io.ram.openbanking.repository.UserBalanceRepository;
import io.ram.openbanking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

@Service
public class BalanceServiceImpl implements BalanceService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    UserBalanceRepository userBalanceRepository;

    @Override
    public User addNewUser(String username, String password) {
        Date now = Date.from(Instant.now());
        Timestamp timestamp = Timestamp.from(Instant.now());
        User user = new User(username, password, now, timestamp);
        return userRepository.save(user);
    }

    @Override
    public Card addNewCard(Card card) {
        Card savedCard = cardRepository.save(card);
        return savedCard;
    }

    @Override
    public UserBalance charge(User user, Card card, Double amountToBeAdded) {
        UserBalance userBalance = userBalanceRepository.findByUserAndCard(user, card);
        userBalance.transactCharge(amountToBeAdded);
        return userBalanceRepository.save(userBalance);
    }

    @Override public UserBalance credit(User user, Card card, Double amountToBeCredited) {
        return null;
    }
}
