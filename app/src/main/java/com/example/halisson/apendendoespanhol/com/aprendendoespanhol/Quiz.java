package com.example.halisson.apendendoespanhol.com.aprendendoespanhol;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.halisson.apendendoespanhol.R;
import com.example.halisson.apendendoespanhol.com.database.BancoJogadorController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Quiz extends AppCompatActivity {

    private Cursor cursor;
    private BancoJogadorController crud;
    private String codigo, nivel;



    static int pontos, erros, resposta, objetivo;
    TextView txtPergunta,txtNome, txtPontos;
    RadioGroup radioRespostas;
    RadioButton radioResposta1, radioResposta2, radioResposta3, radioResposta4;
    ImageView imgPergunta;
    Button btnResponder;
    static List<Pergunta> perguntas;


    private void carregarPergunta() {
        while (perguntas.size() > 0) {
            Pergunta p = perguntas.remove(0);
            txtPergunta.setText(p.getPergunta());
            imgPergunta.setImageResource(p.getImgPergunta());
            List<String> alternativa = p.getAlternativas();
            radioResposta1.setText(alternativa.get(0));
            radioResposta2.setText(alternativa.get(1));
            radioResposta3.setText(alternativa.get(2));
            radioResposta4.setText(alternativa.get(3));
            resposta = p.getResposta();
            radioRespostas.setSelected(false);

            finish();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        codigo = this.getIntent().getStringExtra("codigo");
        crud = new BancoJogadorController(getBaseContext());

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        pontos = 0;
        erros = 0;
        resposta = R.id.radio_respostas1;

        imgPergunta = (ImageView) findViewById(R.id.imgPergunta);
        txtPergunta = (TextView) findViewById(R.id.txt_pergunta);
        radioRespostas = (RadioGroup) findViewById(R.id.radio_respostas);
        radioResposta1 = (RadioButton) findViewById(R.id.radio_respostas1);
        radioResposta2 = (RadioButton) findViewById(R.id.radio_respostas2);
        radioResposta3 = (RadioButton) findViewById(R.id.radio_respostas3);
        radioResposta4 = (RadioButton) findViewById(R.id.radio_respostas4);
        btnResponder = (Button) findViewById(R.id.btnResponder);

        txtNome = (TextView)findViewById(R.id.txt_nome);
        txtPontos = (TextView)findViewById(R.id.txt_pontos);


        //txtNome.setText(cursor.getString(cursor.getColumnIndexOrThrow("nome")));
        //txtPontos.setText("Pontuação total: " + Integer.toString(MainActivity.pontuacaoTotal));

        btnResponder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton radioButton = (RadioButton) findViewById(radioRespostas.getCheckedRadioButtonId());
                if (radioRespostas.getCheckedRadioButtonId() != -1) {

                    RadioButton rb = (RadioButton) findViewById(radioRespostas.getCheckedRadioButtonId());
                    Intent intent = new Intent(getApplicationContext(), Resposta.class);
                    if (radioRespostas.getCheckedRadioButtonId() == resposta) {
                        intent.putExtra("acertou", true);
                        pontos++;
                    } else
                        intent.putExtra("acertou", false);
                    intent.putExtra("pontos", pontos);
                    startActivity(intent);

                    radioRespostas.clearCheck();
                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(Quiz.this).create();
                    alertDialog.setTitle("Ooops!");
                    alertDialog.setMessage("Você precisa selecionar uma alternativa :)");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
            }

        });
        carregarPergunta();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
            if (pontos == 2) {
                Toast.makeText(getApplicationContext(), "Nível 2 liberado!", Toast.LENGTH_LONG).show();
                MainActivity.btnNivel2.setVisibility(View.VISIBLE);
                nivel = "2";
            }else if(pontos == 4){
                Toast.makeText(getApplicationContext(), "Nível 3 liberado!", Toast.LENGTH_LONG).show();
                MainActivity.btnNivel3.setVisibility(View.VISIBLE);
                nivel = "3";
            }

        carregarPergunta();
    }

}
