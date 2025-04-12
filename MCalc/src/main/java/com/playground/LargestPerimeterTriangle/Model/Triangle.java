package com.playground.LargestPerimeterTriangle.Model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Triangle {
    private final int a;
    private final int b;
    private final int c;

    public Triangle(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}
