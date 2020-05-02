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
    private TarefaModel tarefaAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);
        editTarefa = findViewById(R.id.textTarefa);

        tarefaAtual = (TarefaModel) getIntent().getSerializableExtra("tarefaSelecionada");
        if (tarefaAtual != null) {
            editTarefa.setText(tarefaAtual.getNomeTarefa());
        }
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

                String nomeTarefa = editTarefa.getText().toString();
                if (!nomeTarefa.isEmpty()) {
                    TarefaModel tarefa = new TarefaModel();
                    tarefa.setNomeTarefa(nomeTarefa);
                    finish();
                    tarefaDAO.salvar(tarefa);
                }

                break;
        }


        return super.onOptionsItemSelected(item);
    }
}
