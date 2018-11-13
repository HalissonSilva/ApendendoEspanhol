package com.example.halisson.apendendoespanhol.com.aprendendoespanhol;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.halisson.apendendoespanhol.R;
import com.example.halisson.apendendoespanhol.com.database.BancoPerguntas;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static Button btnRanking, btnNivel1, btnNivel2, btnNivel3;
    Button btnSobre, btnSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSobre = (Button) findViewById(R.id.btnAjuda);
        btnRanking = (Button) findViewById(R.id.btnRanking);
        btnNivel1 = (Button) findViewById(R.id.btnNivel1);
        btnNivel2 = (Button) findViewById(R.id.btnNivel2);
        btnNivel3 = (Button) findViewById(R.id.btnNivel3);
        btnSair = (Button) findViewById(R.id.btnSair);

        habilitarBotoesNivel();


        btnNivel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Quiz.class);
                BancoPerguntas.NIVEL_SELECIONADO = "1";
                BancoPerguntas.INDICE_PERGUNTA = 0;
                BancoPerguntas.NUMERO_PERGUNTAS = BancoPerguntas.NIVEL1.size();
                BancoPerguntas.PONTOS_NIVEL = 0;
                BancoPerguntas.ERROS_NIVEL = 0;
                startActivity(intent);
            }
        });

        btnNivel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Quiz.class);
                BancoPerguntas.NIVEL_SELECIONADO = "2";
                BancoPerguntas.INDICE_PERGUNTA = 0;
                BancoPerguntas.NUMERO_PERGUNTAS = BancoPerguntas.NIVEL2.size();
                BancoPerguntas.PONTOS_NIVEL = 0;
                BancoPerguntas.ERROS_NIVEL = 0;
                startActivity(intent);
            }
        });

        btnNivel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Quiz.class);
                BancoPerguntas.NIVEL_SELECIONADO = "3";
                BancoPerguntas.INDICE_PERGUNTA = 0;
                BancoPerguntas.NUMERO_PERGUNTAS = BancoPerguntas.NIVEL3.size();
                BancoPerguntas.PONTOS_NIVEL = 0;
                BancoPerguntas.ERROS_NIVEL = 0;
                startActivity(intent);
            }
        });

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TelaRanking.class);
                startActivity(intent);
                finish();
            }
        });

        btnSobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TelaCreditos.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void habilitarBotoesNivel() {
        Log.e("Progresso", String.valueOf(Sessao.PROGRESSO));
        switch (Sessao.PROGRESSO) {
            case 2:
                btnNivel2.setEnabled(true);
                btnNivel2.setBackground(getBaseContext().getResources().getDrawable(R.drawable.buttonshape));
                break;
            case 3:
                btnNivel2.setEnabled(true);
                btnNivel2.setBackground(getBaseContext().getResources().getDrawable(R.drawable.buttonshape));
                btnNivel3.setEnabled(true);
                btnNivel3.setBackground(getBaseContext().getResources().getDrawable(R.drawable.buttonshape));
                break;
        }

        if (!btnNivel2.isEnabled()) {
            btnNivel2.setBackground(getBaseContext().getResources().getDrawable(R.drawable.buttondisableshape));
        }

        if (!btnNivel3.isEnabled()) {
            btnNivel3.setBackground(getBaseContext().getResources().getDrawable(R.drawable.buttondisableshape));
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        habilitarBotoesNivel();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(MainActivity.this, TelaHistorias.class);
        startActivity(intent);
        finish();
    }
}
