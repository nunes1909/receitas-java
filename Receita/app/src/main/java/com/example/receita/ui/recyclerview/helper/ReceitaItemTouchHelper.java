package com.example.receita.ui.recyclerview.helper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.receita.model.Receita;
import com.example.receita.room.dao.ReceitaDAO;
import com.example.receita.ui.interno.DAO.DaoInterno;
import com.example.receita.ui.recyclerview.adapter.ReceitaAdapter;

public class ReceitaItemTouchHelper extends ItemTouchHelper.Callback {
    private ReceitaAdapter adapter;
    private Context context;
    private ReceitaDAO dao;

    public ReceitaItemTouchHelper(ReceitaAdapter adapter, Context context, ReceitaDAO dao) {
        this.adapter = adapter;
        this.context = context;
        this.dao = dao;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int deslizar = ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
        int arrastar = ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT
                | ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        return makeMovementFlags(0, deslizar);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//        int posicaoInicial = viewHolder.getAdapterPosition();
//        int posicaoFinal = target.getAdapterPosition();
//        Receita receitaInicial = adapter.getAt(posicaoInicial);
//        Receita receitaFinal = adapter.getAt(posicaoFinal);
//
//        dao.move(posicaoFinal, receitaInicial);
//        dao.move(posicaoInicial, receitaFinal);
//        adapter.move(posicaoInicial, posicaoFinal);
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        new AlertDialog.Builder(context)
                .setTitle("Excluindo uma receita")
                .setMessage("Tem certeza que quer excluir a receita?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int posicaoObtida = viewHolder.getAdapterPosition();
                        dao.remove(adapter.getAt(posicaoObtida));
                        adapter.remove(posicaoObtida);
                        Toast.makeText(context, "Receita excluída", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        adapter.notifyDataSetChanged();
                    }
                })
                .show();
        adapter.notifyDataSetChanged();
    }
}
