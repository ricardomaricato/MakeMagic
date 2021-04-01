package br.com.dextra.MakeMagic.service;

import br.com.dextra.MakeMagic.client.PotterApiClient;
import br.com.dextra.MakeMagic.domain.dto.PersonageDto;
import br.com.dextra.MakeMagic.domain.entity.Personage;
import br.com.dextra.MakeMagic.exception.ResourceNotFoundException;
import br.com.dextra.MakeMagic.repository.PersonageRepository;
import br.com.dextra.MakeMagic.util.PersonageCreator;
import br.com.dextra.MakeMagic.util.PersonagePostRequestBodyCreator;
import br.com.dextra.MakeMagic.util.PersonagePutRequestBodyCreator;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class PersonageServiceTest {

    @InjectMocks
    PersonageService personageService;

    @Mock
    PersonageRepository personageRepositoryMock;

    @Mock
    PotterApiClient potterApiClientMock;

    @BeforeEach
    void setUp() {
        BDDMockito.when(personageRepositoryMock.findAll())
                .thenReturn(List.of(PersonageCreator.createValidPersonage()));

        BDDMockito.when(personageRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(PersonageCreator.createValidPersonage()));

        BDDMockito.when(personageRepositoryMock.findByHouse(ArgumentMatchers.anyString()))
                .thenReturn(List.of(PersonageCreator.createValidPersonage()));

        BDDMockito.when(personageRepositoryMock.save(ArgumentMatchers.any(Personage.class)))
                .thenReturn(PersonageCreator.createValidPersonage());

        BDDMockito.doNothing().when(personageRepositoryMock).delete(ArgumentMatchers.any(Personage.class));
    }


    @Test
    @DisplayName("listAll returns list of personage when successful")
    void listAll_ReturnsListOfPersonages_WhenSuccessful() {
        String expectedName = PersonageCreator.createValidPersonage().getName();

        List<PersonageDto> personageDtos = personageService.listAll();

        Assertions.assertThat(personageDtos)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(personageDtos.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findByIdOrThrowNotFoundException throws NotFoundException when personage is not found")
    void findByIdOrThrowNotFoundException_ReturnsPersonages_WhenSuccessful() {
        BDDMockito.when(personageRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(() -> personageService.findByIdOrThrowNotFoundException(1L));
    }

    @Test
    @DisplayName("findById returns personage when successful")
    void findById_ReturnsPersonages_WhenSuccessful() {
        Long expectedId = PersonageCreator.createValidPersonage().getId();

        PersonageDto personageDto = personageService.findById(1L);

        Assertions.assertThat(personageDto).isNotNull();

        Assertions.assertThat(personageDto.getId()).isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findByHouse returns an empty list of personage when personage is not found")
    void findByHouse_ReturnsEmptyListOfPersonage_WhenPersonageIsNotFound(){
        BDDMockito.when(personageRepositoryMock.findByHouse(ArgumentMatchers.anyString()))
                .thenReturn(Collections.emptyList());

        List<PersonageDto> personageDtos = personageService.findByHouse("123456-asfgrga");

        Assertions.assertThat(personageDtos)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("save returns personage when successful")
    void save_ReturnsPersonages_WhenSuccessful() {
        Personage personage = personageService.save(PersonagePostRequestBodyCreator.createPostRequestBody());

        Assertions.assertThat(personage).isNotNull().isEqualTo(PersonageCreator.createValidPersonage());
    }

    @Test
    @DisplayName("replace returns personage when successful")
    void replace_UpdatesPersonages_WhenSuccessful(){
        Assertions.assertThatCode(() -> personageService.replace(PersonagePutRequestBodyCreator.createPersonagePutRequestBody()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("delete returns personage when successful")
    void delete_RemovesPersonages_WhenSuccessful(){
        Assertions.assertThatCode(() -> personageService.delete(1L))
                .doesNotThrowAnyException();
    }
}