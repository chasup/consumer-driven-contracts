package org.xpdojo.bank.cdc.account.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.Objects;

public class TransferRequest {

    private final Long fromAccount;
    private final Long toAccount;
    private final Money amount;
    private final LocalDateTime dateTime;
    private final String description;

    public TransferRequest(@JsonProperty("fromAccount") final Long fromAccount,
                           @JsonProperty("toAccount") final Long toAccount,
                           @JsonProperty("amount") final Money amount,
                           @JsonProperty("dateTime") final LocalDateTime dateTime,
                           @JsonProperty("description") final String description) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.dateTime = dateTime;
        this.description = description;
    }

    public Long getFromAccount() {
        return fromAccount;
    }

    public Long getToAccount() {
        return toAccount;
    }

    public Money getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransferRequest that = (TransferRequest) o;
        return Objects.equals(fromAccount, that.fromAccount) &&
                Objects.equals(toAccount, that.toAccount) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(dateTime, that.dateTime) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromAccount, toAccount, amount, dateTime, description);
    }

    @Override
    public String toString() {
        return "TransferRequest{" +
                "fromAccount=" + fromAccount +
                ", toAccount=" + toAccount +
                ", amount=" + amount +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                '}';
    }
}
