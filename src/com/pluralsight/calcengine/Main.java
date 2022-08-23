package com.pluralsight.calcengine;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //performCalculations();
        //int result = BinaryArrayToNumber.convertBinaryArrayToNumber(new int[] {0, 0, 0, 1});

        //iterate enum
        for ( IterateAClassValues iterateAClassValues : IterateAClassValues.values()) {
            System.out.println(iterateAClassValues);
        }

        //StaticInitializerTest st = new StaticInitializerTest();
        ExtendedStaticInitializerTest est = new ExtendedStaticInitializerTest();
        est.setSomeFieldValue(42);
        System.out.println("GetSomeFieldValue " + est.getSomeFieldValue());
        est.someFunction();

        Object o = new ExtendedStaticInitializerTest();
        if ( o instanceof StaticInitializerTest) {
            ExtendedStaticInitializerTest aext = (ExtendedStaticInitializerTest) o;
            aext.someFunction();
        }


        //Adder add1 = new Adder();
        doCalculation(new Divider(), 100.0d, 50.0d);
        doCalculation(new Adder(), 25.0d, 92.0d);
        doCalculation(new Subtractor(), 225.0d, 17.0d);
        doCalculation(new Multiplier(), 11.0d, 3.0d);

    }

    static void doCalculation(CalculateBase calculation, double leftVal, double rightVal) {

        calculation.setLeftVal(leftVal);
        calculation.setRightVal(rightVal);
        calculation.calculate();
        System.out.println(calculation.getResult());

    }

    static void performCalculations() {
        //[2,7,11,15], target = 9
        int[] test = {2, 4, 11, 3};
        int tar = 6;

        //twoSum(test, tar);


        System.out.println("Please enter a command or nothing for example calc. (ex: Multiply two nine or interactive or binary search)");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        String[] args = userInput.split(" ");


        for (String arg : args) {
            System.out.println(arg);
        }

        if (args[0].equals("")) {

//            Calculator test1 = new Calculator('+', 20, 20);
//            test1.calcResult();
//            test1.printResult();
//            test1.setAllInstances();
//            test1.setAllInstances();
//            Calculator test2 = new Calculator('-', 20, 10);
//            test2.getAllInstances();

            MathEquation[] equations = new MathEquation[4];
            equations[0] = new MathEquation('d', 100.0d, 50.0d);
            equations[1] = new MathEquation('a', 25.0d, 92.0d);
            equations[2] = new MathEquation('s', 225.0d, 17.0d);
            equations[3] = new MathEquation('m', 11.0d, 3.0d);

            for (MathEquation equation : equations) {
                equation.execute();
                System.out.println("Result = " + equation.getResult());
                System.out.println("Average Total = " + MathEquation.getAvgResult());
            }

            MathEquation equationOverload = new MathEquation('d');
            double leftDouble = 9.0d;
            double rightDouble = 4.0d;
            equationOverload.execute(leftDouble, rightDouble);

            System.out.println("Overload results = " + equationOverload.getResult());

            System.out.println("Using Int conversion with Overlaod");

            int leftInt = 9;
            int rightInt = 4;
            //MathEquation equationConversion = new MathEquation('a');
            //equationConversion.execute(leftInt, rightInt);
            equationOverload.execute(leftInt, rightInt);
            System.out.println("Conversion = " + equationOverload.getResult());


        } else if (args.length == 3) {
            //double result = execute ( args[0].charAt(0), Double.parseDouble(args[1]), Double.parseDouble(args[2]));
            double result = handleCommandLine(args);
            System.out.println(result);
        } else if (args.length == 1 && args[0].equals("interactive")) {
            executeInteractively();
        } else if (args.length == 1 && args[0].equals("date")) {
            dateInteractive();

        } else if (args.length == 2 && args[0].equals("binary")) {
            binaryInteractive();
        } else {
            System.out.println("Please provide an operation code and two numeric values.");
        }


        //Factorial.factorial();
        //double[] totalAmount = CalculateInterest.produceInterestHistory(100d, .06d, 10);
//        float total = (totalAmount[totalAmount.length - 1]).setScale(2, RoundingMode.CEILING);
        //System.out.println("Total amount is : " + totalAmount[totalAmount.length - 1]);

    }

    //This is no longer needed since Constructor will now be doing the work.

