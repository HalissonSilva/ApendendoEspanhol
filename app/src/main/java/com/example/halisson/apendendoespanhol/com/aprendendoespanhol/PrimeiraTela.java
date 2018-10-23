package com.example.halisson.apendendoespanhol.com.aprendendoespanhol;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.halisson.apendendoespanhol.R;

public class PrimeiraTela extends AppCompatActivity {

    VideoView videoView;
    MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primeira_tela);


        videoView = (VideoView)findViewById(R.id.videoView);
        mediaController = new MediaController(this);

        String videopath = "android.resource://com.example.halisson.apendendoespanhol/"+R.raw.patinho;
        Uri uri = Uri.parse(videopath);
        videoView.setVideoURI(uri);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        videoView.start();

    }

    public void bntIniciarJogo(View view){
        Intent intent = new Intent(PrimeiraTela.this, MainActivity.class);
        startActivity(intent);
        finish();

    }

}

