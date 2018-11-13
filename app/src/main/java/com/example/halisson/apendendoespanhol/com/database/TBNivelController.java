package com.example.halisson.apendendoespanhol.com.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TBNivelController {
    private SQLiteDatabase db_jogadores;
    private BancoJogador banco;
    private String campos[] = { "_idjogador", "level", "pontos" };
    private Cursor cursor;
    // private static String ORDER_BY = pontos+"DESC";

    public TBNivelController(Context context){
        banco = new BancoJogador(context);
    }

    public String inserir(String _idjogador, String level, int pontos){
        ContentValues valores;
        long resultado;
        db_jogadores = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put("_idjogador", Integer.parseInt(_idjogador));
        valores.put("level", level);
        valores.put("pontos", pontos);
        resultado = db_jogadores.insertOrThrow("nivel",null,valores);
        db_jogadores.close();

        if(resultado == -1)
            return "Erro ao Iserir Registro!";
        else
            return "Registro inserido!";
    }

    public Cursor buscarTodos(){
        db_jogadores = banco.getReadableDatabase();
        cursor = db_jogadores.query("nivel",campos,null,null,null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor buscarPorId(String idJogador){
        db_jogadores=banco.getReadableDatabase();
        cursor = db_jogadores.query("nivel", campos,"_idjogador = ?", new String[] {idJogador}, null, null, null, null);
        if (cursor!=null){
            cursor.moveToFirst();
        }
        db_jogadores.close();
        return cursor;
    }

    public void alterar(String _idjogador, String level, int pontos){
        ContentValues valores;
        db_jogadores = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put("_idjogador", _idjogador);
        valores.put("level", level);
        valores.put("pontos", pontos);

        db_jogadores.update("nivel", valores, "_idjogador = ? AND level = ?", new String[]{_idjogador, level});
    }
}
