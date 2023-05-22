package com.DailyByte.CharacterUpdate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterUpdateTest {

    CharacterUpdate cu;

    @BeforeEach
    void setUp() {
        cu = new CharacterUpdate();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void charUpInitial() {
        cu.setK(2);
        cu.setS("BBAA");
        cu.charUp();
    }
    @Test
    void solutionInit() {
        cu.setK(2);
        cu.setS("BBAA");
        cu.characterUpdate("BBAA", 2);
    }
}