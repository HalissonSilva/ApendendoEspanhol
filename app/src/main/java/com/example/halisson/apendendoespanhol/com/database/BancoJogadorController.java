package com.example.halisson.apendendoespanhol.com.database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Halisson on 01/10/2018.
 */

public class BancoJogadorController {
    private SQLiteDatabase db_jogadores;
    private BancoJogador banco;
    private String campos[] = { "_id", "nome", "pontos", "progresso"};
    private Cursor cursor;
   // private static String ORDER_BY = pontos+"DESC";

    public BancoJogadorController(Context context){
        banco = new BancoJogador(context);
    }

    public long inserir(String nome, int pontos, int nivel){
        ContentValues valores;
        long resultado;
        db_jogadores = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put("nome", nome);
        valores.put("pontos", pontos);
        valores.put("progresso", nivel);
        resultado = db_jogadores.insertOrThrow("jogador",null,valores);
        db_jogadores.close();

        return resultado;
    }

    public Cursor buscarTodos(){
        db_jogadores = banco.getReadableDatabase();
        cursor = db_jogadores.query("jogador",campos,null,null,null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor buscarTodosRanking(){
        db_jogadores = banco.getReadableDatabase();
        cursor = db_jogadores.query("jogador",campos,null,null,null,null,"pontos DESC");
        if(cursor!=null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor buscarPorId(String id){
        db_jogadores=banco.getReadableDatabase();
        cursor = db_jogadores.query("jogador", campos,"_id = ?", new String[]{id}, null, null, null, null);
        if (cursor!=null){
            cursor.moveToFirst();
        }
        db_jogadores.close();
        return cursor;
    }

    public void alterar(String id, int pontos, int nivel){
        ContentValues valores;
        db_jogadores = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put("pontos", pontos);
        valores.put("progresso", nivel);

        db_jogadores.update("jogador", valores, "_id = ?", new String[]{String.valueOf(id)});
    }

}
