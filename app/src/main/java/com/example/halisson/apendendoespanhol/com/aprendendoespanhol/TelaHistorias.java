package com.example.halisson.apendendoespanhol.com.aprendendoespanhol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.halisson.apendendoespanhol.R;

public class TelaHistorias extends AppCompatActivity {

    ImageView imageView, imageView2;
    Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_historias);

        imageView = (ImageView) findViewById(R.id.imgPatinho);
        imageView2 = (ImageView) findViewById(R.id.imgPatinho2);
        btnVoltar = (Button) findViewById(R.id.btnHistoriasVoltar);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irParaNiveis();
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irParaNiveis();
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public void irParaNiveis() {
        Intent intent = new Intent(TelaHistorias.this, PrimeiraTela.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(TelaHistorias.this, TelaJogador.class);
        startActivity(intent);
        finish();
    }
}
