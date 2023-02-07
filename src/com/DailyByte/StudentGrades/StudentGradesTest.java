package com.DailyByte.StudentGrades;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StudentGradesTest {

    @Test
    void averageGradesPerStudentInitTest() {
        StudentGrades sg = new StudentGrades();
        Map<Integer, List<Integer>> hm = sg.averageGradesPerStudent();
        for (Map.Entry<Integer, List<Integer>> me : hm.entrySet()) {
            System.out.println(me.getKey() + ":" + me.getValue().stream().mapToInt(Integer::intValue).sum());
        }

    }
}