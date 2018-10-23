package com.example.halisson.apendendoespanhol.com.aprendendoespanhol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.halisson.apendendoespanhol.R;

public class TelaHistorias extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_historias);
        imageView = (ImageView)findViewById(R.id.imgPatinho);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TelaHistorias.this, PrimeiraTela.class);
                startActivity(intent);
                finish();

            }
        });


    }
}
