package com.example.halisson.apendendoespanhol.com.aprendendoespanhol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.halisson.apendendoespanhol.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    static Button btnRanking, btnNivel1, btnNivel2, btnNivel3;
    Button btnSobre, btnSair;
    static int pontuacaoTotal;
    static boolean parada;

    List<Pergunta> perguntasFaceis = new ArrayList<Pergunta>(){
        {
            add(new Pergunta(R.drawable.quantosovos, "Quantos ovos pôs a mamãe pato?", R.id.radio_respostas3, "A)" +"cinco", "B) seis", "C) siete", "D) ocho"));
            add(new Pergunta(R.drawable.cisne, "O patinho feio era na verdade um?", R.id.radio_respostas4, "A) un pato", "B) una gallina", "C) un ganso", "D) un cisne"));
            add(new Pergunta(R.drawable.cordospatinhos, "Qual era a cor dos patinhos irmãos do patinho feio?", R.id.radio_respostas1, "A) amarillo", "B) blanca", "C) rojo", "D) grises"));
        }
    };

    List<Pergunta> perguntasMedianas = new ArrayList<Pergunta>(){
        {
            add(new Pergunta(R.drawable.cordopatinhofeio, "Qual era a cor do patinho feio assim que ele nasceu?", R.id.radio_respostas3, "A) amarillho", "B) blanco", "C) gris y blanco", "D) gris"));
            add(new Pergunta(R.drawable.procuramae, "O Patinho Feio ... quando sua mãe é levada?", R.id.radio_respostas2, "A) canta", "B) la busca", "C) llora", "D) se queda feliz"));
            add(new Pergunta(R.drawable.diferencaentreospatos, "Qual eram as diferenças entre o patinho feio e seus irmãos?", R.id.radio_respostas3, "A) solamente la color ", "B) solamente el tamaño", "C) el color y el tamaño", "D) ninguno"));
        }
    };

    List<Pergunta> perguntasDificeis = new ArrayList<Pergunta>(){
        {
            add(new Pergunta(R.drawable.riem, "... animais da granja riem do Patinho Feio", R.id.radio_respostas4, "A)ellos ", "B) las", "C) ellas", "D) los"));
            add(new Pergunta(R.drawable.animaisdagranja, "Com qual desses animais o Patinho Feio não falou quando procurava suas mãe?", R.id.radio_respostas1, "A) un cerdo", "B) un perro", "C) una gallina", "D) una vaca"));
            add(new Pergunta(R.drawable.mameindoembora, "O que separou a mamãe pato de seus filhotes?", R.id.radio_respostas2, "A)los hermanos de Patito Feo ", "B) los cazadores", "C) la gallina", "D) la vaca"));
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.verificaNivel();

        btnRanking =(Button)findViewById(R.id.btnRanking);
        btnNivel1 = (Button)findViewById(R.id.btnNivel1);
        btnNivel2 = (Button)findViewById(R.id.btnNivel2);
        btnNivel3 = (Button)findViewById(R.id.btnNivel3);
        btnSair = (Button)findViewById(R.id.btnSair);

        btnNivel2.setVisibility(View.INVISIBLE);
        btnNivel3.setVisibility(View.INVISIBLE);


        btnNivel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quiz.perguntas = new ArrayList<Pergunta>(perguntasFaceis);
                Intent intent = new Intent(getApplicationContext(), Quiz.class);
                startActivity(intent);
            }
        });

        btnNivel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quiz.perguntas = new ArrayList<Pergunta>(perguntasMedianas);
                Intent intent = new Intent(getApplicationContext(), Quiz.class);
                startActivity(intent);
            }
        });

        btnNivel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quiz.perguntas = new ArrayList<Pergunta>(perguntasDificeis);
                Intent intent = new Intent(getApplicationContext(), Quiz.class);
                startActivity(intent);
            }
        });

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void verificaNivel(){
        switch (Sessao.NIVEL){
            case "1":
                btnNivel2.setEnabled(false);
                btnNivel3.setEnabled(false);
                break;
            case "2":
                btnNivel3.setEnabled(false);
                break;
            default:
                break;
        }
    }

    public void Ranking(View v) {
        Intent intent = new Intent(MainActivity.this, TelaRanking.class);
        startActivity(intent);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Quiz.pontos = 0;
        if (parada) {
            Intent intent = new Intent(getApplicationContext(), Desempenho.class);
            startActivity(intent);
        }
    }



}
