package com.playground.LargestPerimeterTriangle.Strategy;

import com.playground.LargestPerimeterTriangle.api.TriangleStrategy;

public class SideB implements TriangleStrategy {
    @Override
    public boolean isVerified(int x, int y, int z) {
        return x + z > y && y > 0;
    }
}
