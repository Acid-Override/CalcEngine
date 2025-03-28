package com.DailyByte.NestedArray;

class NestedArray {

    /*
       * In this problem you have a nested array of strings.
       * Lists of strings can be nested to an arbitrary depth.
       * Print each element contained in the list, including all elements in nested lists.
       * Each element should be printed on a new line with the element's index at the beginning of the line.
       *
       * Ex:
       * 0.0: 1
       * 1: 2
       * 2: 3
       * 3.0: blue
       * 3.1: yellow
       * 3.2: red
       * 4: 5
       * 5.1: helicopter
       * 5.2: blimp
       * 5.3.1: biplane

       // 1 : blue
       //


       a.b.c.d
       * etc...
       */
    public static Object[] nestedArray = new Object[]{
            "1","2","3", new Object[]{"blue", "yellow", "red"},"5",
            new Object[]{"helicoptor", "blimp",
                    new Object[]{"biplane", "seaplane",
                            new Object[]{"B-52", "F-35", "A-12"}
                    }
            },
            "7"};


    public static void main(String[] args) {
        System.out.println("Build solution here");

        recursivePrinter(nestedArray, "");
    }

    public static void recursivePrinter (Object[] valueArray, String topIndex) {
        // iterate through the valueArray
        // if String
        // print value with topIndex and currentIndex.toString()
        // if NOT string
        // recursive call method with array of objects and currentIndex

        for (int i = 0; i < valueArray.length; i++) {
            if (valueArray[i] instanceof String) {
                if (!topIndex.isEmpty()) {
                    System.out.println((topIndex + "." + i) + ": " + valueArray[i]);
                } else {
                    System.out.println((i + "") + ": " + valueArray[i]);
                }

            } else {
                if (!topIndex.isEmpty()) {
                    recursivePrinter( (Object[]) valueArray[i], topIndex + "." + i);
                } else {
                    recursivePrinter((Object[]) valueArray[i], i + "");
                }
            }
        }
    }
}