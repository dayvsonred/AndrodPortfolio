package com.example.dayvsonandrodportfolio;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dayvsonandrodportfolio.models.CepJson;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;

public class ApiRestCepActivity extends AppCompatActivity {

    private EditText txtCepGET;
    private EditText txtCep;
    private EditText txtLograd;
    private EditText txtCompr;
    private EditText txtBairro;
    private EditText txtLocal;
    private EditText txtUF;
    private TextView txtTitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_rest_cep);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        txtCep    = (EditText) findViewById(R.id.Cep);
        txtCepGET = (EditText) findViewById(R.id.CepGet);
        txtBairro = (EditText) findViewById(R.id.Bairro);
        txtCompr  = (EditText) findViewById(R.id.Comp);
        txtLocal  = (EditText) findViewById(R.id.Localidade);
        txtUF     = (EditText) findViewById(R.id.UF);
        txtLograd = (EditText) findViewById(R.id.Lograd);
        txtTitulo = (TextView) findViewById(R.id.TituloBuscaCep);




    }

    public void ExecutarGetCep(View view) {

        String URL  = ViaCepService.BASE_URL_VIA_CEP + txtCepGET.getText().toString() + "/json/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ViaCepService service = retrofit.create(ViaCepService.class);
        Call<CepJson> RequestGetCep = service.GetCep(URL);

        RequestGetCep.enqueue(new Callback<CepJson>() {
            @Override
            public void onResponse(Call<CepJson> call, Response<CepJson> response) {
                if(!response.isSuccessful()){
                    Toast T =  Toast.makeText(ApiRestCepActivity.this, "ERROR API", Toast.LENGTH_SHORT);
                    T.show();
                    txtTitulo.setText("CEP Não encontrado");
                }else {
                    Toast T =  Toast.makeText(ApiRestCepActivity.this, "sucesso", Toast.LENGTH_SHORT);
                    T.show();
                    //CepJson CepRetornado = response.body();

                    txtTitulo.setText("CEP Buscado com sucesso");
                    txtCep.setText(response.body().cep.toString());
                    txtBairro.setText(response.body().bairro.toString());
                    txtCompr.setText(response.body().complemento.toString());
                    txtLocal.setText(response.body().localidade.toString());
                    txtLograd.setText(response.body().logradouro.toString());
                    txtUF.setText(response.body().uf.toString());



                    //Log.i("TAG", CepRetornado.toString());
                }

            }

            @Override
            public void onFailure(Call<CepJson> call, Throwable t) {
                Toast T =  Toast.makeText(ApiRestCepActivity.this, "error", Toast.LENGTH_SHORT);
                T.show();
                txtTitulo.setText("Erro na busca");

            }
        });

    }
}
