package com.example.receita.ui.recyclerview.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.receita.R;
import com.example.receita.model.Receita;
import com.example.receita.ui.recyclerview.listener.OnItemClickListener;

import java.util.List;

public class ReceitaAdapter extends RecyclerView.Adapter<ReceitaAdapter.ReceitaViewHolder> {
    private final List<Receita> receitas;
    private OnItemClickListener listener;

    public ReceitaAdapter(List<Receita> receitas) {
        this.receitas = receitas;
    }


    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ReceitaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_receita, parent, false);
        return new ReceitaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReceitaViewHolder holder, int position) {
        Receita receita = receitas.get(position);
        holder.vincula(receita);
    }

    @Override
    public int getItemCount() {
        return receitas.size();
    }

    class ReceitaViewHolder extends RecyclerView.ViewHolder {

        private TextView campoTitulo;
        private ImageView imagemTipo;
        private Receita receita;

        public ReceitaViewHolder(@NonNull View itemView) {
            super(itemView);
            campoTitulo = itemView.findViewById(R.id.item_receita_titulo);
            imagemTipo = itemView.findViewById(R.id.item_receita_tipo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClicik(receita, getAdapterPosition(), receita.getId());
                }
            });
        }

        public void vincula(Receita receita) {
            this.receita = receita;
            campoTitulo.setText(receita.getTitulo());

            switch (receita.getTipo()){
                case 0:
                    imagemTipo.setImageResource(R.drawable.ic_tipo_receita_refeicao);
                    break;
                case 1:
                    imagemTipo.setImageResource(R.drawable.ic_tipo_receita_lanche);
                    break;
                case 2:
                    imagemTipo.setImageResource(R.drawable.ic_tipo_receita_cafe);
                    break;
                default:
                    imagemTipo.setImageResource(R.drawable.ic_tipo_receita_drink);
                    break;
            }
        }
    }

    //save
    public void salva(Receita receita) {
        receitas.add(receita);
        notifyDataSetChanged();
    }

    //remove
    public void remove(int posicao) {
        receitas.remove(posicao);
        notifyItemRemoved(posicao);
    }

    //remove todos
    public void removeAll() {
        receitas.clear();
        notifyDataSetChanged();
    }

    //busca pela posição
    public Receita getAt(int posicao) {
        return receitas.get(posicao);
    }

    public void move(int posicaoInicial, int posicaoFinal) {
        Receita receitaInicial = receitas.get(posicaoInicial);
        receitas.remove(posicaoInicial);
        receitas.add(posicaoFinal, receitaInicial);
        notifyItemMoved(posicaoInicial, posicaoFinal);
    }

    //edit
    public void edita(int posicao, Receita receita) {
        receitas.set(posicao, receita);
        notifyDataSetChanged();
    }
}
