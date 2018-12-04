package io.ram.openbanking.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "card")
public class Card implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "card_number")
    @JsonProperty("number")
    private Long number;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "card_cvv")
    @JsonProperty("cvv")
    private Integer cvv;

    public Card(){
        //hibernate
    }
    public Card(Long number, Integer cvv,User user) {
        this.number = number;
        this.cvv = cvv;
        this.user = user;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Card))
            return false;
        Card card = (Card) o;
        return Objects.equals(getId(), card.getId()) &&
               Objects.equals(getNumber(), card.getNumber()) &&
               Objects.equals(getCvv(), card.getCvv());
    }

    @Override public int hashCode() {
        return Objects.hash(getId(), getNumber(), getCvv());
    }

    @Override public String toString() {
        return "Card{" +
               "id=" + id +
               ", number=" + number +
               ", user=" + user +
               ", cvv=" + cvv +
               '}';
    }
}
