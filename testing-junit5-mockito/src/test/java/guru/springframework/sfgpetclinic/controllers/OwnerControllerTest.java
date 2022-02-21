package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.fauxspring.Model;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    private static final String OWNERS_CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";
    private static final String REDIRECT_OWNERS_5 = "redirect:/owners/5";
    private static final String REDIRECT_OWNERS_1 = "redirect:/owners/1";
    private static final String OWNERS_FIND_OWNERS = "owners/findOwners";
    private static final String OWNERS_OWNERS_LIST = "owners/ownersList";

    @Mock
    OwnerService ownerService;

    @Mock
    Model model;

    @InjectMocks
    OwnerController controller;

    @Mock
    BindingResult bindingResult;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    @MockitoSettings(strictness = Strictness.LENIENT)
    @BeforeEach
    void setUp() {
        given(ownerService.findAllByLastNameLike(stringArgumentCaptor.capture())).willAnswer(invocation -> {
            List<Owner> owners = new ArrayList<>();
            String name = invocation.getArgument(0);
            switch (name) {
                case "%Doe%":
                    owners.add(new Owner(1L, "John", "Doe"));
                    return owners;
                case "%DontFindMe%":
                    return owners;
                case "%FindMe%":
                    owners.add(new Owner(1L, "John", "Doe"));
                    owners.add(new Owner(1L, "John2", "Doe2"));
                    return owners;
            }

            throw new RuntimeException("Invalid Argument");
        });
    }

    @MockitoSettings(strictness = Strictness.LENIENT)
    @Test
    void processFindFormWildCardString() {

        // given
        Owner owner = new Owner(1L, "John", "Doe");
        List<Owner> ownerList = new ArrayList<>();
        final ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        given(ownerService.findAllByLastNameLike(captor.capture())).willReturn(ownerList);

        // when
        String viewName = controller.processFindForm(owner, bindingResult, null);

        // then
        assertThat(captor.getValue()).isEqualToIgnoringCase("%Doe%");

    }

    @MockitoSettings(strictness = Strictness.LENIENT)
    @Test
    void processFindFormWildCardStringAnnotation() {

        // given
        Owner owner = new Owner(1L, "John", "Doe");
//        List<Owner> ownerList = new ArrayList<>();
//        given(ownerService.findAllByLastNameLike(stringArgumentCaptor.capture())).willReturn(ownerList);

        // when
        String viewName = controller.processFindForm(owner, bindingResult, null);

        // then
        assertThat(stringArgumentCaptor.getValue()).isEqualToIgnoringCase("%Doe%");
        assertThat(viewName).isEqualToIgnoringCase(REDIRECT_OWNERS_1);
        verifyZeroInteractions(model);
    }

    @Test
    void processFindFormWildCardNotFound() {

        // given
        Owner owner = new Owner(1L, "John", "DontFindMe");
//        List<Owner> ownerList = new ArrayList<>();
//        given(ownerService.findAllByLastNameLike(stringArgumentCaptor.capture())).willReturn(ownerList);

        // when
        String viewName = controller.processFindForm(owner, bindingResult, null);

        verifyNoMoreInteractions(ownerService);
        // then
        assertThat(stringArgumentCaptor.getValue()).isEqualToIgnoringCase("%DontFindMe%");
        assertThat(viewName).isEqualToIgnoringCase(OWNERS_FIND_OWNERS);
        verifyZeroInteractions(model);
    }

    @MockitoSettings(strictness = Strictness.LENIENT)
    @Test
    void processFindFormWildCardFound() {

        // given
        Owner owner = new Owner(1L, "John", "FindMe");
//        List<Owner> ownerList = new ArrayList<>();
//        given(ownerService.findAllByLastNameLike(stringArgumentCaptor.capture())).willReturn(ownerList);
        InOrder inOrder = Mockito.inOrder(ownerService, model);

        // when
        String viewName = controller.processFindForm(owner, bindingResult, model);

        // then
        assertThat(stringArgumentCaptor.getValue()).isEqualToIgnoringCase("%FindMe%");
        assertThat(viewName).isEqualToIgnoringCase(OWNERS_OWNERS_LIST);

        // inorder asserts
        inOrder.verify(ownerService).findAllByLastNameLike(anyString());
        inOrder.verify(model, times(1)).addAttribute(anyString(), anyList());
        verifyNoMoreInteractions(model);
    }

    @Test
    void processCreationFormHasErrors() {

        // given
        Owner owner = new Owner(1L, "Jane", "Doe");
        given(bindingResult.hasErrors()).willReturn(true);

        // when
        String viewName = controller.processCreationForm(owner, bindingResult);

        // then
        assertThat(viewName).isEqualToIgnoringCase(OWNERS_CREATE_OR_UPDATE_OWNER_FORM);

    }

    @Test
    void processCreationFormNoErrors() {

        // given
        Owner owner = new Owner(5L, "Jane", "Doe");
        given(bindingResult.hasErrors()).willReturn(false);
        given(ownerService.save(any())).willReturn(owner);

        // when
        String viewName = controller.processCreationForm(owner, bindingResult);

        // then
        assertThat(viewName).isEqualToIgnoringCase(REDIRECT_OWNERS_5);

    }


}