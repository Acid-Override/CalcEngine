package com.playground.EnumStudy;

public enum Pokemon {
    PIKACHU("Thunder Shock"),
    CHARMANDER("Scratch"),
    EEVEE("Sand Attach");

    private final String attack;

    Pokemon(String attack) {
        this.attack = attack;
    }

    public String getAttack() {
        System.out.println(attack);
        return attack;
    }
}
