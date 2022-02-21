package guru.springframework;

import org.junit.jupiter.api.*;

class GreetingTest {

    private static Greeting greeting;

    /**
     * This is really important to be a static method
     */
    @BeforeAll
    public static void beforeClass() {
        System.out.println("Before - I am only called Once!!!");
    }

    @BeforeEach
    void setup() {
        System.out.println("In before each...");
        greeting = new Greeting();
    }

    @Test
    void helloWorld() {

        System.out.println(greeting.helloWorld());

    }

    @Test
    void testHelloWorld() {

        System.out.println(greeting.helloWorld("Medi"));

    }

    @Test
    void testHelloWorld2() {

        System.out.println(greeting.helloWorld("Sam"));

    }

    @AfterEach
    void tearDown() {
        System.out.println("In after each...");
    }

    /**
     * This is really important to be a static method
     */
    @AfterAll
    public static void afterClass() {
        System.out.println("After!!! **** - I am only called once!!!");
    }

}