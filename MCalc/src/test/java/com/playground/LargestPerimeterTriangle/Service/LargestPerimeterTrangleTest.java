package com.playground.LargestPerimeterTriangle.Service;

import com.playground.LargestPerimeterTriangle.Model.Triangle;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class LargestPerimeterTrangleTest {

    @Test
    @DisplayName("Should calculate perimeter for a valid triangle")
    void getLargestPerimeterSuccess() {
        LargestPerimeterTrangle largestPerimeterTrangle = new LargestPerimeterTrangle(new Triangle(2, 1, 2));
        assertEquals(5, largestPerimeterTrangle.getLargestPerimeter());
    }

    @Test
    @DisplayName("Should return 0 for an invalid triangle")
    void getLargestPerimeterFail() {
        LargestPerimeterTrangle largestPerimeterTrangle = new LargestPerimeterTrangle(new Triangle(1, 2, 1));
        assertEquals(0, largestPerimeterTrangle.getLargestPerimeter());
    }

    @Test
    @DisplayName("Should validate a proper triangle")
    void isValidSuccess() {
        LargestPerimeterTrangle largestPerimeterTrangle = new LargestPerimeterTrangle(new Triangle(2, 1, 2));
        assertTrue(largestPerimeterTrangle.isValid(2, 1, 2));
    }
    
    @ParameterizedTest
    @DisplayName("Should validate various valid triangles")
    @CsvSource({
        "3, 4, 5",     // Common right triangle
        "5, 5, 5",     // Equilateral triangle
        "5, 5, 8",     // Isosceles triangle
        "7, 10, 5"     // Scalene triangle
    })
    void shouldValidateValidTriangles(int a, int b, int c) {
        log.info("a: {}, b: {}, c: {}", a, b, c);
        LargestPerimeterTrangle largestPerimeterTrangle = new LargestPerimeterTrangle(new Triangle(a, b, c));
        assertTrue(largestPerimeterTrangle.isValid(a, b, c));
        assertEquals(a + b + c, largestPerimeterTrangle.getLargestPerimeter());
    }
    
    @ParameterizedTest
    @DisplayName("Should invalidate triangles that violate triangle inequality")
    @CsvSource({
        "1, 2, 3",     // a + b = c
        "1, 1, 3",     // a + b < c
        "3, 1, 1",     // b + c < a
        "1, 3, 1"      // a + c < b
    })
    void shouldInvalidateTrianglesViolatingInequality(int a, int b, int c) {
        log.info("a: {}, b: {}, c: {}", a, b, c);
        LargestPerimeterTrangle largestPerimeterTrangle = new LargestPerimeterTrangle(new Triangle(a, b, c));
        assertFalse(largestPerimeterTrangle.isValid(a, b, c));
        assertEquals(0, largestPerimeterTrangle.getLargestPerimeter());
    }
    
    @ParameterizedTest
    @DisplayName("Should invalidate triangles with non-positive sides")
    @CsvSource({
        "0, 2, 2",     // a = 0
        "2, 0, 2",     // b = 0
        "2, 2, 0",     // c = 0
        "-1, 2, 2",    // a < 0
        "2, -1, 2",    // b < 0
        "2, 2, -1"     // c < 0
    })
    void shouldInvalidateTrianglesWithNonPositiveSides(int a, int b, int c) {
        LargestPerimeterTrangle largestPerimeterTrangle = new LargestPerimeterTrangle(new Triangle(a, b, c));
        assertFalse(largestPerimeterTrangle.isValid(a, b, c));
        assertEquals(0, largestPerimeterTrangle.getLargestPerimeter());
    }
    
    @Test
    @DisplayName("Should calculate perimeter for a triangle with large sides")
    void shouldCalculatePerimeterForLargeTriangle() {
        int a = 1000000;
        int b = 1000000;
        int c = 1000000;
        LargestPerimeterTrangle largestPerimeterTrangle = new LargestPerimeterTrangle(new Triangle(a, b, c));
        assertEquals(3000000, largestPerimeterTrangle.getLargestPerimeter());
    }
    
    @Test
    @DisplayName("Should validate triangle with minimal valid sides")
    void shouldValidateMinimalTriangle() {
        LargestPerimeterTrangle largestPerimeterTrangle = new LargestPerimeterTrangle(new Triangle(1, 1, 1));
        assertTrue(largestPerimeterTrangle.isValid(1, 1, 1));
        assertEquals(3, largestPerimeterTrangle.getLargestPerimeter());
    }
}