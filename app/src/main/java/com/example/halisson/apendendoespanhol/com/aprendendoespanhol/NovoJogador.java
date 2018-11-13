package com.example.halisson.apendendoespanhol.com.aprendendoespanhol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.halisson.apendendoespanhol.R;
import com.example.halisson.apendendoespanhol.com.database.BancoJogadorController;
import com.example.halisson.apendendoespanhol.com.database.TBNivelController;

public class NovoJogador extends AppCompatActivity {

    EditText edtNome;
    Button btnCadastrar, btnCancelar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_jogador);

        edtNome = (EditText)findViewById(R.id.edtNome);
        edtNome.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
        btnCadastrar = (Button)findViewById(R.id.btnCadastrar);
        btnCancelar = (Button)findViewById(R.id.btnCancelar);


        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BancoJogadorController crud = new BancoJogadorController(getBaseContext());
                TBNivelController nivelController = new TBNivelController(getBaseContext());
                edtNome = (EditText)findViewById(R.id.edtNome);
                String nomeStr = edtNome.getText().toString();
                int pontosStr = 0;
                String msg;
                if (!nomeStr.equals("")){
                    long r = crud.inserir(nomeStr, pontosStr, 1);
                    nivelController.inserir(String.valueOf(r),"1", 0);
                    nivelController.inserir(String.valueOf(r),"2", 0);
                    msg = nivelController.inserir(String.valueOf(r),"3", 0);
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
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(NovoJogador.this, TelaJogador.class);
        startActivity(intent);
        finish();
    }
}
