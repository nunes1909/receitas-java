package com.example.receita.room.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.receita.model.Receita;
import com.example.receita.room.dao.ReceitaDAO;

@Database(entities = {Receita.class}, version = 1, exportSchema = false)
public abstract class ReceitaDatabase extends RoomDatabase {

    public abstract ReceitaDAO getReceitaDAO();

    public static ReceitaDatabase getInstance(Context context){
        return Room.databaseBuilder(context, ReceitaDatabase.class, "receita.db")
                .allowMainThreadQueries()
                .build();
    }
}
