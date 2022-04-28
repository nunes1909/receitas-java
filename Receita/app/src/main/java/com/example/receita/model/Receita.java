package com.example.receita.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Receita implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String titulo;
    private String descricao;
    private int tipo;

    public Receita(String titulo, String descricao, int tipo) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.tipo = tipo;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
