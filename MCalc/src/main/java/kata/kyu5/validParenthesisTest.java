package kata.kyu5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// TODO: Replace examples and use TDD by writing your own tests


public class validParenthesisTest {

    @Test
    public void sampleTest() {
        // assertEquals("expected", "actual");
        assertEquals(true, validParentheses.solution( "()" ));
        assertEquals(false,validParentheses.solution( "())" ));
        assertEquals(true,validParentheses.solution( "32423(sgsdg)" ));
        assertEquals(false,validParentheses.solution( "(dsgdsg))2432" ));
        assertEquals(true,validParentheses.solution( "adasdasfa" ));
    }
}
