package com.example.poketracker;

import java.util.Date;
import java.util.UUID;

public class Pokemon {

    private UUID mId;
    private String mName;
    private Date mDate;
    private boolean mShiny;
    private boolean mRegional;
    private boolean mIV;
    private boolean mLucky;

    public Pokemon() {
        this(UUID.randomUUID());
    }

    public Pokemon(UUID id) {
        mId = id;
        mDate = new Date();
    }

    public UUID getmId() {
        return mId;
    }

    public String getmName() {
        return mName;
    }

    public Date getmDate() {
        return mDate;
    }

    public boolean ismShiny() {
        return mShiny;
    }

    public boolean ismRegional() {
        return mRegional;
    }

    public boolean ismIV() {
        return mIV;
    }

    public boolean ismLucky() {
        return mLucky;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public void setmShiny(boolean mShiny) {
        this.mShiny = mShiny;
    }

    public void setmRegional(boolean mRegional) {
        this.mRegional = mRegional;
    }

    public void setmIV(boolean mIV) {
        this.mIV = mIV;
    }

    public void setmLucky(boolean mLucky) {
        this.mLucky = mLucky;
    }
}
