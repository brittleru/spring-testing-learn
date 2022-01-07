package com.example;

import java.util.HashMap;

public class Bank {

    private final HashMap<Pair, Integer> rateMap = new HashMap<>();

    Money reduce(Expression source, String toCurrency) {
        return source.reduce(this, toCurrency);
//            if (source instanceof Money) {
//                return (Money) source;
//            }
//            Sum sum = (Sum) source;
//            return sum.reduce(toCurrency);
    }

    public int rate(String from, String to) {

        if (from.equals(to)) {
            return 1;
        }

        return rateMap.get(new Pair(from, to));
//        return (from.equals("CHF") && to.equals("USD")) ? 2 : 1;
    }

    public void addRate(String from, String to, int rate) {
        rateMap.put(new Pair(from, to), rate);
    }
}
