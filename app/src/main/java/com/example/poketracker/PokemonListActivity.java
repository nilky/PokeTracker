package com.example.poketracker;

import androidx.fragment.app.Fragment;

public class PokemonListActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment() {
        return new PokemonListFragment();
    }

}
