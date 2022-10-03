package com.pluralsight.calcengine;

abstract class AbstractClassesExample {
    private int abstractClassInt;
    public String str = "Abstract Class String";

    public AbstractClassesExample() {}
    public AbstractClassesExample (int abstractClassInt) {
        this.abstractClassInt = abstractClassInt;
    }

    public void method () {
        System.out.println("I am the abstract class method");
    }
    public void setStr (String str) {
        this.str = str;
    }
    public String getStr () {
        return this.str;
    }
}

class First extends AbstractClassesExample {
    private int number;
    private String firstStr = "Hello World";
    int[] numArray = {1, 2, 3, 4, 5};

    public First(){
        super();
    }
    public First(int abstractClassInt) {
        super(abstractClassInt);
    }

    public static void main(String... args) {

        First first = new First();
        first.method();

    }

    @Override
    public void method() {
        System.out.println("Method in First object which overrides Abstract Class");
        System.out.println(str);
        System.out.println(firstStr);

        try {
            System.out.println(numArray[10]);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("Try catch is over");
        }
        System.out.println(numArray[10]);
    }




    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}


