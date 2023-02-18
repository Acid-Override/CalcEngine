package com.playground.Inheritance.Model;

import com.playground.Inheritance.Interface.CanMove;
import com.playground.Inheritance.Interface.MakeSound;
import com.playground.Inheritance.Interface.WarmBlooded;

public abstract class Animal implements CanMove, MakeSound, WarmBlooded {


    public abstract int move();
    public abstract void callSound();
    public abstract int currentBodyTemp();
    public abstract boolean isWarmBlooded();


}
