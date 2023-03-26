package com.playground.EnumStudy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class PokemonTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAttack() {
    }

    @Test
    void listOfEnumsTest() {
        List<Pokemon> myPokemons = List.of(Pokemon.PIKACHU, Pokemon.EEVEE);
        myPokemons.forEach(Pokemon::getAttack);
    }

    @Test
    void values() {
    }

    @Test
    void valueOf() {
    }
}