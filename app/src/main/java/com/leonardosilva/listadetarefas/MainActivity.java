package com.leonardosilva.listadetarefas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.leonardosilva.listadetarefas.adapter.TarefaAdapter;
import com.leonardosilva.listadetarefas.helper.RecyclerItemClickListener;
import com.leonardosilva.listadetarefas.helper.TarefaDAO;
import com.leonardosilva.listadetarefas.model.TarefaModel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TarefaAdapter adapter;
    private List<TarefaModel> listaTarefas = new ArrayList<>();
    private TarefaModel tarefaSelecionada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                TarefaModel tarefaSelecionada = listaTarefas.get(position);

                Intent intent = new Intent(MainActivity.this, AdicionarTarefaActivity.class);
                intent.putExtra("tarefaSelecionada", tarefaSelecionada);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {
                tarefaSelecionada = listaTarefas.get(position);
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);

                dialog.setTitle("Confirmar exclusão");
                dialog.setMessage("Deseja excluir a tarefa: "+tarefaSelecionada.getNomeTarefa() + " ?");

                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
                        if (tarefaDAO.deletar(tarefaSelecionada)) {
                            carregarListadeTarefas();
                            Toast.makeText(MainActivity.this, "Sucesso ao excluir", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this, "Erro ao excluir", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

                dialog.setNegativeButton("Não", null);

                dialog.create();
                dialog.show();
            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        }));


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdicionarTarefaActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        carregarListadeTarefas();
        super.onStart();
    }

    public void carregarListadeTarefas(){
    TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
    listaTarefas = tarefaDAO.listar();

    adapter = new TarefaAdapter(listaTarefas);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
