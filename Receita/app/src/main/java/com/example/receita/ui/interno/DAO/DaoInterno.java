package com.example.receita.ui.interno.DAO;

import com.example.receita.model.Receita;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DaoInterno {

    private final static ArrayList<Receita> receitas = new ArrayList<>();

    public List<Receita> todasReceitas(){
        return (List<Receita>) receitas.clone();
    }

    //save
    public void salva(Receita... receitas) {
        DaoInterno.receitas.addAll(Arrays.asList(receitas));
    }

    //remove
    public void remove(Receita receita) {
        receitas.remove(receita);
    }

    //remove todos
    public void removeAll() {
        receitas.clear();
    }

    //move
    public void move(int posicao, Receita receita) {
        receitas.set(posicao, receita);
    }

    //edita
    public void edita(int posicao, Receita receita) {
        receitas.set(posicao, receita);
    }


}
