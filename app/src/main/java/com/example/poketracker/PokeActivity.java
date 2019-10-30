package com.example.poketracker;

import androidx.fragment.app.Fragment;


public class PokeActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new PokemonFragment();
    }
}
