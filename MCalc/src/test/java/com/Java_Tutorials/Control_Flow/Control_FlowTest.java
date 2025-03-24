package com.Java_Tutorials.Control_Flow;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Control_FlowTest {

    Control_Flow cf;

    @BeforeAll
    void init() {
        cf = new Control_Flow();
    }

    @Test
    void breakWithLabelDemo() {
        cf.BreakWithLabelDemo();
        assertTrue(true);
    }
    
    @Test
    void searchSubStringLabelDemo() {
        String searchMe = "Look for a subString in me";
        String subString = "sub";
        cf.searchSubStringLabelDemo(searchMe, subString);
    }

    @Test
    void searchSubStringLabelDemoCompliant() {
        String searchMe = "Look for a subString in me";
        String subString = "sub";
        boolean result = cf.searchSubStringLabelDemoCompliant(searchMe, subString);
        assertTrue(result);
    }

    @Test
    void labelsShouldNotBeUsed() {
        cf.labelsShouldNotBeUsed();
    }

    @Test
    void labelsShouldNotBeUsedCompliant() {
        cf.labelsShouldNotBeUsedCompliant();}


}