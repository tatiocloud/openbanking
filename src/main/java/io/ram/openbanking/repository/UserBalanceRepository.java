package io.ram.openbanking.repository;

import io.ram.openbanking.model.Card;
import io.ram.openbanking.model.User;
import io.ram.openbanking.model.UserBalance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBalanceRepository extends CrudRepository<UserBalance,Long> {

    UserBalance findByUserAndCard(User user, Card card);
}
