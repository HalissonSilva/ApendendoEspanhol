package com.example.halisson.apendendoespanhol.com.aprendendoespanhol;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.halisson.apendendoespanhol.R;

public class TelaSplash extends AppCompatActivity {
    private final static int TIME_SPLASH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_splash);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(TelaSplash.this, TelaJogador.class);
                startActivity(intent);
                finish();
            }
        },TIME_SPLASH);
    }
}
