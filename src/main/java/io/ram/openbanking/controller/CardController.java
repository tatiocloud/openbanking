package io.ram.openbanking.controller;

import io.ram.openbanking.model.Card;
import io.ram.openbanking.model.User;
import io.ram.openbanking.service.CardService;
import io.ram.openbanking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/cards")
public class CardController extends AbstractController{

    @Autowired
    private CardService cardService;

    @PostMapping("/create")
    public @ResponseBody String addNewCard(@RequestBody String cardReq) throws IOException {

        Card card = super.mapFromJson(cardReq, Card.class);
        Card savedCard = cardService.addNewCard(card);
        return super.mapToJson(savedCard);
    }

}
