package com.example.receita.ui.activity;

import static com.example.receita.ui.activity.ConstantesActivities.LISTA_RECEITA_TITULO_APPBAR;
import static com.example.receita.ui.activity.ConstantesActivities.MENSAGEM_ERRO_EDITAR;
import static com.example.receita.ui.activity.ConstantesActivities.MENSAGEM_ERRO_SALVAR;
import static com.example.receita.ui.activity.ConstantesActivities.POSICAO;
import static com.example.receita.ui.activity.ConstantesActivities.RECEITA;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.RoomDatabase;

import com.example.receita.R;
import com.example.receita.model.Receita;
import com.example.receita.room.dao.ReceitaDAO;
import com.example.receita.room.database.ReceitaDatabase;
import com.example.receita.ui.interno.DAO.DaoInterno;
import com.example.receita.ui.recyclerview.adapter.ReceitaAdapter;
import com.example.receita.ui.recyclerview.helper.ReceitaItemTouchHelper;
import com.example.receita.ui.recyclerview.listener.OnItemClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListaReceitasActivity extends AppCompatActivity {
    private FloatingActionButton botaoNovaReceita;
    private RecyclerView listaDeReceitas;
    private ReceitaDAO dao;
    private ReceitaAdapter adapter;
    private List<Receita> todasReceitas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_receitas);
        setTitle(LISTA_RECEITA_TITULO_APPBAR);
        configuraBotaoNovaReceita();
        dao = ReceitaDatabase.getInstance(this).getReceitaDAO();
        todasReceitas = dao.todas();
        configuraRecyclerView(this.todasReceitas);
    }

    private void configuraBotaoNovaReceita() {
        botaoNovaReceita = findViewById(R.id.lista_receitas_fab_nova_receita);
        botaoNovaReceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vaiParaNovaReceita = new Intent(ListaReceitasActivity.this,
                        NovaReceitaActivity.class);
                startActivityForResult(vaiParaNovaReceita, 1);
            }
        });
    }

    private void configuraRecyclerView(List<Receita> todasReceitas) {
        listaDeReceitas = findViewById(R.id.lista_receitas_recycler_view);
        configuraAdapter(todasReceitas);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ReceitaItemTouchHelper(adapter, this, dao));
        itemTouchHelper.attachToRecyclerView(listaDeReceitas);
    }

    private void configuraAdapter(List<Receita> todasReceitas) {
        adapter = new ReceitaAdapter(todasReceitas);
        listaDeReceitas.setAdapter(adapter);
        adapter.setListener(new OnItemClickListener() {
            @Override
            public void onItemClicik(Receita receita, int posicao, long id) {
                Intent vaiParaNovaReceitaEdita = new Intent(
                        ListaReceitasActivity.this, NovaReceitaActivity.class);
                vaiParaNovaReceitaEdita.putExtra(RECEITA, receita);
                vaiParaNovaReceitaEdita.putExtra(POSICAO, posicao);
                vaiParaNovaReceitaEdita.putExtra("id", id);
                startActivityForResult(vaiParaNovaReceitaEdita, 2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && data != null && data.hasExtra(RECEITA)) {
            Receita receitaRecebidaInsere = (Receita) data.getSerializableExtra(RECEITA);
            if (resultCode == RESULT_OK) {
                long id = dao.salva(receitaRecebidaInsere);
                receitaRecebidaInsere.setId(id);
                adapter.salva(receitaRecebidaInsere);
            } else {
                Toast.makeText(this,
                        MENSAGEM_ERRO_SALVAR, Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == 2 && data != null && data.hasExtra(POSICAO)) {
            Receita receitaRecebidaEdita = (Receita) data.getSerializableExtra(RECEITA);
            String tituloRecebido = receitaRecebidaEdita.getTitulo();
            String descricaoRecebida = receitaRecebidaEdita.getDescricao();

            int tipoRecebido = receitaRecebidaEdita.getTipo();
            int posicaoRecebidaEdita = data.getIntExtra(POSICAO, -1);

            long idRecebido = data.getLongExtra("id", -1);

            if (resultCode == RESULT_OK) {
                dao.edita(tituloRecebido, descricaoRecebida, tipoRecebido, idRecebido);
                adapter.edita(posicaoRecebidaEdita, receitaRecebidaEdita);
            } else {
                Toast.makeText(this,
                        MENSAGEM_ERRO_EDITAR, Toast.LENGTH_SHORT).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_menu_lista_receita, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.item_limpa_lista) {
            new AlertDialog.Builder(this)
                    .setTitle("Excluindo todas as receitas")
                    .setMessage("Tem certeza que quer excluir todas as receitas?")
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dao.removeAll();
                            adapter.removeAll();
                            Toast.makeText(ListaReceitasActivity.this,
                                    "Todas as receitas foram excluídas!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            adapter.notifyDataSetChanged();
                        }
                    })
                    .setIcon(R.drawable.ic_action_alert)
                    .show();
            adapter.notifyDataSetChanged();
        }

        return super.onOptionsItemSelected(item);
    }
}