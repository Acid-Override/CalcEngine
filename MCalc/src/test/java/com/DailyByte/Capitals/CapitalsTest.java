package com.DailyByte.Capitals;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class CapitalsTest {

    @Test
    void sortCapitals() {
        Capitals capitals = new Capitals();
        String[] capitalsList = {"Hartford", "Springfield", "Burlington", "Springfield", "Springfield", "Hartford", "hartford"};
        capitals.sortCapitals(capitalsList);
        Integer total =Capitals.getMap().values().stream().reduce(0, Integer::sum);
        log.info("Map : {}", Capitals.getMap());
        log.info("Total : {}", total);
        Integer error = Capitals.getError().values().stream().reduce(0, Integer::sum);
        log.error("Error : {}", Capitals.getError());
        log.info("Error : {}", error);
    }

    @Test
    void sortCapitalsWithTwoWordCapital() {
        Capitals capitals = new Capitals();
        String[] capitalsList = {"Santa Fe", "santa Fe", "Springfield", "Burlington", "Springfield", "Springfield", "Hartford", "hartford"};
        capitals.sortCapitals(capitalsList);
        Integer total =Capitals.getMap().values().stream().reduce(0, Integer::sum);
        log.info("Map : {}", Capitals.getMap());
        log.info("Total : {}", total);
        Integer error = Capitals.getError().values().stream().reduce(0, Integer::sum);
        log.error("Error : {}", Capitals.getError());
        log.info("Error : {}", error);
    }
}