package com.playground.Inheritance.Dinosaurs;

import com.playground.Inheritance.Model.Dinosaur;
import com.playground.Inheritance.Model.Size;
import lombok.*;

import java.util.logging.Logger;

@Getter @Setter
@NoArgsConstructor
@ToString
public class T_REX extends Dinosaur {
    private Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public T_REX(String name, Size size) {
        super(name, size);
    }

    @Override
    public int move() {
        return 10;
    }

    @Override
    public void callSound() {
        log.info("Roooooooaaaaaaaarrr");
    }

    @Override
    public int currentBodyTemp() {
        return 50;
    }

    @Override
    public boolean isWarmBlooded() {
        return false;
    }
}
