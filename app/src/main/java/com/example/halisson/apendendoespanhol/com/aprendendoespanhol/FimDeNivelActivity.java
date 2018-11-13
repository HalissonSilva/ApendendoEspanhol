package com.example.halisson.apendendoespanhol.com.aprendendoespanhol;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.halisson.apendendoespanhol.R;
import com.example.halisson.apendendoespanhol.com.database.BancoJogador;
import com.example.halisson.apendendoespanhol.com.database.BancoJogadorController;
import com.example.halisson.apendendoespanhol.com.database.BancoPerguntas;
import com.example.halisson.apendendoespanhol.com.database.TBNivelController;

public class FimDeNivelActivity extends AppCompatActivity {

    private Cursor cursor;
    private BancoJogadorController bancoJogadorController;
    private TBNivelController tbNivelController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fim_de_nivel);

        bancoJogadorController = new BancoJogadorController(getBaseContext());
        tbNivelController = new TBNivelController(getBaseContext());

        // SALVA O DESEMPENHO DO USUÁRIO NO BANCO
        tbNivelController.alterar(Sessao.ID, BancoPerguntas.NIVEL_SELECIONADO, BancoPerguntas.PONTOS_NIVEL);

        // ATUALIZA OS PONTOS DO USUARIO
        int pontuacaoGeral = 0;
        cursor = tbNivelController.buscarPorId(Sessao.ID);
        if(cursor != null && cursor.moveToFirst()) {
            do {
                pontuacaoGeral += cursor.getInt(cursor.getColumnIndex("pontos"));
            } while (cursor.moveToNext());
        }

        bancoJogadorController.alterar(Sessao.ID, pontuacaoGeral, Sessao.PROGRESSO);
    }
}
