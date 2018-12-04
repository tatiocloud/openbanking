package io.ram.openbanking.service;

import io.ram.openbanking.model.User;

public interface UserService {

    User addNewUser(String username, String password);

    User findByUserName(String username);

}