//    private static MathEquation create(double leftVal, double rightVal, char opCode) {
//        MathEquation equation = new MathEquation();  // Uses default constructor
////        equation.leftVal = leftVal;
////        equation.rightVal = rightVal;
////        equation.opCode = opCode;
//        equation.setLeftVal(leftVal);
//        equation.setRightVal(rightVal);
//        equation.setOpCode(opCode);
//        return equation;
//    }

    private static void dateInteractive() {
        LocalDate today = LocalDate.now();
        System.out.println(today);

        DateTimeFormatter usDateFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        System.out.println(today.format(usDateFormat));

        System.out.println("Please enter a date: ");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        LocalDate theDate = LocalDate.parse(userInput, usDateFormat);
        System.out.println("theDate :" + theDate);
    }

    private static void binaryInteractive() {
        System.out.println("Please enter start, end and target and I will return the number of hops");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        String[] parts = userInput.split(" ");
        int start = Integer.parseInt(parts[0]);
        int end = Integer.parseInt(parts[1]);
        int target = Integer.parseInt(parts[2]);

        int total = end - start + 1;
        int[] arr = new int[total];
        for (int i = 0; i < total; i++) {
            arr[i] = start++;
        }
        int searchResult = BinarySearch.search(arr, target);
        System.out.println(searchResult);
        int result = BinarySearch.recursiveSearch(arr, target, 0, arr.length - 1);
        System.out.println("Recursive Binary Search : " + result);
    }

    static void executeInteractively() {
        System.out.println("Please enter a modifier and two numbers: ");
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine(); //gather all input from user until they hit enter
        String[] parts = userInput.split(" ");
        performOperation(parts);
        //System.out.println(parts[0] + " " + parts[1] + " & " + parts[2] + " = " + result);
    }

    private static void performOperation(String[] parts) {
        char opCode = opCodeFromString(parts[0]);

        if (opCode == 'w') {
            handleWhen(parts);
        } else {
            double leftVal = valueFromString(parts[1]);
            double rightVal = valueFromString(parts[2]);
            double result = execute(opCode, leftVal, rightVal);
            displayResult(opCode, leftVal, rightVal, result);
        }
    }

    private static void handleWhen(String[] parts) {

        LocalDate startDate = LocalDate.parse(parts[1]);
        long daysToAdd = (long) valueFromString(parts[2]);
        LocalDate newDate = startDate.plusDays(daysToAdd);

        //String formatting
        String output = String.format("%s plus %d days is %s", startDate, daysToAdd, newDate);
        System.out.println(output);

    }

    private static void displayResult(char opCode, double leftVal, double rightVal, double result) {
        char symbol = symbolFromOpCode(opCode);
//        StringBuilder sb = new StringBuilder();
//        sb.append("The result of ");
//        sb.append(leftVal);
//        sb.append(symbol);
//        sb.append(rightVal);
//        sb.append(" = ");
//        sb.append(result);
//        String printOutResult = sb.toString();

        String printOutResult = String.format("The result of %+.1f %c %+.1f = %+.1f", leftVal, symbol, rightVal, result);

        System.out.println(printOutResult);
    }

    private static char symbolFromOpCode(char opCode) {
        char[] opCodes = {'a', 's', 'm', 'd'};
        char[] symbols = {'+', '-', '*', '/'};
        char symbol = ' ';

        for (int i = 0; i < opCodes.length; i++) {
            if (opCode == opCodes[i]) {
                symbol = symbols[i];
                break;
            }
        }
        return symbol;
    }


    private static double handleCommandLine(String[] args) {
        char opCode = args[0].charAt(0);
        double leftVal = Double.parseDouble(args[1]);
        double rightVal = Double.parseDouble(args[2]);
        double result = execute(opCode, leftVal, rightVal);
        return result;
    }

    static double execute(char opCode, double leftVal, double rightVal) {
        double result = 0.0;
        switch (opCode) {
            case 'a':
                result = leftVal + rightVal;
                break;
            case 's':
                result = leftVal - rightVal;
                break;
            case 'm':
                result = leftVal * rightVal;
                break;
            case 'd':
                result = rightVal != 0 ? leftVal / rightVal : 0.0d;
                break;
            default:
                System.out.println("Invalid letter: " + opCode);
        }
        return result;
    }

    static char opCodeFromString(String operationName) {
        return operationName.toLowerCase().charAt(0);
    }

    static double valueFromString(String str) {
        String[] stringOfValues = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

        double value = -1d;
        for (int i = 0; i < stringOfValues.length; i++) {
            if (str.equals(stringOfValues[i])) {
                value = i;
                break;
            }
        }
        if (value == -1d) {
            value = Double.parseDouble(str);
        }
        return value;
    }


}