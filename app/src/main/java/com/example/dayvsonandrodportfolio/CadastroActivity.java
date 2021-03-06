package com.example.dayvsonandrodportfolio;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dayvsonandrodportfolio.database.DadosOpenHelper;
import com.example.dayvsonandrodportfolio.dominio.entidade.Cliente;
import com.example.dayvsonandrodportfolio.dominio.repositorio.ClienteRepositorio;

import java.util.regex.Pattern;

public class CadastroActivity extends AppCompatActivity {

    private EditText edtNome;
    private EditText edtTel;
    private EditText edtEnder;
    private EditText edtEmail;

    private SQLiteDatabase conexao;
    private DadosOpenHelper dadosOpenHelper;

    private CoordinatorLayout layoutContatinCadastro;

    private ClienteRepositorio clienteRepositorio;

    private Cliente cliente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
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

        edtNome = (EditText) findViewById(R.id.edt_nome) ;
        edtTel = (EditText) findViewById(R.id.edt_tel) ;
        edtEmail = (EditText) findViewById(R.id.edt_email) ;
        edtEnder = (EditText) findViewById(R.id.edt_enderec) ;

        layoutContatinCadastro = (CoordinatorLayout) findViewById(R.id.layoutContatinCadastro);

        CreateConexao();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_confirm_cad, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int ID = item.getItemId();

        switch (ID){
             case R.id.action_ok:
                Toast.makeText(this, "Cadastrando Cliente", Toast.LENGTH_SHORT).show();
                 confimar();
             break;

            case R.id.action_cancel:
                Toast.makeText(this, "Cancelar cadastro", Toast.LENGTH_SHORT).show();
                finish();
                break;

        }

        return super.onOptionsItemSelected(item);
    }



    private boolean vaild_camps(){
        String nome = edtNome.getText().toString();
        String tel = edtTel.getText().toString();
        String ender = edtEnder.getText().toString();
        String email = edtEmail.getText().toString();
        boolean res = false;

        cliente.nome = nome;
        cliente.telefone = tel;
        cliente.email = email;
        cliente.endereco = ender;



        if(res = (isCampoVasio(nome)) ){
            edtNome.requestFocus();
        }else
            if(res = (isCampoVasio(tel)) ){
            edtTel.requestFocus();
            }else
                if(res = (isCampoVasio(ender)) ){
                edtEnder.requestFocus();
                }
                else
                    if(res = (!isEmailValido(email)) ){
                    edtEmail.requestFocus();
                    }

        if(res){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Aviso");
            dlg.setMessage("Preencha corretamente os campos");
            dlg.setNeutralButton("ok",null);
            dlg.show();

        }/*else{
            Toast.makeText(this, "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
            edtEnder.setText(null);
            edtNome.setText(null);
            edtEmail.setText(null);
            edtTel.setText(null);
            edtNome.requestFocus();
        }*/

        return res;
    }

    private boolean isCampoVasio(String valor){
        boolean Result = ( TextUtils.isEmpty(valor) || valor.trim().isEmpty() );
        return Result;
    }

    private boolean isEmailValido(String email){
        boolean Result = (!isCampoVasio(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        return Result;
    }


    private void confimar(){

        cliente = new Cliente();

        if(vaild_camps() == false){
            try {
                clienteRepositorio.inserir(cliente);
                Toast.makeText(this, "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                edtEnder.setText(null);
                edtNome.setText(null);
                edtEmail.setText(null);
                edtTel.setText(null);
                edtNome.requestFocus();
                finish();
            }
            catch (SQLException ex){
                AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                dlg.setTitle("Erro");
                dlg.setMessage(ex.getMessage());
                dlg.setNeutralButton("ok", null);
                dlg.show();


            }

        }

    }


    private void CreateConexao(){

        try {
            dadosOpenHelper = new DadosOpenHelper(this);
            conexao = dadosOpenHelper.getWritableDatabase();
            Snackbar.make(layoutContatinCadastro, "Conexão criada com sucesso",Snackbar.LENGTH_SHORT)
                    .setAction("ok", null).show();

            clienteRepositorio = new ClienteRepositorio(conexao);

        }catch (SQLException ex){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Erro");
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton("ok", null);
            dlg.show();


        }

    }




}
