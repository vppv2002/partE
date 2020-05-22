package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class CurrencyFragment extends Fragment {

    private String currencyName;
    private Double saleRateNB;
    private Double purchaseRateNB;
    private Double saleRate;
    private Double purchaseRate;

    public CurrencyFragment(CurrencyModel.ExchangeRate currency) {
        currencyName = currency.getCurrency();
        saleRateNB = currency.getSaleRateNB();
        purchaseRateNB = currency.getPurchaseRateNB();
        saleRate = currency.getSaleRate();
        purchaseRate = currency.getPurchaseRate();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle b) {
        View v = inflater.inflate(R.layout.currency_fragment, container, false);
        TextView viewCurrencyName = v.findViewById(R.id.fragment_currency_name);
        TextView viewSaleRateNB = v.findViewById(R.id.fragment_currency_sale_rateNB);
        TextView viewPurchaseRateNB = v.findViewById(R.id.fragment_currency_purchase_RateNB);
        TextView viewSaleRate = v.findViewById(R.id.fragment_currency_sale_Rate);
        TextView viewPurchaseRate = v.findViewById(R.id.fragment_currency_purchase_Rate);

        viewCurrencyName.setText(String.format("Валюта: %s", currencyName));
        viewSaleRateNB.setText(String.format("Курс продажи НБ: %s", saleRateNB));
        viewPurchaseRateNB.setText(String.format("Курс покупки НБ: %s", purchaseRateNB));
        viewSaleRate.setText(String.format("Курс продажи: %s", saleRate));
        viewPurchaseRate.setText(String.format("Курс покупки: %s", purchaseRate));

        return v;
    }
}