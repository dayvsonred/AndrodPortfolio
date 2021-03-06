package com.example.dayvsonandrodportfolio;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.dayvsonandrodportfolio.database.DadosOpenHelper;

public class MainActivity extends AppCompatActivity {


    private Button ButtonOpenCalculadora;
    private SQLiteDatabase conexao;
    private DadosOpenHelper dadosOpenHelper;

    private ConstraintLayout layoutContatinMain;

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
        layoutContatinMain = (ConstraintLayout) findViewById(R.id.main_content_actv);

        CreateConexao();

    }

    private void CreateConexao(){

        try {
            dadosOpenHelper = new DadosOpenHelper(this);
            conexao = dadosOpenHelper.getWritableDatabase();
            Snackbar.make(layoutContatinMain, "Conexão criada com sucesso",Snackbar.LENGTH_SHORT)
                    .setAction("ok", null).show();

        }catch (SQLException ex){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Erro");
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton("ok", null);
            dlg.show();


        }

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

    public void OpenListCliente(View view) {

        Intent It = new Intent(MainActivity.this , ListClientesActivity.class);
        startActivity(It);
    }
}
