package com.playground.LargestPerimeterTriangle.Strategy;

import com.playground.LargestPerimeterTriangle.api.TriangleStrategy;

public class SideA implements TriangleStrategy {
    @Override
    public boolean isVerified(int x, int y, int z) {
        return x + y > z && x > 0;
    }
}
