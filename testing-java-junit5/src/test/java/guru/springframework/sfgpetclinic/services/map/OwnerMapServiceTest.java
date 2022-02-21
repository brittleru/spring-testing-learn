package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Owner Map Service Test - ")
class OwnerMapServiceTest {

    private OwnerMapService ownerMapService;
    private PetTypeService petTypeService;
    private PetService petService;

    @BeforeEach
    void setUp() {
        petTypeService = new PetTypeMapService();
        petService = new PetMapService();
        ownerMapService = new OwnerMapService(petTypeService, petService);

        System.out.println("First Before Each");
    }

    @Test
    @DisplayName("Verify Zero Owners")
    void ownersAreZero() {
        int ownerCount = ownerMapService.findAll().size();
        assertEquals(0, ownerCount);
    }

    @Nested
    @DisplayName("Pet Type - ")
    class TestCreatePetTypes {

        @BeforeEach
        void setUp() {
            PetType petType1 = new PetType(1L, "Dog");
            PetType petType2 = new PetType(2L, "Cat");
            petTypeService.save(petType1);
            petTypeService.save(petType2);

            System.out.println("Nested Before each");

        }

        @Test
        void testPetCount() {
            int petTypeCount = petTypeService.findAll().size();
            assertEquals(2, petTypeCount);
        }

        @Nested
        @DisplayName("Save Owners Tests - ")
        class SaveOwnersTests {

            @BeforeEach
            void setUp() {
                ownerMapService.save(new Owner(1L, "Before", "Each"));

                System.out.println("Saved Owners Before Each");
            }

            @Test
            void saveOwner() {
                Owner owner = new Owner(2L, "Joe", "Buck");

                Owner savedOwner = ownerMapService.save(owner);
                assertNotNull(savedOwner);
            }

            @Nested
            @DisplayName("Find Owners Tests - ")
            class FindOwnersTests {

                @Test
                @DisplayName("Find Owner")
                void findOwner() {
                    Owner foundOwner = ownerMapService.findById(1L);
                    assertNotNull(foundOwner);
                }

                @Test
                @DisplayName("Find Owner Not Found")
                void findOwnerNotFound() {
                    Owner foundOwner = ownerMapService.findById(2L);
                    assertNull(foundOwner);
                }

            }
        }

    }

    @Test
    @DisplayName("Verify Still Zero Owners")
    void ownersAreStillZero() {
        int ownerCount = ownerMapService.findAll().size();

        assertEquals(0, ownerCount);
    }
}