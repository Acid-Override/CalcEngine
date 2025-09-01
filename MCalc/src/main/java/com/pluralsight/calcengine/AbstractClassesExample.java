package com.pluralsight.calcengine;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
abstract class AbstractClassesExample {
    public String str = "Abstract Class String";

    public AbstractClassesExample() {}
    public AbstractClassesExample (int abstractClassInt) {
        log.info("AbstractClassInt={}", abstractClassInt);
    }

    public void method () {
        log.info("I am the abstract class method");
    }

}

@Slf4j
class First extends AbstractClassesExample {
    @Setter
    @Getter
    private int number;
    private final static String firstStr = "Hello World";
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
        log.info("Method in First object which overrides Abstract Class");
        log.info(str);
        log.info(firstStr);

        try {
            log.info(String.valueOf(numArray[10]));
        } catch (Exception e) {
            log.info(String.valueOf(e));
        } finally {
            log.info("Try catch is over");
        }
        log.info(String.valueOf(numArray[10]));
    }


}


