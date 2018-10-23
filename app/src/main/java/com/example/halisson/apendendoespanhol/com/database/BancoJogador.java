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

        super(context, "db_jogadores", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String sql = "CREATE TABLE jogador (_id integer primary key autoincrement, nome text, pontos text, nivel text)";

        db.execSQL(sql);
    }

    @Override

    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS jogador");
        onCreate(db);

    }

    }
