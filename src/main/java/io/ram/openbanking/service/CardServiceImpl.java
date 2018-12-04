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
import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CardRepository cardRepository;


    @Override
    public Card addNewCard(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public Card findCardByNumberAndCvv(Long number, Integer cvv) {
        List<Card> cards = cardRepository.findByNumberAndCvv(number, cvv);
        if(!cards.isEmpty()){
            return cards.get(0);
        }
        return null;
    }

}
