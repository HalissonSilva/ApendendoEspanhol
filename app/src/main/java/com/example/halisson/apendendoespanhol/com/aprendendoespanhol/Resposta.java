package com.example.halisson.apendendoespanhol.com.aprendendoespanhol;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.halisson.apendendoespanhol.R;
import com.example.halisson.apendendoespanhol.com.database.BancoJogadorController;

public class Resposta extends AppCompatActivity {

    ImageView imgResposta;
    TextView txtResposta;
    MediaPlayer efeitoSonoro;
    private BancoJogadorController crud;
    private String codigo;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resposta);


        //final EditText nome = (EditText) findViewById(R.id.edtNome);
        //final EditText ponto = (EditText)findViewById(R.id.edtPonto);

        codigo = this.getIntent().getStringExtra("codigo");
        crud = new BancoJogadorController(getBaseContext());


        BancoJogadorController crud = new BancoJogadorController(getBaseContext());

        imgResposta = (ImageView) findViewById(R.id.img_resposta);
        txtResposta = (TextView) findViewById(R.id.txt_resposta);

        Intent intent = getIntent();
        boolean resposta = intent.getBooleanExtra("acertou", false);

        if(resposta){
            imgResposta.setImageResource(R.drawable.corect);
            txtResposta.setText("Acertou");
            Quiz.pontos++;
            MainActivity.pontuacaoTotal++;
            efeitoSonoro = MediaPlayer.create(getApplicationContext(), R.raw.correct);
            efeitoSonoro.start();
        }else{
            imgResposta.setImageResource(R.drawable.incorrect);
            txtResposta.setText("Errou!");
            Quiz.erros++;
            efeitoSonoro = MediaPlayer.create(getApplicationContext(), R.raw.wrong);
            efeitoSonoro.start();
        }

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                efeitoSonoro.stop();
                efeitoSonoro.release();
                finish();
            }
        });

        thread.start();
    }
}
