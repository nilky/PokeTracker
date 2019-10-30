package com.example.poketracker;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PokemonLab {
    private static PokemonLab sPokemonLab;

    private List<Pokemon> mPokemons;

    public static PokemonLab get(Context context) {
        if (sPokemonLab == null) {
            sPokemonLab = new PokemonLab(context);
        }
        return sPokemonLab;
        }
    private PokemonLab(Context context) {
        mPokemons = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Pokemon pokemon = new Pokemon();
            pokemon.setmName("Pokemon #" + i);
            pokemon.setmShiny(i % 2 == 0);
            pokemon.setmRegional(i % 2 == 0);
            pokemon.setmIV(i % 2 == 0);
            pokemon.setmLucky(i % 2 == 0);
            mPokemons.add(pokemon);
        }
    }
    public List<Pokemon> getmPokemons() {
        return mPokemons;
    }
    public Pokemon getPokemon(UUID id) {
        for (Pokemon pokemon : mPokemons) {
            if (pokemon.getmId().equals(id)) {
                return pokemon;
            }
        }
        return null;
    }
}
