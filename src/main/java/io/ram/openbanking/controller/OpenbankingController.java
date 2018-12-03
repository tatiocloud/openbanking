package io.ram.openbanking.controller;

import com.google.gson.Gson;
import io.ram.openbanking.model.User;
import io.ram.openbanking.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class OpenbankingController {

    @Autowired
    private Gson gson;

    @Autowired
    private BalanceService balanceService;

    @RequestMapping("/user/newuser")
    public User addNewUser(@RequestBody String userReq){

        User user = gson.fromJson(userReq, User.class);
        return null;
    }

}
