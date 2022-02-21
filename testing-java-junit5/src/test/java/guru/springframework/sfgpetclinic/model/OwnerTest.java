package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.CustomArgsProvider;
import guru.springframework.sfgpetclinic.ModelTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class OwnerTest implements ModelTests {

    @Test
    void dependentAssertions() {

        Owner owner = new Owner(1L, "Joe", "Buck");
        owner.setCity("Galati");
        owner.setTelephone("1231231234");

        assertAll("Properties Test",
                () -> assertAll("Person Properties",
                        () -> assertEquals("Joe", owner.getFirstName(), "First Name Did not Match"),
                        () -> assertEquals("Buck", owner.getLastName())
                ),

                () -> assertAll("Owner Properties",
                        () -> assertEquals("Galati", owner.getCity(), "City Did Not Match"),
                        () -> assertEquals("1231231234", owner.getTelephone())
                )
        );

        // Hamcrest
        assertThat(owner.getCity(), is("Galati"));

    }


    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @ValueSource(strings = {"Spring", "Framework", "Master"})
    @DisplayName("Value Source Test")
    void testValueSource(String val) {

        System.out.println(val);

    }

    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @EnumSource(OwnerType.class)
    @DisplayName("Enum Source Test")
    void enumTest(OwnerType ownerType) {
        System.out.println(ownerType);
    }


    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @CsvSource({
            "B, 1, 1",
            "GL, 2, 2",
            "BV, 3, 1",
    })
    @DisplayName("CSV Input Test")
    void csvInputTest(String stateName, int val1, int val2) {
        System.out.println(stateName + " = " + val1 + ":" + val2);
    }


    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @CsvFileSource(resources = "/input.csv", numLinesToSkip = 1) // to look into root context
    @DisplayName("CSV From File Test")
    void csvFromFileTest(String stateName, int val1, int val2) {
        System.out.println(stateName + " = " + val1 + ":" + val2);
    }

    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @DisplayName("Method Provider Test")
    @MethodSource("getArgs")
    void fromMethodTests(String stateName, int val1, int val2) {
        System.out.println(stateName + " = " + val1 + ":" + val2);
    }

    private static Stream<Arguments> getArgs() {
        return Stream.of(
                Arguments.of("B", 2, 421),
                Arguments.of("GL", 123, 321),
                Arguments.of("BV", 999, 999)
        );
    }

    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @DisplayName("Custom Provider Test")
    @ArgumentsSource(CustomArgsProvider.class)
    void fromCustomProviderTests(String stateName, int val1, int val2) {
        System.out.println(stateName + " = " + val1 + ":" + val2);
    }

}