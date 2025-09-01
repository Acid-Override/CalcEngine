package kata.kyu6;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class GrabScrabTest {

    @Test
    void grabScrab() {
        List<String> result = GrabScrab.grabScrab("ortsp", List.of("sport", "parrot", "ports", "matey"));
        log.info("Result={}", result);
    }
}