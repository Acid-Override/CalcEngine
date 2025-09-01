package com.playground.EnumStudy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum Pokemon {
    PIKACHU("Thunder Shock"),
    CHARMANDER("Scratch"),
    EEVEE("Sand Attach");

    private final String attack;

    Pokemon(String attack) {
        this.attack = attack;
    }

    public String getAttack() {
        log.info(attack);
        return attack;
    }
}
