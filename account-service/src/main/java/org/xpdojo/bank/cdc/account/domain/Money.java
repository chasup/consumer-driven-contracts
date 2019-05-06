package org.xpdojo.bank.cdc.account.domain;

import java.util.Objects;

import static java.util.Objects.hash;

public class Money implements Comparable<Money> {
    private final double amount;

    private Money(final double anAmount) {
        this.amount = anAmount;
    }

    public static Money anAmountOf(final double anAmount) {
        return new Money(anAmount);
    }

    public Money add(final Money anAmount) {
        return anAmountOf(this.amount + anAmount.amount);
    }

    public Money less(final Money anAmount) {
        return anAmountOf(this.amount - anAmount.amount);
    }

    public boolean isLessThan(Money anAmount) {
        return amount < anAmount.amount;
    }

    public Money negative() {
        return anAmountOf(amount * -1);
    }

    @Override
    public int compareTo(Money otherAmount) {
        return Double.valueOf(amount).compareTo(Double.valueOf(otherAmount.amount));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Double.compare(money.amount, amount) == 0;
    }

    @Override
    public int hashCode() {
        return hash(amount);
    }

    @Override
    public String toString() {
        return "Money{" +
                "amount=" + amount +
                '}';
    }
}
