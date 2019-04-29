package com.example.dayvsonandrodportfolio;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private Button ButtonOpenCalculadora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        ButtonOpenCalculadora = findViewById(R.id.buttonOpenCalculardora);


    }


    public void OpenCalculadora(View view) {
        Intent It = new Intent(MainActivity.this , CalculadoraActivity.class);
        startActivity(It);
    }

    public void OpenRestApiCep(View view) {
        Intent It = new Intent(MainActivity.this , ApiRestCepActivity.class);
        startActivity(It);

    }

    public void OpenCadasto(View view) {

        Intent It = new Intent(MainActivity.this , CadastroActivity.class);
        startActivity(It);
    }
}
