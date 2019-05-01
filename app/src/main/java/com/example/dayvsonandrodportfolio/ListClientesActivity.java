package com.example.dayvsonandrodportfolio;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.example.dayvsonandrodportfolio.database.DadosOpenHelper;
import com.example.dayvsonandrodportfolio.dominio.entidade.Cliente;
import com.example.dayvsonandrodportfolio.dominio.repositorio.ClienteRepositorio;

import java.util.List;

public class ListClientesActivity extends AppCompatActivity {

    private SQLiteDatabase conexao;
    private DadosOpenHelper dadosOpenHelper;
    private LinearLayout layoutContatinListCliet;
    private RecyclerView lstDados;
    private ClienteAdapter clienteAdapter;
    private ClienteRepositorio clienteRepositorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_clientes);
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



        lstDados = (RecyclerView)findViewById(R.id.lstDados);

        lstDados.setHasFixedSize(true);

        //layoutContatinListCliet = (LinearLayout) findViewById(R.id.layoutListClient);
        layoutContatinListCliet = (LinearLayout) findViewById(R.id.layoutContatinListCliet);

        CreateConexao();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        lstDados.setLayoutManager(linearLayoutManager);

        clienteRepositorio = new ClienteRepositorio(conexao);

        List<Cliente> dados = clienteRepositorio.BuscarTodos();

        clienteAdapter = new ClienteAdapter(dados);

        lstDados.setAdapter(clienteAdapter);


    }



    private void CreateConexao(){

        try {
            dadosOpenHelper = new DadosOpenHelper(this);
            conexao = dadosOpenHelper.getWritableDatabase();
            //Snackbar.make(layoutContatinListCliet, "Conexão criada com sucesso",Snackbar.LENGTH_SHORT)
             //       .setAction("ok", null).show();

        }catch (SQLException ex){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Erro");
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton("ok", null);
            dlg.show();


        }

    }

}
