package com.example.demo.bank.helpers;

import com.example.demo.bank.model.models.Rate;

import java.util.ArrayList;

public class FindRates {
    private static FindRates INSTANCE;

    private static ArrayList<Rate> rates;

    private FindRates(ArrayList<Rate> rates) {
        this.rates = rates;
    }

    private FindRates() {
        rates = new ArrayList<>();
    }

    public static FindRates getInstance(ArrayList<Rate> newRates) {
        rates = newRates;
        if (INSTANCE == null)
            INSTANCE = new FindRates(rates);

        return INSTANCE;
    }

    public static FindRates getInstance() {
        if (INSTANCE == null)
            INSTANCE = new FindRates();

        return INSTANCE;
    }

    public double findRate(String from, ArrayList<String> testedCurrencies, double calculatedRate) {
        double rateValue = 0;
        for (Rate rate : rates) {
            if (rate.getFrom().equalsIgnoreCase(from) && rate.getTo().equalsIgnoreCase("EUR")) {
                rateValue += rate.getRate() * calculatedRate;
                break;
            } else if (rate.getFrom().equalsIgnoreCase(from)) {
                if (!testedCurrencies.contains(rate.getTo())) {
                    testedCurrencies.add(rate.getTo());
                    rateValue += findRate(rate.getTo(), testedCurrencies, calculatedRate * rate.getRate());
                    if (rateValue != 0) {
                        break;
                    }
                }
            }
        }

        return rateValue;
    }
}
