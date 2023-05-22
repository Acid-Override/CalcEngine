package com.playground.Inheritance.Model;

import com.playground.Inheritance.Interface.CanMove;
import com.playground.Inheritance.Interface.MakeSound;
import com.playground.Inheritance.Interface.WarmBlooded;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Slf4j
public abstract class Dinosaur implements CanMove, MakeSound, WarmBlooded {


    private String name;
    private Size size;

    {
        log.info("SLF4J");
    }

//    public abstract int move();
//    public abstract void callSound();
//    public abstract int currentBodyTemp();
//    public abstract boolean isWarmBlooded();


}
