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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.halisson.apendendoespanhol.R;
import com.example.halisson.apendendoespanhol.com.database.BancoJogadorController;
import com.example.halisson.apendendoespanhol.com.database.TBNivelController;

import org.w3c.dom.Text;

public class TelaJogador extends AppCompatActivity {

    private Cursor cursor;
    private BancoJogadorController crud;

    private Button btnJogar, btnNovoJogador;

    private ListView lista;

    private LinearLayout llMensagem;

    private TextView txtNome, txtMensagem;

    private String nome, id = "";

    private TBNivelController tbController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_jogador);

        crud = new BancoJogadorController(getBaseContext());
        cursor = crud.buscarTodos();

        llMensagem = findViewById(R.id.llMensagem);

        txtMensagem = (TextView) findViewById(R.id.txtMensagem);

        btnJogar = (Button) findViewById(R.id.btnJogar);
        btnNovoJogador = (Button) findViewById(R.id.btnNovoJogador);

        String[] nomeCampos = new String[]{"_id", "nome"};
        int[] idView = new int[]{R.id.txtLtIdJogador, R.id.txtLtNomeJogador};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                R.layout.jogador_layout, cursor, nomeCampos, idView);
        lista = (ListView) findViewById(R.id.listJogadores);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                TextView txtLtNome = view.findViewById(R.id.txtLtNomeJogador);
                TextView txtLtId = view.findViewById(R.id.txtLtIdJogador);

                nome = txtLtNome.getText().toString();
                id = txtLtId.getText().toString();

                llMensagem.setVisibility(View.VISIBLE);
                txtMensagem.setText("Vamos jogar " + nome + "?");

                btnJogar.setEnabled(true);

            }
        });


    }

    public void Jogar(View v) {
        cursor = crud.buscarPorId(id);
        Sessao.NOME_JOGADOR = nome;
        Sessao.ID = id;
        Sessao.PONTOS = cursor.getString(cursor.getColumnIndex("pontos"));
        Sessao.PROGRESSO = cursor.getInt(cursor.getColumnIndex("progresso"));


        Intent intent = new Intent(TelaJogador.this, TelaHistorias.class);
        startActivity(intent);
        finish();
    }

    public void NovoJogador(View v) {
        Intent intent = new Intent(TelaJogador.this, NovoJogador.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
