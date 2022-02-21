package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.ModelTests;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest implements ModelTests {


    @Test
    void groupedAssertions() {
        // given
        Person person = new Person(1L, "Joe", "Buck");

        // then
        assertAll("Test Props Set", () -> assertEquals(person.getFirstName(), "Joe"), () -> assertEquals(person.getLastName(), "Buck"));

    }


    @Test
    void groupedAssertionsMsgs() {
        // given
        Person person = new Person(1L, "Joe", "Buck");

        // then
        assertAll("Test Props Set", () -> assertEquals(person.getFirstName(), "Joe", "Fist Name Failed"), () -> assertEquals(person.getLastName(), "Buck", "Last Name Failed"));

    }



}