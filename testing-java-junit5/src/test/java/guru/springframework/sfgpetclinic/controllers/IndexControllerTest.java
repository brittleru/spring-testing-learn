package guru.springframework.sfgpetclinic.controllers;

import com.sun.jdi.Value;
import guru.springframework.sfgpetclinic.ControllerTests;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;

import java.time.Duration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class IndexControllerTest implements ControllerTests {

    private static IndexController controller;

    @BeforeEach
    void setUp() {
        controller = new IndexController();
    }

    @DisplayName("Test Proper View name is returned for index page")
    @Test
    void index() {
        assertEquals("index", controller.index());
        assertEquals("index", controller.index(), "Wrong View Returned");
        assertEquals("index", controller.index(), () -> "Another Expensive Message " +
                "Make me only if you have to");

        // AssertJ
        assertThat(controller.index()).isEqualToIgnoringCase("index");
    }

    // lambda expressions are only evaluated on a failure condition
    @Test
    @DisplayName("Test Exception 😎")
    void oupsHandler() {

        assertThrows(ValueNotFoundException.class, () -> {
            controller.oopsHandler();
        });

//        assertTrue("notimplemented".equals(controller.oopsHandler()), () -> "This is some expensive" +
//                " Message to build" +
//                " for my test");

    }


    @Disabled("Demo of timeout")
    @Test
    void testTimeOut() {
        assertTimeout(Duration.ofMillis(100), () -> {
            Thread.sleep(5000);
            System.out.println("I got here");
        });
    }

    @Disabled("Demo of timeout")
    @Test
    void testTimeOutPrempt() {
        assertTimeout(Duration.ofMillis(100), () -> {
            Thread.sleep(5000);
            System.out.println("I got here 1124124124124");
        });
    }

    @Disabled("Was just a test")
    @Test
    void testAssumptionTrue() {

        assertTrue("GURU".equalsIgnoreCase(System.getenv("GURU_RUNTIME")));

    }

    @Test
    void testAssumptionTrueAssumptionIsTrue() {

        assertTrue("GURU".equalsIgnoreCase("GURU"));

    }

    @Test
    @EnabledOnOs(OS.MAC)
    void testMeOnMacOS() {

    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    void testMeOnWindows() {

    }

    @Test
    @EnabledOnJre(JRE.JAVA_8)
    void testMeOnJava8() {

    }

    @Test
    @EnabledOnJre(JRE.JAVA_11)
    void testMeOnJava11() {

    }

    @Test
    @EnabledOnJre(JRE.OTHER)
    void testMeOnJava17() {

    }

    @Test
    @EnabledIfEnvironmentVariable(named = "USERNAME", matches = "a fet cate lul")
    void testIfUserFatCat() {
        System.out.println("YAY");
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "USERNAME", matches = "a not fet cate lul")
    void testIfUserNotFatCat() {

    }
}