package com.playground.Inheritance.Animals;

import com.playground.Inheritance.Model.Animal;
import lombok.*;

import java.util.logging.Logger;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class Dog extends Animal {


    Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Override
    public int move() {
        return 0;
    }

    @Override
    public void callSound() {
        log.info("Woof WOof");
    }

    @Override
    public int currentBodyTemp() {
        return 0;
    }

    @Override
    public boolean isWarmBlooded() {
        return false;
    }
}
