package com.example.poketracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import database.PokemonDbSchema.PokemonBaseHelper;
import database.PokemonDbSchema.PokemonCursorWrapper;
import database.PokemonDbSchema.PokemonDbSchema;

public class PokemonLab {
    private static PokemonLab sPokemonLab;

    private List<Pokemon> mPokemons;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static PokemonLab get(Context context) {
        if (sPokemonLab == null) {
            sPokemonLab = new PokemonLab(context);
        }
        return sPokemonLab;
        }
    private PokemonLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new PokemonBaseHelper(mContext).getWritableDatabase();
        mPokemons = new ArrayList<>();
    }

    public void addPokemon(Pokemon p) {
        ContentValues values = getContentValues(p);

        mDatabase.insert(PokemonDbSchema.PokemonTable.NAME, null, values);
    }

    public void deletePokemon(Pokemon p) {
        ContentValues values = getContentValues(p);

        mDatabase.delete(PokemonDbSchema.PokemonTable.NAME, null, null);
    }

    public List<Pokemon> getmPokemons() {
        List<Pokemon> pokemons = new ArrayList<>();

        PokemonCursorWrapper cursor = queryPokemons(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                pokemons.add(cursor.getPokemon());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return pokemons;
    }
    public Pokemon getPokemon(UUID id) {
        PokemonCursorWrapper cursor = queryPokemons(PokemonDbSchema.PokemonTable.Cols.UUID + " = ?", new String[] { id.toString() });
        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getPokemon();
        } finally {
            cursor.close();
        }

    }

    public void updatePokemon(Pokemon pokemon) {
        String uuidString = pokemon.getmId().toString();
        ContentValues values = getContentValues(pokemon);

        mDatabase.update(PokemonDbSchema.PokemonTable.NAME, values, PokemonDbSchema.PokemonTable.Cols.UUID + " = ?", new String[] { uuidString });
    }

    private PokemonCursorWrapper queryPokemons(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(PokemonDbSchema.PokemonTable.NAME, null, whereClause, whereArgs, null, null, null);
        return new PokemonCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Pokemon pokemon) {
        ContentValues values = new ContentValues();
        values.put(PokemonDbSchema.PokemonTable.Cols.UUID, pokemon.getmId().toString());
        values.put(PokemonDbSchema.PokemonTable.Cols.TITLE, pokemon.getmName());
        values.put(PokemonDbSchema.PokemonTable.Cols.DATE, pokemon.getmDate().getTime());
        values.put(PokemonDbSchema.PokemonTable.Cols.SHINY, pokemon.ismShiny() ? 1 : 0);
        values.put(PokemonDbSchema.PokemonTable.Cols.REGIONAL, pokemon.ismRegional() ? 1 : 0);
        values.put(PokemonDbSchema.PokemonTable.Cols.IV, pokemon.ismIV() ? 1 : 0);
        values.put(PokemonDbSchema.PokemonTable.Cols.LUCKY, pokemon.ismLucky() ? 1 : 0);
        return values;
    }
}
