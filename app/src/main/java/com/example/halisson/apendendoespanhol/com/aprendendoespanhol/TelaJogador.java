package com.example.halisson.apendendoespanhol.com.aprendendoespanhol;

import android.bluetooth.BluetoothAssignedNumbers;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.halisson.apendendoespanhol.R;
import com.example.halisson.apendendoespanhol.com.database.BancoJogadorController;

public class TelaJogador extends AppCompatActivity {

    private Cursor cursor;
    private BancoJogadorController crud;
    public String codigo;
    String nome ;


    Button btnJogar, btnNovoJogador;
    Button btnBuscarJogador;
    private ListView lista;
    public String codigoJogador, nomeJogador, pontosJogador, nivelJogador;

    private EditText edtNome;
    private String edtCodigo = "";
    private EditText edtPontos;
    private EditText edtNivel;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_jogador);

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtPontos = (EditText)findViewById(R.id.edtPontos);
        edtNivel = (EditText)findViewById(R.id.edtNivel);

        codigo = this.getIntent().getStringExtra("codigo");
        crud = new BancoJogadorController(getBaseContext());

        btnJogar = (Button) findViewById(R.id.btnJogar);
        btnNovoJogador = (Button) findViewById(R.id.btnNovoJogador);
        btnBuscarJogador = (Button) findViewById(R.id.btnBuscar);

        BancoJogadorController crud = new BancoJogadorController(getBaseContext());
        final Cursor cursor = crud.buscarTodos();

        btnBuscarJogador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] nomeCampos = new String[]{"_id", "nome", "pontos", "nivel"};
                int[] idView = new int[]{R.id.idJogador, R.id.nomeJogador, R.id.pontosJogador, R.id.nivelJogador};

                SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                        R.layout.jogador_layout, cursor, nomeCampos, idView);
                lista = (ListView) findViewById(R.id.listJogadores);
                lista.setAdapter(adaptador);

                lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        edtNome.setText(cursor.getString(cursor.getColumnIndexOrThrow("nome")));
                        edtCodigo = cursor.getString(cursor.getColumnIndexOrThrow("idJogador"));
                        edtPontos.setText(cursor.getString(cursor.getColumnIndexOrThrow("pontos")));
                        edtNivel.setText(cursor.getString(cursor.getColumnIndexOrThrow("nivel")));

                        btnJogar.setEnabled(true);

                    }
                });
            }
        });


    }

       public void Jogar(View v){

           if (nome.equals("") ){
               Toast.makeText(getApplicationContext(),R.string.msg_selecioneumjogador,Toast.LENGTH_LONG).show();

               Intent intent = getIntent();
               Bundle dadosJogador = new Bundle();
               dadosJogador = intent.getExtras();
               codigoJogador=dadosJogador.getString("codigo").toString();
               nomeJogador=dadosJogador.getString("nome").toString();
               pontosJogador=dadosJogador.getString("pontos").toString();
               nivelJogador=dadosJogador.getString("nivel").toString();

           }else{
               Sessao.NOME_JOGADOR = edtNome.getText().toString();
               Sessao.NIVEL = edtNivel.getText().toString();
               Sessao.PONTOS = edtPontos.getText().toString();
               Sessao.CODIGO = edtCodigo.toString();

               Intent intent = new Intent(TelaJogador.this, TelaHistorias.class);
               startActivity(intent);
               finish();
           }

    }
    public void NovoJogador(View v){
        Intent intent = new Intent(TelaJogador.this, NovoJogador.class);
        startActivity(intent);
        finish();

    }



}
