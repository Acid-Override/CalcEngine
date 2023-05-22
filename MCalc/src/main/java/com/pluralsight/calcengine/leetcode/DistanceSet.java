package com.pluralsight.calcengine.leetcode;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class DistanceSet {

   // [(1, 1), (-1, -1), (3, 4), (6, 1), (-1, -6), (-4, -3)]
    // return [(-1, -1), (1, 1)]

    @Test public void firstTest() {
        int[][] test = {{1,1}, {-1, -1}, {3, 4}, {6, 1}, {-4, -4}};
        DistanceSet ds = new DistanceSet();
        //ds.distanceSet(test);
        ds.distanceSet(arrayOfPoints());
    }

    private Point[] arrayOfPoints(){
        Point[] arrayList = new Point[]{
                new Point(1, 8),
                new Point(3, 4),
                new Point(6, 1),
                new Point(-4, -3)
        };
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
               System.out.println(result);

           }
       }
    }


    private class ShortestPairs {
        private Point pointOne;
        private Point pointTwo;
        private BigDecimal shortestDistance;

        private boolean isShortest () {

            return false;
        }


        public BigDecimal getShortestDistance() {
            return shortestDistance;
        }

        public void setShortestDistance(BigDecimal shortestDistance) {
            this.shortestDistance = shortestDistance;
        }
    }



    private class Distance {
        private Point pointOne;
        private Point pointTwo;
        private double distance;

        private double shortestDistance;
        private Point[] pointsArray;

        public Distance( Point pt1, Point pt2) {
            pointOne = pt1;
            pointTwo = pt2;
        }



        private void distanceBetweenPoints(){
            BigDecimal distance = new BigDecimal(this.pointOne.distance(this.pointTwo));
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

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public void setPointsArray(Point[] pointsArray) {
            this.pointsArray = pointsArray;
        }
    } //class Distance










    private class TestClass {
        private int someVal;
        private String someOtherVal;

        public void method () {
            System.out.println("I am some method");
            System.out.println(someVal);
            System.out.println(someOtherVal);
        }

        public int getSomeVal() {
            return someVal;
        }

        public void setSomeVal(int someVal) {
            this.someVal = someVal;
        }

        public String getSomeOtherVal() {
            return someOtherVal;
        }

        public void setSomeOtherVal(String someOtherVal) {
            this.someOtherVal = someOtherVal;
        }

    } //private class TestClass

}


