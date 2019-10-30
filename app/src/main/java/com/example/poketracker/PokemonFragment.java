package com.example.poketracker;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PokemonFragment extends Fragment {
    private Pokemon mPokemon;
    private EditText mNameField;
    private Button mDateButton;
    private CheckBox mShinyCheckBox;
    private CheckBox mRegionalCheckBox;
    private CheckBox mIVCheckBox;
    private CheckBox mLuckyCheckBox;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPokemon = new Pokemon();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pokemon, container, false);
        mNameField = (EditText)
                v.findViewById(R.id.pokemon_name);
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
        mDateButton.setText(mPokemon.getmDate().toString());
        mDateButton.setEnabled(false);

        mShinyCheckBox = (CheckBox)
                v.findViewById(R.id.pokemon_shiny);
        mShinyCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)  {
                mPokemon.setmShiny(isChecked);
            }
        });

        mRegionalCheckBox = (CheckBox)
                v.findViewById(R.id.pokemon_regional);
        mRegionalCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)  {
                mPokemon.setmRegional(isChecked);
            }
        });

        mIVCheckBox = (CheckBox)
                v.findViewById(R.id.pokemon_IV);
        mIVCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)  {
                mPokemon.setmIV(isChecked);
            }
        });

        mLuckyCheckBox = (CheckBox)
                v.findViewById(R.id.pokemon_lucky);
        mLuckyCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)  {
                mPokemon.setmLucky(isChecked);
            }
        });

        return v;
    }
}
