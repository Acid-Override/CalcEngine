package com.pluralsight.calcengine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.DoubleToIntFunction;

public class ScratchPad {

//    public String getNameQuick() throws IOException {
//        final BufferedReader r = new BufferedReader(
//                new FileReader("saved.name"));
//        final String name = r.readLine();
//        r.flush();
//        return name;
//    }

    private int savingsInCents;

    private static class ConvertToCents {
        //static DoubleToIntFunction f = p -? p*100;
    }

//    public static void main ( String... currency) throws Exception {
//        ScratchPad creditUnion = new ScratchPad();
//        try{
//            creditUnion.savingsInCents = 100;
//            thow new IOException("Auto-pilot error");
//        } catch (Exception | ScratchPad e) {
//            thow e;
//        } catch (Exception a) {
//            trhow a;
//        }
//        ScheduledExecutorService
//    }

    public static void main(String... args) {
        Long a = null;
        Long b = 999999999l;

        System.out.println(Math.min(a, b));
    }
}
