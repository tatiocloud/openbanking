package io.ram.openbanking.repository;

import io.ram.openbanking.model.Card;
import io.ram.openbanking.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends CrudRepository<Card,Long> {

    List<Card> findByNumberAndCvv(Integer number, Integer cvv);
}
