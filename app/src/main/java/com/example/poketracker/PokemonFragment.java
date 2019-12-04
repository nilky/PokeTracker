package com.example.poketracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.Date;
import java.util.UUID;

public class PokemonFragment extends Fragment {

    private static final String ARG_POKE_ID = "poke_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0;

    private Pokemon mPokemon;
    private EditText mNameField;
    private Button mDateButton;
    private CheckBox mShinyCheckBox;
    private CheckBox mRegionalCheckBox;
    private CheckBox mIVCheckBox;
    private CheckBox mLuckyCheckBox;


    public static PokemonFragment newInstance(UUID pokeId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_POKE_ID, pokeId);

        PokemonFragment fragment = new PokemonFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        UUID pokeId = (UUID)
    getArguments().getSerializable(ARG_POKE_ID);
        mPokemon = PokemonLab.get(getActivity()).getPokemon(pokeId);
    }

    @Override
    public void onPause() {
        super.onPause();

        PokemonLab.get(getActivity())
                .updatePokemon(mPokemon);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pokemon, container, false);
        mNameField = (EditText)
                v.findViewById(R.id.pokemon_name);
        mNameField.setText(mPokemon.getmName());
        mNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPokemon.setmName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mDateButton = (Button)
                v.findViewById(R.id.pokemon_date);
        updateDate();
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mPokemon.getmDate());
                dialog.setTargetFragment(PokemonFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });

        mShinyCheckBox = (CheckBox)
                v.findViewById(R.id.pokemon_shiny);
        mShinyCheckBox.setChecked(mPokemon.ismShiny());
        mShinyCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)  {
                mPokemon.setmShiny(isChecked);
            }
        });

        mRegionalCheckBox = (CheckBox)
                v.findViewById(R.id.pokemon_regional);
        mRegionalCheckBox.setChecked(mPokemon.ismRegional());
        mRegionalCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)  {
                mPokemon.setmRegional(isChecked);
            }
        });

        mIVCheckBox = (CheckBox)
                v.findViewById(R.id.pokemon_IV);
        mIVCheckBox.setChecked(mPokemon.ismIV());
        mIVCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)  {
                mPokemon.setmIV(isChecked);
            }
        });

        mLuckyCheckBox = (CheckBox)
                v.findViewById(R.id.pokemon_lucky);
        mLuckyCheckBox.setChecked(mPokemon.ismLucky());
        mLuckyCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)  {
                mPokemon.setmLucky(isChecked);
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mPokemon.setmDate(date);
            updateDate();
        }
    }

    private void updateDate() {
        mDateButton.setText(mPokemon.getmDate().toString());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_pokemon, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.delete_pokemon:
                UUID pokemonId = (UUID) getArguments().getSerializable(ARG_POKE_ID);
                PokemonLab pokemonLab = PokemonLab.get(getActivity());
                mPokemon = pokemonLab.getPokemon(pokemonId);
                pokemonLab.deletePokemon(mPokemon);
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
