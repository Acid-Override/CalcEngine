package com.playground.Inheritance.Tests;

import com.playground.Inheritance.Animals.Dog;
import com.playground.Inheritance.Dinosaurs.T_REX;
import com.playground.Inheritance.Model.Animal;
import com.playground.Inheritance.Model.Dinosaur;
import com.playground.Inheritance.Model.Size;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static com.playground.Inheritance.Interface.CanMove.a;

public class SOLID_TESTS {

    Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Test
    void initTest() {
        Dinosaur sam = new T_REX("Sam", Size.Large);
        sam.callSound();
        log.info("Name : " + sam.getName() + " Size : " + sam.getSize());
        log.info("Current Body Temp : " + sam.currentBodyTemp());
        log.info("Interface (public, static, final) values " + a);
    }
    @Test
    void initDogTest() {
        Animal dog = new Dog();
        dog.callSound();
    }
}
