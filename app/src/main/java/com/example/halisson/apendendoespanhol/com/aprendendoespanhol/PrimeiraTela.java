package com.example.halisson.apendendoespanhol.com.aprendendoespanhol;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.halisson.apendendoespanhol.R;

public class PrimeiraTela extends AppCompatActivity {

    VideoView videoView;
    MediaController mediaController;
    Button btnJogar, btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primeira_tela);

        btnJogar = (Button) findViewById(R.id.btnPrimeiraTelaJogar);
        btnVoltar = (Button) findViewById(R.id.btnPrimeiraTelaVoltar);


        videoView = (VideoView)findViewById(R.id.videoView);
        mediaController = new MediaController(this);

        String videopath = "android.resource://com.example.halisson.apendendoespanhol/"+R.raw.patinho;
        Uri uri = Uri.parse(videopath);
        videoView.setVideoURI(uri);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        videoView.start();

        btnJogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PrimeiraTela.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PrimeiraTela.this, TelaHistorias.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(PrimeiraTela.this, TelaHistorias.class);
        startActivity(intent);
        finish();
    }
}

