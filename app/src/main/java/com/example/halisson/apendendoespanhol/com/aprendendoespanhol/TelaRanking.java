package com.example.halisson.apendendoespanhol.com.aprendendoespanhol;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.halisson.apendendoespanhol.R;
import com.example.halisson.apendendoespanhol.com.database.BancoJogadorController;

public class TelaRanking extends AppCompatActivity {
    private Cursor cursor;
    private BancoJogadorController crud;
    private String codigo;
    private String nome;
    private String pontos;
    private ListView listaRanking;
    private Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_ranking);

        btnVoltar = (Button)findViewById(R.id.btnVoltar);

        BancoJogadorController crud = new BancoJogadorController(getBaseContext());
        final Cursor cursor = crud.buscarTodosRanking();

        String[] nomeCampos = new String[]{"_id", "nome", "pontos"};

        int[] idView = new int[]{R.id.txtRankingPosicao, R.id.txtRankingNome, R.id.txtRankingPontos};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                R.layout.layout_jogador_completo, cursor, nomeCampos, idView);

        listaRanking = (ListView)findViewById(R.id.listaRanking);

        AdapterRankingPersonalizado adapterRankingPersonalizado =
                new AdapterRankingPersonalizado(TelaRanking.this, cursor, 0);

        listaRanking.setAdapter(adapterRankingPersonalizado);

    }

    public void Voltar(View v){
       onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(TelaRanking.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
