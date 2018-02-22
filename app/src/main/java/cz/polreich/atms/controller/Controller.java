package cz.polreich.atms.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import cz.polreich.atms.AirBankService;
import cz.polreich.atms.model.Branch;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Martin on 22.02.2018.
 */

public class Controller implements Callback<List<Branch>> {

    static final String BASE_URL = "https://api.airbank.cz/";

    public void start(String apikey) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        AirBankService airBankService = retrofit.create(AirBankService.class);

        Call<List<Branch>> call = airBankService.getBranchesList(apikey);
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<List<Branch>> call, Response<List<Branch>> response) {
        if(response.isSuccessful()) {
            List<Branch> branchesList = response.body();
            branchesList.forEach(branch -> System.out.println(branch.getName()));
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<List<Branch>> call, Throwable t) {
        t.printStackTrace();
    }
}
