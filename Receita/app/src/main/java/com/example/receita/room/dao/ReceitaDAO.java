package com.example.receita.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.receita.model.Receita;

import java.util.List;

@Dao
public interface ReceitaDAO {

    @Query("SELECT * FROM receita")
    List<Receita> todas();

    @Insert
    Long salva(Receita receita);

    @Query("UPDATE receita SET titulo = :titulo, descricao = :descricao, tipo = :tipo WHERE id = :id")
    void edita(String titulo, String descricao, int tipo, long id);

    @Query("DELETE FROM receita")
    void removeAll();

    @Delete
    void remove(Receita receita);
}
