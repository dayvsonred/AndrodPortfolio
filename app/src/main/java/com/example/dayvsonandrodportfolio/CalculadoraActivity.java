package com.example.dayvsonandrodportfolio;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CalculadoraActivity extends AppCompatActivity {

    private EditText editTextNum1;
    private EditText editTextNum2;
    private EditText editTextResult;
    private Button bt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);
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


        editTextNum1 = (EditText) findViewById(R.id.editTextNum1);
        editTextNum2 = (EditText) findViewById(R.id.editTextNum2);
        editTextResult = (EditText) findViewById(R.id.editTextResultado);
        bt = (Button) findViewById(R.id.buttonSoma);

        Toast T =  Toast.makeText(this, "Calculadora Iniciada", Toast.LENGTH_SHORT);
        T.show();

    }

    public void btSoma(View view) {

        int A, B;
        A = Integer.valueOf( editTextNum1.getText().toString());
        B = Integer.valueOf( editTextNum2.getText().toString());

        int soma = A + B;

        editTextResult.setText(  String.valueOf(soma) );

    }

    public void btSub(View view) {
        int A, B;
        A = Integer.valueOf( editTextNum1.getText().toString());
        B = Integer.valueOf( editTextNum2.getText().toString());

        int soma = A - B;

        editTextResult.setText(  String.valueOf(soma) );
    }

    public void btMULT(View view) {
        int A, B;
        A = Integer.valueOf( editTextNum1.getText().toString());
        B = Integer.valueOf( editTextNum2.getText().toString());

        int soma = A * B;

        editTextResult.setText(  String.valueOf(soma) );
    }

    public void btDIV(View view) {
        int A, B;
        A = Integer.valueOf( editTextNum1.getText().toString());
        B = Integer.valueOf( editTextNum2.getText().toString());

        int soma = A / B;

        editTextResult.setText(  String.valueOf(soma) );
    }

    public void btC(View view) {
        editTextNum1.setText(null);
        editTextNum2.setText(null);
        editTextResult.setText(null);
    }
}
