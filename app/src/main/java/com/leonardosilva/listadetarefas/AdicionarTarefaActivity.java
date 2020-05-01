package com.leonardosilva.listadetarefas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.textfield.TextInputEditText;
import com.leonardosilva.listadetarefas.helper.TarefaDAO;
import com.leonardosilva.listadetarefas.model.TarefaModel;

public class AdicionarTarefaActivity extends AppCompatActivity {

    private TextInputEditText editTarefa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);
        editTarefa = findViewById(R.id.textTarefa);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_adicionar_tarefa, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemSalvar:
                TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
                TarefaModel tarefa = new TarefaModel();
                tarefa.setNomeTarefa("Ir correr");

                tarefaDAO.salvar(tarefa);
                break;
        }


        return super.onOptionsItemSelected(item);
    }
}
