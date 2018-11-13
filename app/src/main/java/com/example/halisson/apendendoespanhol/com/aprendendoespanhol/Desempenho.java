package com.example.halisson.apendendoespanhol.com.aprendendoespanhol;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.halisson.apendendoespanhol.com.database.BancoJogadorController;
import com.example.halisson.apendendoespanhol.com.database.BancoPerguntas;
import com.example.halisson.apendendoespanhol.com.database.TBNivelController;


public class Desempenho extends AppCompatActivity {

    private Cursor cursor;
    private BancoJogadorController bancoJogadorController;
    private TBNivelController tbNivelController;

    TextView txtPontos, txtNomeJogador, txtInfo;
    ImageView imgEmoji;
    Button btnSair, btnJogarNovamente;
    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desempenho);
        //getSupportActionBar().hide();

        bancoJogadorController = new BancoJogadorController(getBaseContext());
        tbNivelController = new TBNivelController(getBaseContext());

        txtPontos = findViewById(R.id.txtDesempenhoPontos);
        txtPontos.setText(String.valueOf(BancoPerguntas.PONTOS_NIVEL));

        txtInfo = findViewById(R.id.txtDesempenhoInfo);
        txtInfo.setText("Nível " + BancoPerguntas.NIVEL_SELECIONADO);

        // SALVA O DESEMPENHO DO USUÁRIO NO BANCO
        tbNivelController.alterar(Sessao.ID, BancoPerguntas.NIVEL_SELECIONADO, BancoPerguntas.PONTOS_NIVEL);

        // ATUALIZA OS PONTOS DO USUARIO
        int pontuacaoGeral = 0;
        cursor = tbNivelController.buscarPorId(Sessao.ID);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                pontuacaoGeral += cursor.getInt(cursor.getColumnIndex("pontos"));
            } while (cursor.moveToNext());
        }

        imgEmoji = (ImageView) findViewById(R.id.img_emoji);
        txtNomeJogador = (TextView) findViewById(R.id.txt_nome);
        btnSair = (Button) findViewById(R.id.btn_sair);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.win);
        btnJogarNovamente = (Button) findViewById(R.id.btnDesempenhoJogarNovamente);

        if (BancoPerguntas.PONTOS_NIVEL > BancoPerguntas.NUMERO_MIN_ACERTOS) {
            if (Integer.parseInt(BancoPerguntas.NIVEL_SELECIONADO) <= 2) {
                if (Sessao.PROGRESSO == Integer.parseInt(BancoPerguntas.NIVEL_SELECIONADO)
                        && Sessao.PROGRESSO != 3){
                    txtNomeJogador.setText("Parabéns " + Sessao.NOME_JOGADOR + " você liberou o nível "
                            + (Integer.parseInt(BancoPerguntas.NIVEL_SELECIONADO) + 1) + "!");
                } else {
                    txtNomeJogador.setText("Parabéns " + Sessao.NOME_JOGADOR + " você finalizou o nível!\n" +
                            "Que tal ir para o próximo?");
                }
                if (Sessao.PROGRESSO < 3) {
                    Sessao.PROGRESSO += 1;
                }
            } else {
                txtNomeJogador.setText("Parabéns " + Sessao.NOME_JOGADOR + " você concluiu todos os níveis!");
            }
        } else {
            txtNomeJogador.setText("Você ainda não liberou o próximo nível! Tente novamente!");
        }

        bancoJogadorController.alterar(Sessao.ID, pontuacaoGeral, Sessao.PROGRESSO);
        cursor = bancoJogadorController.buscarPorId(Sessao.ID);
        Sessao.PROGRESSO = cursor.getInt(cursor.getColumnIndex("progresso"));

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                finish();
            }
        });

        btnJogarNovamente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BancoPerguntas.PONTOS_NIVEL = 0;
                BancoPerguntas.ERROS_NIVEL = 0;
                BancoPerguntas.INDICE_PERGUNTA = 0;
                Intent intent = new Intent(Desempenho.this, Quiz.class);
                startActivity(intent);
                finish();
            }
        });

//        if (MainActivity.pontuacaoTotal < 10) {
//            imgEmoji.setVisibility(View.INVISIBLE);
//            txtPontuacaoTotal = (TextView) findViewById(R.id.txt_pontuacaoTotal);
//            txtPontuacaoTotal.setText("Pontuação total: " + Integer.toString(MainActivity.pontuacaoTotal));
//
//        } else {
//            mediaPlayer.start();
//        }

    }
}
