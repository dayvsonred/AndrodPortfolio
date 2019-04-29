package com.example.dayvsonandrodportfolio;

import com.example.dayvsonandrodportfolio.models.CepJson;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;


public interface ViaCepService {

    public static final String BASE_URL_VIA_CEP = "https://viacep.com.br/ws/";

    @GET
    Call<CepJson> GetCep(@Url String url);


    //@GET("{fullUrl}")
    //Call<Users> getUsers(@Path("fullUrl") fullUrl);


}
