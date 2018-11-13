package com.example.halisson.apendendoespanhol.com.aprendendoespanhol;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.halisson.apendendoespanhol.R;
import com.example.halisson.apendendoespanhol.com.database.BancoJogadorController;
import com.example.halisson.apendendoespanhol.com.database.BancoPerguntas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Quiz extends AppCompatActivity {

    private int resposta;
    private boolean eleAcertou, jaRespondeu = false;
    TextView txtPergunta, txtInfo;
    RadioGroup radioRespostas;
    RadioButton radioResposta1, radioResposta2, radioResposta3, radioResposta4;
    ImageView imgPergunta;
    Button btnResponder, btnVoltar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Log.e("nivel", BancoPerguntas.NIVEL_SELECIONADO);
        Log.e("indicePergunta", String.valueOf(BancoPerguntas.INDICE_PERGUNTA));

        imgPergunta = (ImageView) findViewById(R.id.imgPergunta);
        txtPergunta = (TextView) findViewById(R.id.txt_pergunta);
        radioRespostas = (RadioGroup) findViewById(R.id.radio_respostas);
        radioResposta1 = (RadioButton) findViewById(R.id.radio_respostas1);
        radioResposta2 = (RadioButton) findViewById(R.id.radio_respostas2);
        radioResposta3 = (RadioButton) findViewById(R.id.radio_respostas3);
        radioResposta4 = (RadioButton) findViewById(R.id.radio_respostas4);
        btnResponder = (Button) findViewById(R.id.btnResponder);
        btnVoltar = (Button) findViewById(R.id.btnVoltar);

        txtInfo = (TextView) findViewById(R.id.txtInfo);

        btnResponder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton radioButton = (RadioButton) findViewById(radioRespostas.getCheckedRadioButtonId());
                if (radioRespostas.getCheckedRadioButtonId() != -1) {

                    RadioButton rb = (RadioButton) findViewById(radioRespostas.getCheckedRadioButtonId());
                    Intent intent = new Intent(getApplicationContext(), Resposta.class);
                    if (radioRespostas.getCheckedRadioButtonId() == resposta) {
                        eleAcertou = true;
                        intent.putExtra("acertou", eleAcertou);
                        if(!jaRespondeu) {
                            BancoPerguntas.PONTOS_NIVEL++;
                        }
                    } else {
                        eleAcertou = false;
                        intent.putExtra("acertou", eleAcertou);
                        if(!jaRespondeu) {
                            BancoPerguntas.ERROS_NIVEL++;
                        }
                        jaRespondeu = true;
                    }

                    // INCREMENTA O INDICE DA QUESTAO ATUAL
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

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        txtInfo.setText("Nivel " + BancoPerguntas.NIVEL_SELECIONADO + " - Questão " + (BancoPerguntas.INDICE_PERGUNTA + 1));

        carregarNivel(BancoPerguntas.NIVEL_SELECIONADO, BancoPerguntas.INDICE_PERGUNTA);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        // VERIFICA SE O USUARIO ACERTOU A PERGUNTA. SE NÃO, NÃO VAI PARA A PROXIMA PERGUNTA
        if (eleAcertou) {
            BancoPerguntas.INDICE_PERGUNTA++;
            jaRespondeu = false;
        }
        Log.e("indicePergunta", String.valueOf(BancoPerguntas.INDICE_PERGUNTA));
        // VERIFICA SE AS PERGUNTAS DO NÍVEL ACABARAM
        if (BancoPerguntas.INDICE_PERGUNTA == BancoPerguntas.NUMERO_PERGUNTAS) {
            Intent intent = new Intent(Quiz.this, Desempenho.class);
            startActivity(intent);
            finish();
        } else {
            carregarNivel(BancoPerguntas.NIVEL_SELECIONADO, BancoPerguntas.INDICE_PERGUNTA);
        }
        txtInfo.setText("Nivel " + BancoPerguntas.NIVEL_SELECIONADO + " - Questão " + (BancoPerguntas.INDICE_PERGUNTA + 1));

    }

    private void carregarPergunta(Pergunta p) {
        txtPergunta.setText(p.getPergunta());
        imgPergunta.setImageResource(p.getImgPergunta());
        List<String> alternativa = p.getAlternativas();
        radioResposta1.setText(alternativa.get(0));
        radioResposta2.setText(alternativa.get(1));
        radioResposta3.setText(alternativa.get(2));
        radioResposta4.setText(alternativa.get(3));
        resposta = p.getResposta();
        radioRespostas.setSelected(false);
    }

    private void carregarNivel(String nivel, int indicePergunta) {
        switch (nivel) {
            case "1":
                carregarPergunta(BancoPerguntas.NIVEL1.get(indicePergunta));
                break;
            case "2":
                carregarPergunta(BancoPerguntas.NIVEL2.get(indicePergunta));
                break;
            case "3":
                carregarPergunta(BancoPerguntas.NIVEL3.get(indicePergunta));
                break;
        }
    }

}
