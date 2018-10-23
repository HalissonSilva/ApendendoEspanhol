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
        final Cursor cursor = crud.buscarTodos();

        String[] nomeCampos = new String[]{"_id", "nome", "pontos","nivel"};

        int[] idView = new int[]{R.id.idJogador, R.id.nomeJogador, R.id.pontosJogador, R.id.nivelJogador};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                R.layout.layout_jogador_completo, cursor, nomeCampos, idView);

        listaRanking = (ListView)findViewById(R.id.listaRanking);
        listaRanking.setAdapter(adaptador);

    }

    public void Voltar(View v){
        Intent intent = new Intent(TelaRanking.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
