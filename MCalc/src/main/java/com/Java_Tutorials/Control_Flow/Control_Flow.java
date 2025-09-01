package com.Java_Tutorials.Control_Flow;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Control_Flow {

    public void BreakWithLabelDemo() {
        int[][] arrOfInts = {
                {32, 87, 3, 589},
                {12, 1076, 2000, 8},
                {622, 127, 77, 955}
        };
        int searchFor = 12;
        boolean foundIt = false;

        search:
        for (int i = 0; i < arrOfInts.length; i++) {
            for (int j = 0; j < arrOfInts[i].length; j++) {
                if (arrOfInts[i][j] == searchFor) {
                    log.info("Found " + searchFor + " at " + i + ", " + j);
                    foundIt = true;
                    break search;
                }
            }
        }
        if (!foundIt) {
            log.info("Could not find " + searchFor);
        }
    }

    public void searchSubStringLabelDemo(String searchMe, String subString) {
        boolean foundIt = false;

        test:
        for (int i = 0; i < searchMe.length() - subString.length(); i++) {
            for (int j = 0; j < subString.length(); j++) {
                if (searchMe.charAt(i + j) != subString.charAt(j)) {
                    continue test;
                }
            }
            foundIt = true;
            break;
        }
        log.info("Found it? {}", foundIt ? "yes" : "no");
    }

    public boolean searchSubStringLabelDemoCompliant(String searchMe, String subString) {
        for (int i = 0; i < searchMe.length() - subString.length(); i++) {
            for (int j = 0; j < subString.length(); j++) {
                if (searchMe.charAt(i + j) != subString.charAt(j)) {
                    continue;
                }
            }
            log.info("Found it");
            return true;
        }
        log.info("Could not find it");
        return false;
    }

    public void labelsShouldNotBeUsed() {
        int[][] arrOfInts = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        test:
        for (int row = 0; row < arrOfInts.length; row++) {
            for (int col = 0; col < arrOfInts[row].length; col++) {
                if (col == row) {
                    continue test;
                }
                log.info("{}", arrOfInts[row][col]);
            }
        }
    }

    public void labelsShouldNotBeUsedCompliant() {
        int[][] arrOfInts = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        for (int row = 0; row < arrOfInts.length; row++) {
            for (int col = 0; col < row; col++) {
                log.info("{}", arrOfInts[row][col]);
            }
        }
    }















}
