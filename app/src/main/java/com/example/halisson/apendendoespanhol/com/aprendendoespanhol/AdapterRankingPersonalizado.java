package com.example.halisson.apendendoespanhol.com.aprendendoespanhol;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.halisson.apendendoespanhol.R;

public class AdapterRankingPersonalizado extends CursorAdapter {

    private LayoutInflater cursorInflater;
    private int posicao = 1;
    private Cursor meuCursor;

    public AdapterRankingPersonalizado(Context context, Cursor c, int flags) {
        super(context, c, flags);
        cursorInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        meuCursor = c;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_jogador_completo, viewGroup, false);
        bindView(view, context, cursor);
//        cursorInflater.inflate(R.layout.layout_jogador_completo, viewGroup, false);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtPosicao = (TextView) view.findViewById(R.id.txtRankingPosicao);
        TextView txtNome = (TextView) view.findViewById(R.id.txtRankingNome);
        TextView txtPontos = (TextView) view.findViewById(R.id.txtRankingPontos);

        txtPosicao.setText(String.valueOf(cursor.getPosition()+1) + "º");
        txtNome.setText(cursor.getString(cursor.getColumnIndex("nome")));
        txtPontos.setText(cursor.getString(cursor.getColumnIndex("pontos")));
        Log.e("Posicao", String.valueOf(cursor.getPosition()));
//        posicao += 1;
    }
}
