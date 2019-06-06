package org.xpdojo.bank.cdc.mobile.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransferRequest {

    private Long fromAccount;
    private Long toAccount;
    private Money amount;
    private String description;

    public TransferRequest(@JsonProperty("fromAccount") Long fromAccount,
                           @JsonProperty("toAccount") Long toAccount,
                           @JsonProperty("amount") Money amount,
                           @JsonProperty("description") String description) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransferRequest that = (TransferRequest) o;
        return Objects.equals(fromAccount, that.fromAccount) &&
                Objects.equals(toAccount, that.toAccount) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromAccount, toAccount, amount, description);
    }

    @Override
    public String toString() {
        return "TransferRequest{" +
                "fromAccount=" + fromAccount +
                ", toAccount=" + toAccount +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }
}