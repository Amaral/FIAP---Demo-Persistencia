package br.com.felipeamaral.demopersistencia.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rm49345 on 26/08/2015.
 */
public class DBHelper extends SQLiteOpenHelper  {

    private static final String NOME_BANCO = "pessoa.db";
    private static final int VERSAO_BANCO = 1;


    public DBHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "" +
                     "CREATE TABLE pessoa("+
                     "_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                     "nome TEXT NOT NULL, " +
                     "idade INTEGER NOT NULL)";

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
