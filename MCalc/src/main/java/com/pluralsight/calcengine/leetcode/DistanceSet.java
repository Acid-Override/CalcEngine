package com.pluralsight.calcengine.leetcode;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.awt.Point;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

@Slf4j
public class DistanceSet {

   // [(1, 1), (-1, -1), (3, 4), (6, 1), (-1, -6), (-4, -3)]
    // return [(-1, -1), (1, 1)]

//    @Test public void firstTest() {
//        int[][] test = {{1,1}, {-1, -1}, {3, 4}, {6, 1}, {-4, -4}};
//        DistanceSet ds = new DistanceSet();
//        //ds.distanceSet(test);
//        ds.distanceSet(arrayOfPoints());
//    }

    private Point[] arrayOfPoints(){
        Point[] arrayList = new Point[]{
                new Point(1, 8),
                new Point(3, 4),
                new Point(6, 1),
                new Point(-4, -3)
        };
        log.info("Array of Points:{}", Arrays.stream(arrayList).toArray());
        return arrayList;
    }

    private void distanceSet (Point[] arrayOfPoints){
//
//        TestClass ts = new TestClass();
//        ts.setSomeVal(10);
//        ts.setSomeOtherVal("Hello World");
//        ts.method();

       for ( int i = 0; i < arrayOfPoints.length; i++ ) {
           for ( int j = i + 1; j < arrayOfPoints.length; j++ ) {
               Distance dis = new Distance(arrayOfPoints[i], arrayOfPoints[j]);
               Double result = dis.getDistance();
               log.info(String.valueOf(result));

           }
       }
    }


    @Getter
    @Setter
    private static class ShortestPairs {
        private Point pointOne;
        private Point pointTwo;
        private BigDecimal shortestDistance;

        private boolean isShortest () {

            return false;
        }
    }



    @Data
    private static class Distance {
        private final Point pointOne;
        private final Point pointTwo;
        private double distance;

        private double shortestDistance;

        public Distance( Point pt1, Point pt2) {
            pointOne = pt1;
            pointTwo = pt2;
        }



        private void distanceBetweenPoints(){
            BigDecimal distance = BigDecimal.valueOf(this.pointOne.distance(this.pointTwo));
            this.setDistance(distance.setScale(2, RoundingMode.HALF_EVEN).doubleValue());

        }
        private void shortestDistanceMethod () {
            if ( shortestDistance < distance ) {
                shortestDistance = distance;
            }
        }

        public double getDistance() {
            distanceBetweenPoints();
            return distance;
        }

    }










    private static class TestClass {
        private int someVal;
        private String someOtherVal;

        public void method () {
            log.info("I am some method");
            log.info(String.valueOf(someVal));
            log.info(someOtherVal);
        }

    }

}


