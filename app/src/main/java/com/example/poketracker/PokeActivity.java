package com.example.poketracker;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import java.util.UUID;


public class PokeActivity extends SingleFragmentActivity {

    private static final String EXTRA_POKE_ID = "com.bignerdranch.android.poketracker.poke_id";

    public static Intent newIntent(Context packageContext, UUID pokeId) {
        Intent intent = new Intent(packageContext, PokeActivity.class);
        intent.putExtra(EXTRA_POKE_ID, pokeId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID pokeId = (UUID) getIntent()
        .getSerializableExtra(EXTRA_POKE_ID);
        return PokemonFragment.newInstance(pokeId);
    }
}
