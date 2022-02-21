package guru.springframework.sfgpetclinic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInfo;

@Tag("model")
public interface ModelRepeatedTests {

    // tests that are repeated work properly with this method but those that are not repeatedTests
    // will throw an exception
    @BeforeEach
    default void beforeEachConsoleOutputer(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        System.out.println("Running Test - " + testInfo.getDisplayName() + " - "
                +repetitionInfo.getCurrentRepetition() + " | " +  repetitionInfo.getTotalRepetitions()
        );
    }
}
