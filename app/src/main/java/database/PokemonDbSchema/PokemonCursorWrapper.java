package database.PokemonDbSchema;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.poketracker.Pokemon;

import java.util.Date;
import java.util.UUID;

public class PokemonCursorWrapper extends CursorWrapper {
    public PokemonCursorWrapper(Cursor cursor) {
        super(cursor);
    }
    public Pokemon getPokemon() {
        String uuidString = getString(getColumnIndex(PokemonDbSchema.PokemonTable.Cols.UUID));
        String title = getString(getColumnIndex(PokemonDbSchema.PokemonTable.Cols.TITLE));
        long date = getLong(getColumnIndex(PokemonDbSchema.PokemonTable.Cols.DATE));
        int isShiny = getInt(getColumnIndex(PokemonDbSchema.PokemonTable.Cols.SHINY));
        int isRegional = getInt(getColumnIndex(PokemonDbSchema.PokemonTable.Cols.REGIONAL));
        int isIV = getInt(getColumnIndex(PokemonDbSchema.PokemonTable.Cols.IV));
        int isLucky = getInt(getColumnIndex(PokemonDbSchema.PokemonTable.Cols.LUCKY));

        Pokemon pokemon = new Pokemon(UUID.fromString(uuidString));
        pokemon.setmName(title);
        pokemon.setmDate(new Date(date));
        pokemon.setmShiny(isShiny != 0);
        pokemon.setmRegional(isRegional != 0);
        pokemon.setmIV(isIV != 0);
        pokemon.setmLucky(isLucky != 0);

        return pokemon;
    }
}
