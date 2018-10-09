package unmsm.dycs.commons.domain.valueobject;

import java.math.BigDecimal;

import unmsm.dycs.commons.domain.enumeration.Currency;

public class Money {

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal amount;
    public Currency currency;

    public Money() {
    }

    public Money(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public static Money dollars(BigDecimal amount) {
        return new Money(amount, Currency.USD);
    }

    public static Money soles(BigDecimal amount) {
        return new Money(amount, Currency.PEN);
    }

    public static Money euros(BigDecimal amount) {
        return new Money(amount, Currency.EUR);
    }

    protected void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    protected void setCurrency(Currency currency) {
        this.currency = currency;
    }

}
