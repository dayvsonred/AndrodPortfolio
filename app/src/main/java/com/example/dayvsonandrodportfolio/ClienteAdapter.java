package com.example.dayvsonandrodportfolio;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.dayvsonandrodportfolio.dominio.entidade.Cliente;

import java.util.List;

public class ClienteAdapter extends RecyclerView.Adapter {

    private List<Cliente> dados;

    public ClienteAdapter(List<Cliente> dados) {
        this.dados =  dados;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    private class ViewHolderCliente extends RecyclerView.ViewHolder{

        public ViewHolderCliente(@NonNull View itemView) {
            super(itemView);
        }
    }
}
