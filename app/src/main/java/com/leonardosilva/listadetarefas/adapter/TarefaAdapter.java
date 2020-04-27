package com.leonardosilva.listadetarefas.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.leonardosilva.listadetarefas.model.TarefaModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.MyViewHolder> {
    private List<TarefaModel> listaTarefas = new ArrayList<>();

    public TarefaAdapter(List<TarefaModel> lista) {
        this.listaTarefas = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }


    }
}
