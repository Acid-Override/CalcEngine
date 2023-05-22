package kata.kyu6;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GrabScrabTest {

    @Test
    void grabScrab() {
        List<String> result = GrabScrab.grabScrab("ortsp", List.of("sport", "parrot", "ports", "matey"));
        System.out.println(result.toString());
    }
}