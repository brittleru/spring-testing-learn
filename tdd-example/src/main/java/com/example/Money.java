package com.example;

public class Money implements Expression {

    protected final int amount;
    protected final String currency;


    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public static Money dollar(int amount) {
        return new Money(amount, "USD");
    }

    public static Money franc(int amount) {
        return new Money(amount, "CHF");
    }

    protected String currency() {
        return currency;
    }

    @Override
    public Expression times(int multiplier) {
        return new Money(amount * multiplier, this.currency);
    }

    @Override
    public Expression plus(Expression addend) {
//        return new Money(amount + addend.amount, currency);
        return new Sum(this, addend);
    }

    @Override
    public Money reduce(Bank bank, String to) {
//        return this;
//        int rate = (currency.equals("CHF") && to.equals("USD")) ? 2 : 1;
        return new Money(amount / bank.rate(this.currency, to), to);
    }


    public boolean equals(Object object) {
        Money money = (Money) object;
        return amount == money.amount && this.currency == money.currency;
    }


    @Override
    public String toString() {
        return "Money{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }


}
