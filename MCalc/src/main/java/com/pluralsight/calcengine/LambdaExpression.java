//package com.pluralsight.calcengine;
//
//import java.util.*;
//import java.util.function.IntPredicate;
//import java.util.stream.IntStream;
//
//public class LambdaExpression {
//
//    private Integer testCases;
//    private Integer conditionToTest;
//    private Integer numberToCheck;
//    private HashMap hashmap = new HashMap();
//    private List<String> result = new ArrayList<>();
//
//
//    public LambdaExpression(Integer testCases, Integer conditionToTest, Integer numberToCheck) {
//        this.testCases = testCases;
//        this.conditionToTest = conditionToTest;
//        this.numberToCheck = numberToCheck;
//    }
//
//    public LambdaExpression() {
//
//    }
//
//    public static void main(String... args) {
//        LambdaExpression lambdaExpression = new LambdaExpression();
//        lambdaExpression.Solution();
//    }
//
//    public void Solution () {
//        log.info("Please enter the number of test cases :");
//        Scanner sc = new Scanner(System.in);
//        testCases = sc.nextInt();
//
//        for ( int i = 0; i < testCases; i++) {
//            Scanner scc = new Scanner(System.in);
//            log.info("Enter a Test case (1 - 3) and a number to test: ");
//            log.info("Test case " + i + " : ");
//            String userInput = scc.nextLine();
//
//            String[] args = userInput.split(" ");
//            int condition = Integer.parseInt(args[0]);
//            Integer testNum = Integer.parseInt(args[1]);
//            int[] intArray = {condition, testNum};
//
//            //hashmap.put(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
//            hashmap.put(intArray, Boolean.valueOf("true"));
//
//            switch (condition){
//                //case 1 : result.add(isOdd(testNum)); break;
//                case 1 : result.add(isOdd(testNum)); break;
//                case 2 : result.add(isPrime(testNum)); break;
//                case 3 : result.add(isPalindrome()); break;
//            }
//        }
//        log.info(hashmap.values());
//    }
//
//    private String isPalindrome(int number) {
//        return String.valueOf(number).equals()
//
//        if( () -> String.valueOf(number).equals(new StringBuilder(Integer.toString(number)).reverse().toString())) {
//            return "Palindrome";
//        } else {
//            return "Non-Palindrome";
//        }
//    }
//
//    private String isPrime(int number) {
//        if (number > 1 && IntStream.range(2, number).noneMatch(i -> number%i==0)) {
//            return "Prime";
//        } else {
//            return "Composite";
//        }
//    }
//
//    private String isOdd(int testNum) {
//        () -> { return testNum % 2 == 0 ? "Even" : "Odd";}
//        return testNum -> testNum % 2 == 0 ? "Even" : "Odd";
//
//    }
//
//
//    public Integer getTestCases() {
//        return testCases;
//    }
//    public void setTestCases(Integer testCases) {
//        this.testCases = testCases;
//    }
//
//    public Integer getConditionToTest() {
//        return conditionToTest;
//    }
//    public void setConditionToTest(Integer conditionToTest) {
//        this.conditionToTest = conditionToTest;
//    }
//
//    public Integer getNumberToCheck() {
//        return numberToCheck;
//    }
//    public void setNumberToCheck(Integer numberToCheck) {
//        this.numberToCheck = numberToCheck;
//    }
//}
