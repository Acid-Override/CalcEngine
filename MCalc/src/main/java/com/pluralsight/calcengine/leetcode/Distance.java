package com.pluralsight.calcengine.leetcode;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Distance {

//    @Test
//    public void firstTest() {
//        Distance dis = new Distance();
//        //dis.distance(1, 1, 0, 0);
//        //assertEquals(dis.distance(1, 1, 0, 0), 1.41);
//        Point point1 = new Point(1,1);
//        Point point2 = new Point(0, 0);
//        assertEquals(dis.distanceBetweenPoints(point1, point2), 1.41);
//    }

    private double distance(int x1, int y1, int x2, int y2) {
        //d=√((x_2-x_1)²+(y_2-y_1)²)
        double value = Math.sqrt((Math.pow(x2-x1, 2)) + Math.pow(y2-y1, 2) );
        String result = String.format("%.2f", value);
        return Double.parseDouble(result);
    }

    private double distanceBetweenPoints(Point pointOne, Point pointTwo){
        BigDecimal distance = new BigDecimal(pointOne.distance(pointTwo));
        return distance.setScale(2, RoundingMode.HALF_EVEN).doubleValue();

    }
}
