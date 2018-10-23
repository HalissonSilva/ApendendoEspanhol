package com.example.halisson.apendendoespanhol.com.aprendendoespanhol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.halisson.apendendoespanhol.R;
import com.example.halisson.apendendoespanhol.com.database.BancoJogadorController;

public class NovoJogador extends AppCompatActivity {

    EditText edtNome;
    Button btnCadastrar, btnCancelar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_jogador);

        edtNome = (EditText)findViewById(R.id.edtNome);
        btnCadastrar = (Button)findViewById(R.id.btnCadastrar);
        btnCancelar = (Button)findViewById(R.id.btnCancelar);


        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BancoJogadorController crud = new BancoJogadorController(getBaseContext());
                edtNome = (EditText)findViewById(R.id.edtNome);
                String nomeStr = edtNome.getText().toString();
                String pontosStr = "0";
                String nivelStr = "1";
                String msg;


                if (!nomeStr.equals("")){
                    msg = crud.inserir(nomeStr, pontosStr, nivelStr);
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(NovoJogador.this, TelaJogador.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),
                            R.string.msg_camposobrigatorios,Toast.LENGTH_LONG).show();
                }
            }
        });

    }



    public void Cancelar(View v){
        Intent intent = new Intent(NovoJogador.this, TelaJogador.class);
        Toast.makeText(this, "Operação cancelada", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
}
