package com.DailyByte.StudentGrades;

import java.util.*;
//import java.util.HashSet;
import java.util.Map;

public class StudentGrades {

    Map<Integer, List<Integer>> hm = new HashMap<Integer, List<Integer>>();

    public Map<Integer, List<Integer>> averageGradesPerStudent(){
        hm.putIfAbsent(1, new ArrayList<>());
        hm.get(1).add(100);
        hm.putIfAbsent(2, new ArrayList<>());
        hm.get(2).add(95);
        hm.putIfAbsent(3, new ArrayList<>());
        hm.get(3).add(99);
        hm.get(1).add(88);
        hm.get(2).add(94);
        return hm;
    }

}
