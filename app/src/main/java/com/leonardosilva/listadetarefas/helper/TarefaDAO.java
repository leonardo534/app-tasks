package com.leonardosilva.listadetarefas.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.leonardosilva.listadetarefas.AdicionarTarefaActivity;
import com.leonardosilva.listadetarefas.model.TarefaModel;

import java.util.ArrayList;
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
        ContentValues cv = new ContentValues();
        cv.put("nome", tarefa.getNomeTarefa());

        try{
            String[] args = {tarefa.getId().toString()};
            escreve.update(DbHelper.TABELA_TAREFAS, cv, "id=?", args);
        }catch (Exception e){
            Log.i("TAG", "ERROR AO ATUALIZAR: "+e.getMessage());

        }


        return true;
    }

    @Override
    public boolean deletar(TarefaModel tarefa) {
        String[] args = {tarefa.getId().toString()};
        try{
            escreve.delete(DbHelper.TABELA_TAREFAS, "id=?", args);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public List<TarefaModel> listar() {

        List<TarefaModel> tarefasList = new ArrayList<>();

        String sql = "SELECT * FROM "+DbHelper.TABELA_TAREFAS+" ;";
        Cursor c = ler.rawQuery(sql, null);
        while (c.moveToNext()){
            TarefaModel tarefaModel = new TarefaModel();
            Long id = c.getLong(c.getColumnIndex("id"));
            String nomeTarefa = c.getString(c.getColumnIndex("nome"));

            tarefaModel.setId(id);
            tarefaModel.setNomeTarefa(nomeTarefa);

            tarefasList.add(tarefaModel);
        }
        return  tarefasList;

    }
}
