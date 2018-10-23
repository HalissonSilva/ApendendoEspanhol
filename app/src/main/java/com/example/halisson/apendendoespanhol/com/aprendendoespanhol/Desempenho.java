package com.example.halisson.apendendoespanhol.com.aprendendoespanhol;

import android.database.Cursor;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.halisson.apendendoespanhol.R;
import com.example.halisson.apendendoespanhol.com.database.BancoJogadorController;


public class Desempenho extends AppCompatActivity {

    private Cursor cursor;
    private BancoJogadorController crud;
    private String codigo;
    private String nivel;

    TextView txtPontuacaoTotal, txtNomeJogador, txtNivelJogador;
    ImageView imgEmoji;
    Button btnSair;
    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desempenho);
        //getSupportActionBar().hide();

        codigo = this.getIntent().getStringExtra("codigo");

        crud = new BancoJogadorController(getBaseContext());

        imgEmoji = (ImageView) findViewById(R.id.img_emoji);
        txtNomeJogador = (TextView)findViewById(R.id.txt_nome);
        btnSair = (Button) findViewById(R.id.btn_sair);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.win);

        cursor = crud.buscarPorId(Integer.parseInt(codigo));

        txtNomeJogador.setText(cursor.getString(cursor.getColumnIndexOrThrow("nome")));
        txtNivelJogador.setText(cursor.getString(cursor.getColumnIndexOrThrow("nivel")));




        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                finish();
            }
        });

        if (MainActivity.pontuacaoTotal < 10) {
            imgEmoji.setVisibility(View.INVISIBLE);
            txtPontuacaoTotal = (TextView) findViewById(R.id.txt_pontuacaoTotal);
            txtPontuacaoTotal.setText("Pontuação total: " + Integer.toString(MainActivity.pontuacaoTotal));

        } else {
            mediaPlayer.start();
        }

        crud.alterar(Integer.parseInt(codigo), txtNomeJogador.getText().toString(),txtPontuacaoTotal.getText().toString(), txtNivelJogador.getText().toString());

    }
}
