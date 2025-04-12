package com.playground.LargestPerimeterTriangle.Strategy;

import com.playground.LargestPerimeterTriangle.api.TriangleStrategy;

public class SideC implements TriangleStrategy {
    @Override
    public boolean isVerified(int x, int y, int z) {
        return y + z > x && z > 0;
    }
}
