package io.ram.openbanking.model;

import javax.persistence.*;

@Entity
@Table(name = "balances")
public class UserBalance {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "balance")
    private Double balance = 0.0;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;

    public UserBalance(){
        //hibernate
    }

    public UserBalance(User user, Card card) {
        this.user = user;
        this.card = card;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void transactCharge(Double amount) {
        this.balance += amount;
    }

    public void transactCredit(Double amount) {
        this.balance -= amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    @Override public String toString() {
        return "UserBalance{" +
               "id=" + id +
               ", balance=" + balance +
               ", user=" + user +
               ", card=" + card +
               '}';
    }
}
