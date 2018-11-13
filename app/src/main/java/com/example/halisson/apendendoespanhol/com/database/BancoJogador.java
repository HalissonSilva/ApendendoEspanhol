package com.example.halisson.apendendoespanhol.com.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Halisson on 30/09/2018.
 */

public class BancoJogador extends SQLiteOpenHelper {


    public BancoJogador(Context context) {
        super(context, "db_jogadores", null, 5);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE jogador (_id integer primary key autoincrement, nome text, pontos integer, progresso integer)";
        db.execSQL(sql);
        String sqlNivel = "CREATE TABLE nivel (_idjogador integer, level text, pontos integer)";
        db.execSQL(sqlNivel);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS jogador");
        db.execSQL("DROP TABLE IF EXISTS nivel");
        onCreate(db);

    }

}
