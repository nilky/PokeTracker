package com.example.poketracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PokemonListFragment extends Fragment {

    private static final String SAVED_SUBTITLE_VISIBLE = "subtitle";
    private RecyclerView mPokemonRecyclerView;
    private PokemonAdapter mAdapter;
    private boolean mSubtitleVisible;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pokemon_list, container, false);

        mPokemonRecyclerView = (RecyclerView) view.findViewById(R.id.pokemon_recycler_view);
        mPokemonRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (savedInstanceState != null) {
            mSubtitleVisible = savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
        }

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE, mSubtitleVisible);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_pokemon_list, menu);

        MenuItem subtitleItem = menu.findItem(R.id.show_subtitle);
        if (mSubtitleVisible) {
            subtitleItem.setTitle(R.string.hide_subtitle);
        } else {
            subtitleItem.setTitle(R.string.show_subtitle);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_pokemon:
                Pokemon pokemon = new Pokemon();

                PokemonLab.get(getActivity()).addPokemon(pokemon);
                Intent intent = PokeActivity.newIntent(getActivity(),pokemon.getmId());
                startActivity(intent);
                return true;
            case R.id.show_subtitle:
                mSubtitleVisible = !mSubtitleVisible;
                getActivity().invalidateOptionsMenu();
                updateSubtitle();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateSubtitle() {
        PokemonLab pokemonLab = PokemonLab.get(getActivity());
        int pokemonCount = pokemonLab.getmPokemons().size();
        String subtitle =
                getString(R.string.subtitle_format, pokemonCount);

        if (!mSubtitleVisible) {
            subtitle = null;
        }

        AppCompatActivity activity = (AppCompatActivity)
                getActivity();

        activity.getSupportActionBar().setSubtitle(subtitle);
    }

    private void updateUI() {
        PokemonLab pokemonLab = PokemonLab.get(getActivity());
        List<Pokemon> pokemons = pokemonLab.getmPokemons();

        if (mAdapter == null) {
            mAdapter = new PokemonAdapter(pokemons);
            mPokemonRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setPokemons(pokemons);
            mAdapter.notifyDataSetChanged();
        }

        updateSubtitle();
    }

    private class PokemonHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Pokemon mPokemon;

        private TextView mNameTextView;
        private TextView mDateTextView;

        public void bind(Pokemon pokemon) {
            mPokemon = pokemon;
            mNameTextView.setText(mPokemon.getmName());
            mDateTextView.setText(mPokemon.getmDate().toString());
        }

        public PokemonHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_pokemon, parent, false));
            itemView.setOnClickListener(this);

            mNameTextView = (TextView)
                    itemView.findViewById(R.id.pokemon_name);
            mDateTextView = (TextView)
                    itemView.findViewById(R.id.pokemon_date);
        }

        @Override
        public void onClick(View view) {
            Intent intent = PokeActivity.newIntent(getActivity(),mPokemon.getmId());
            startActivity(intent);
        }
    }

    private class PokemonAdapter extends RecyclerView.Adapter<PokemonHolder> {
        private List<Pokemon> mPokemons;
        public PokemonAdapter(List<Pokemon> pokemons) {
            mPokemons = pokemons;
        }

        @NonNull
        @Override
        public PokemonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new PokemonHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull PokemonHolder holder, int position) {
            Pokemon pokemon = mPokemons.get(position);
            holder.bind(pokemon);
        }

        @Override
        public int getItemCount() {
            return mPokemons.size();
        }

        public void setPokemons(List<Pokemon> pokemons) {
            mPokemons = pokemons;
        }
    }
}
