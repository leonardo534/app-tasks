package com.leonardosilva.listadetarefas.helper;

import com.leonardosilva.listadetarefas.adapter.TarefaAdapter;
import com.leonardosilva.listadetarefas.model.TarefaModel;

import java.util.List;

public interface ITarefaDAO {
    public boolean salvar(TarefaModel tarefa);
    public boolean atualizar(TarefaModel tarefa);
    public boolean deletar(TarefaModel tarefa);
    public List<TarefaModel> listar();
}
