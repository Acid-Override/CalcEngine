package com.playground.LargestPerimeterTriangle.Service;

import com.playground.LargestPerimeterTriangle.Model.Triangle;
import com.playground.LargestPerimeterTriangle.Strategy.SideA;
import com.playground.LargestPerimeterTriangle.Strategy.SideB;
import com.playground.LargestPerimeterTriangle.Strategy.SideC;
import com.playground.LargestPerimeterTriangle.api.TriangleStrategy;
import com.playground.LargestPerimeterTriangle.api.TriangleValidator;

import java.util.ArrayList;
import java.util.List;

public class LargestPerimeterTrangle implements TriangleValidator {
    private final List<TriangleStrategy> triangleStrategies = new ArrayList<>();
    private final Triangle triangle;

    {
        triangleStrategies.add(new SideA());
        triangleStrategies.add(new SideB());
        triangleStrategies.add(new SideC());
    }

    public LargestPerimeterTrangle(Triangle triangle) {
        this.triangle = triangle;
    }

    public int getLargestPerimeter() {
        if (!isValid(triangle.getA(), triangle.getB(), triangle.getC())) {
            return 0;
        }
        return triangle.getA() + triangle.getB() + triangle.getC();
    }

    @Override
    public boolean isValid(int x, int y, int z) {
        return verify(x, y, z);
    }

    private boolean verify(int x, int y, int z) {
        return triangleStrategies
                .stream()
                .map(triangleStrategy -> triangleStrategy.isVerified(x, y, z))
                .reduce(true, Boolean::logicalAnd);
    }
}
