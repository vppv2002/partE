package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListFragment extends Fragment implements OnItemClickListener {

    private RecyclerView recyclerView;
    private CurrencyAdapter currencyAdapter;
    private String date;

    private List<CurrencyModel.ExchangeRate> rateList;

    public ListFragment(String dateOfRates) {
        date = dateOfRates;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle b) {
        View root = inflater.inflate(R.layout.list_fragment, container, false);
        processingGetResponseByDate(date, root);
        return root;
    }

    private void processingGetResponseByDate(String date, View root) {
        ApiService.getData(date)
                .enqueue(new Callback<CurrencyModel>() {
                    @Override
                    public void onResponse(Call<CurrencyModel> call, Response<CurrencyModel> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            rateList = response.body().getExchangeRate();
                            recyclerView = root.findViewById(R.id.txt_currency_list);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                            currencyAdapter = new CurrencyAdapter(root.getContext(), rateList, ListFragment.this);
                            recyclerView.setAdapter(currencyAdapter);
                        } else {
                            Toast.makeText(root.getContext(), R.string.error, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<CurrencyModel> call, Throwable t) {
                        Toast.makeText(root.getContext(), R.string.error, Toast.LENGTH_SHORT).show();
                    }
                });

    }
    @Override
    public void onItemClick(CurrencyModel.ExchangeRate currency) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.main_container, new CurrencyFragment(currency))
                .addToBackStack(null)
                .commit();
    }
}