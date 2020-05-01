package com.leonardosilva.listadetarefas.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.leonardosilva.listadetarefas.model.TarefaModel;

import java.util.List;

public class TarefaDAO  implements ITarefaDAO{

    private SQLiteDatabase escreve;
    private SQLiteDatabase ler;

    public TarefaDAO(Context context) {
        DbHelper db = new DbHelper(context);
        escreve = db.getWritableDatabase();
        ler = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(TarefaModel tarefa) {
        ContentValues cv = new ContentValues();
        cv.put("nome", tarefa.getNomeTarefa());

        try {
            escreve.insert(DbHelper.TABELA_TAREFAS, null,  cv);
            Log.i("TAG", "SUCESSO AO SALVAR: ");

        }catch (Exception e){
            Log.i("TAG", "ERROR AO SALVAR: "+e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean atualizar(TarefaModel tarefa) {
        return false;
    }

    @Override
    public boolean deletar(TarefaModel tarefa) {
        return false;
    }

    @Override
    public List<TarefaModel> listar() {
        return null;
    }
}
