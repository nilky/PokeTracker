package database.PokemonDbSchema;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PokemonBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "pokemonBase.db";

    public PokemonBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + PokemonDbSchema.PokemonTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                PokemonDbSchema.PokemonTable.Cols.UUID + ", " +
                PokemonDbSchema.PokemonTable.Cols.TITLE + ", " +
                PokemonDbSchema.PokemonTable.Cols.DATE + ", " +
                PokemonDbSchema.PokemonTable.Cols.SHINY + ", " +
                PokemonDbSchema.PokemonTable.Cols.REGIONAL + ", " +
                PokemonDbSchema.PokemonTable.Cols.IV + ", " +
                PokemonDbSchema.PokemonTable.Cols.LUCKY +
                ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
