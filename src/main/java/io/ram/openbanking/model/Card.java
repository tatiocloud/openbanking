package io.ram.openbanking.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "card")
public class Card implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "card_number")
    private Long number;

    @Column(name = "card_cvv")
    private Integer cvv;

    public Card(Long number, Integer cvv) {
        this.number = number;
        this.cvv = cvv;
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
               ", cvv=" + cvv +
               '}';
    }
}
