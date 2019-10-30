package com.example.poketracker;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PokemonListFragment extends Fragment {

    private RecyclerView mPokemonRecyclerView;
    private PokemonAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pokemon_list, container, false);

        mPokemonRecyclerView = (RecyclerView) view.findViewById(R.id.pokemon_recycler_view);
        mPokemonRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI() {
        PokemonLab pokemonLab = PokemonLab.get(getActivity());
        List<Pokemon> pokemons = pokemonLab.getmPokemons();


        mAdapter = new PokemonAdapter(pokemons);
        mPokemonRecyclerView.setAdapter(mAdapter);
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
            Toast.makeText(getActivity(), mPokemon.getmName() + " clicked!", Toast.LENGTH_SHORT).show();
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
    }
}
