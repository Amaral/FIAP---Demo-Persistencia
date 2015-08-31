package br.com.felipeamaral.demopersistencia.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.felipeamaral.demopersistencia.model.Pessoa;

/**
 * Created by rm49345 on 26/08/2015.
 */
public class PessoaDAO {

    private DBHelper dbHelper;
    private Context context;

    public PessoaDAO(Context context) {
        dbHelper = new DBHelper(context);
        this.context = context;
    }

    public void salvar(Pessoa pessoa) {
        if(pessoa.getId() == null) {
            inserir(pessoa);
        }else {
            atualizar(pessoa);
        }
    }

    private void inserir(Pessoa pessoa) {
        ContentValues valores = new ContentValues();
        valores.put("nome", pessoa.getNome());
        valores.put("idade", pessoa.getIdade());

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long id = db.insert("pessoa",null, valores);
        pessoa.setId(id);
        db.close();
    }

    private void atualizar(Pessoa pessoa) {
        ContentValues valores = new ContentValues();

        valores.put("nome", pessoa.getNome());
        valores.put("idade", pessoa.getIdade());

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.update("pessoa",valores,"_id=?",new String[]{pessoa.getId().toString()});
        db.close();
    }

    public List<Pessoa> listaAll(){

        List<Pessoa> pessoas = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("pessoa", null, null, null, null, null, "nome ASC");

        try {
            while (cursor.moveToNext()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(cursor.getLong(cursor.getColumnIndex("_id")));
                pessoa.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                pessoa.setIdade(cursor.getInt(cursor.getColumnIndex("idade")));
                pessoas.add(pessoa);
            }
        }finally {
            cursor.close();
        }
        db.close();

        return pessoas;
    }
}
