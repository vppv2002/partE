package com.example.myapplication;

import java.util.List;

public class CurrencyModel {

    private String date;
    private String bank;
    private Integer baseCurrency;
    private String baseCurrencyLit;
    private List<ExchangeRate> exchangeRate;

    public List<ExchangeRate> getExchangeRate() {
        return exchangeRate;
    }

    public class ExchangeRate {

        private String baseCurrency;
        private String currency;
        private Double saleRateNB;
        private Double purchaseRateNB;
        private Double saleRate;
        private Double purchaseRate;

        public String getCurrency() {
            return currency;
        }

        public Double getSaleRateNB() {
            return saleRateNB;
        }

        public Double getPurchaseRateNB() {
            return purchaseRateNB;
        }

        public Double getSaleRate() {
            return saleRate;
        }

        public Double getPurchaseRate() {
            return purchaseRate;
        }
    }
}