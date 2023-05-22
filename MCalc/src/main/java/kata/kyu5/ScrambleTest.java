package kata.kyu5;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static kata.kyu5.Scramble.scramble;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ScrambleTest {

    //Scramble scram = new Scramble();

    @BeforeEach
    void setUp() {
        System.out.println("setting up test");
    }

    //@Test
//    void scrambleTestWithDifferentWords() {
//        scram.setStr1("hello");
//        scram.setStr2("world");
//        boolean result = scram.scramble();
//        assertEquals(result, false);
//    }
//    @Test
//    void scrambleTestWithMatchingLetters(){
//        scram.setStr1("hello");
//        scram.setStr2("olleh");
//        boolean result = scram.scramble();
//        assertEquals(result, true);
//    }
    @Test
    void codeWarsTests() {
        assertEquals(scramble("rkqodlw", "world"),true);
        assertEquals(scramble("cedewaraaossoqqyt", "codewars"),true);
        assertEquals(scramble("katas", "steak"),false);
        assertEquals(scramble("scriptjavx", "javascript"),false);
        assertEquals(scramble("scriptingjava", "javascript"),true);
        assertEquals(scramble("scriptsjava", "javascripts"),true);
        assertEquals(scramble("javscripts", "javascript"),false);
        assertEquals(scramble("jscripts", "javascript"),false);
        assertEquals(scramble("aabbcamaomsccdd", "commas"),true);
        assertEquals(scramble("commas", "commas"),true);
        assertEquals(scramble("sammoc", "commas"),false);
    }

    private static void testing(boolean actual, boolean expected) {
        assertEquals(expected, actual);
    }

    @Test
    public void test() {
        System.out.println("Fixed Tests scramble");
        testing(Scramble.scrambleV2("rkqodlw","world"), true);
        testing(Scramble.scrambleV2("cedewaraaossoqqyt","codewars"),true);
        testing(Scramble.scrambleV2("katas","steak"),false);
        testing(Scramble.scrambleV2("scriptjavx","javascript"),false);
        testing(Scramble.scrambleV2("scriptingjava","javascript"),true);
        testing(Scramble.scrambleV2("scriptsjava","javascripts"),true);
        testing(Scramble.scrambleV2("javscripts","javascript"),false);
        testing(Scramble.scrambleV2("aabbcamaomsccdd","commas"),true);
        testing(Scramble.scrambleV2("commas","commas"),true);
        testing(Scramble.scrambleV2("sammoc","commas"),true);
    }

    @Test
    public void deletemeTest () {
        Scramble.test();
    }

    @AfterEach
    void afterEachTestRun() {
        System.out.println("After Each Test Run");
    }

}