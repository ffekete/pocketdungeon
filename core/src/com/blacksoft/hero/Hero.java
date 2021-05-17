package com.blacksoft.hero;

import com.blacksoft.creature.Creature;

public abstract class Hero extends Creature {

    public Party party;

    public Hero(Party party) {
        this.party = party;
    }

    @Override
    public void die() {
        this.party.heroes.remove(this);
    }
}
