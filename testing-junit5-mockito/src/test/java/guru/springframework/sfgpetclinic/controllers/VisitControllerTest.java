package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.VisitService;
import guru.springframework.sfgpetclinic.services.map.PetMapService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    @Mock
    VisitService visitService;

//    @Mock
//    PetService petService;
    @Spy
    PetMapService petMapService;


    @InjectMocks
    VisitController visitController;

    // a spy dif from a mock because it allows you to access the underlying object but works like a regular mock
    // a spy is a wrapper around the real implementation, when you call the real method
    // when anything happens go and call the real method


    @Test
    void loadPetWithVisit() {

        // given
        Map<String, Object> model = new HashMap<>();
        Pet pet = new Pet(12L);
        Pet pet3 = new Pet(3L);
        petMapService.save(pet);
        petMapService.save(pet3);

//        given(petService.findById(anyLong())).willReturn(pet);
        given(petMapService.findById(anyLong())).willCallRealMethod();


        // when
        Visit visit = visitController.loadPetWithVisit(12L, model);

        // then
        assertThat(visit).isNotNull();
        assertThat(visit.getPet()).isNotNull();
        assertThat(visit.getPet().getId()).isEqualTo(12L);
        verify(petMapService, times(1)).findById(anyLong());

    }

    @Test
    void loadPetWithVisitWithStubbing() {

        // given
        Map<String, Object> model = new HashMap<>();
        Pet pet = new Pet(12L);
        Pet pet3 = new Pet(3L);
        petMapService.save(pet);
        petMapService.save(pet3);
        given(petMapService.findById(anyLong())).willReturn(pet3);

        // when
        Visit visit = visitController.loadPetWithVisit(12L, model);

        // then
        assertThat(visit).isNotNull();
        assertThat(visit.getPet()).isNotNull();
        assertThat(visit.getPet().getId()).isEqualTo(3L);
        verify(petMapService, times(1)).findById(anyLong());

    }
}