package com.DailyByte.CorrectCapitalization;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CorrectCapitalizationTest {

    private Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private CorrectCapitalization cc;


    @BeforeAll
    void init() {
        log.info("Startup");
        cc = new CorrectCapitalization();
    }

    @Test
    void correctAllCapitalizationTest() {
        assertTrue(cc.detectCapitalUse("HELLO"));
    }
    @Test
    void correctFirstLetterCapitalizationTest() {
        assertTrue(cc.detectCapitalUse("Supercalifragaligistic"));
    }
    @Test
    void incorrectCapitalLetterPlacement() {
        assertFalse(cc.detectCapitalUse("suPercalifragaligistic"));
    }
    @AfterAll
    void teardown() {
        log.info("TearDown");
    }

}