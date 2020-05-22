package com.example.myapplication;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder> {

    private Context context;
    private List<CurrencyModel.ExchangeRate> list;
    private OnItemClickListener listener;

    public CurrencyAdapter(Context context, List<CurrencyModel.ExchangeRate> list, OnItemClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CurrencyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new CurrencyViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyViewHolder holder, int position) {
        CurrencyModel.ExchangeRate currency = list.get(position);
        holder.bind(currency);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class CurrencyViewHolder extends RecyclerView.ViewHolder {
        OnItemClickListener listener;
        TextView currencyName;
        TextView currencyRateNB;
        View root;

        public CurrencyViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            this.listener = listener;

            root = itemView.findViewById(R.id.root);
            currencyName = itemView.findViewById(R.id.currency_name);
            currencyRateNB = itemView.findViewById(R.id.currency_rateNB);
        }

        public void bind(final CurrencyModel.ExchangeRate currency) {
            currencyName.setText(String.format("Валюта: %s",currency.getCurrency()));
            currencyRateNB.setText(String.format("Курс: %s",currency.getPurchaseRateNB()));
            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(currency);
                }
            });
        }
    }
}