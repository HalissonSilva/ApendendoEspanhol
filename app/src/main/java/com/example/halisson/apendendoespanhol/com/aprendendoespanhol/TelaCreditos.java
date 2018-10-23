package com.example.halisson.apendendoespanhol.com.aprendendoespanhol;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.halisson.apendendoespanhol.R;

public class TelaCreditos extends AppCompatActivity {
    private final static int TIME_SPLASH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_creditos);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        },TIME_SPLASH);
    }
}
