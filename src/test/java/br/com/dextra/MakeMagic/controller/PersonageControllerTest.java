package br.com.dextra.MakeMagic.controller;

import br.com.dextra.MakeMagic.domain.dto.PersonageDto;
import br.com.dextra.MakeMagic.domain.entity.Personage;
import br.com.dextra.MakeMagic.domain.requests.PersonagePostRequestBody;
import br.com.dextra.MakeMagic.domain.requests.PersonagePutRequestBody;
import br.com.dextra.MakeMagic.service.PersonageService;
import br.com.dextra.MakeMagic.util.PersonageCreator;
import br.com.dextra.MakeMagic.util.PersonageCreatorDto;
import br.com.dextra.MakeMagic.util.PersonagePostRequestBodyCreator;
import br.com.dextra.MakeMagic.util.PersonagePutRequestBodyCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

@ExtendWith(SpringExtension.class)
class PersonageControllerTest {

    @InjectMocks
    PersonageController personageController;

    @Mock
    PersonageService personageServiceMock;

    @BeforeEach
    void setUp() {
        BDDMockito.when(personageServiceMock.listAll())
                .thenReturn(List.of(PersonageCreatorDto.createValidPersonageDto()));

        BDDMockito.when(personageServiceMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(PersonageCreatorDto.createValidPersonageDto());

        BDDMockito.when(personageServiceMock.findByHouse(ArgumentMatchers.anyString()))
                .thenReturn(List.of(PersonageCreatorDto.createValidPersonageDto()));

        BDDMockito.when(personageServiceMock.save(ArgumentMatchers.any(PersonagePostRequestBody.class)))
                .thenReturn(PersonageCreator.createValidPersonage());

        BDDMockito.doNothing().when(personageServiceMock).replace(ArgumentMatchers.any(PersonagePutRequestBody.class));

        BDDMockito.doNothing().when(personageServiceMock).delete(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("listAll returns list of personage when successful")
    void listAll_ReturnsListOfPersonages_WhenSuccessful() {
        String expectedName = PersonageCreator.createValidPersonage().getName();

        ResponseEntity<List<PersonageDto>> personageDtos = personageController.listAll();

        Assertions.assertThat(personageDtos.getBody())
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(personageDtos.getBody().get(0).getName()).isEqualTo(expectedName);

        Assertions.assertThat(personageDtos.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("findById returns personage when successful")
    void findById_ReturnsPersonage_WhenSuccessful() {
        Long expectedId = PersonageCreator.createValidPersonage().getId();

        ResponseEntity<PersonageDto> personageDtos = personageController.findById(1L);

        Assertions.assertThat(personageDtos.getBody()).isNotNull();

        Assertions.assertThat(personageDtos.getBody().getId()).isEqualTo(expectedId);

        Assertions.assertThat(personageDtos.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("findByHouse returns an HttpStatus.NO_CONTENT when personage is not found")
    void findByHouse_Returns_HttpStatus_NO_CONTENT_WhenPersonageIsNotFound() {
        BDDMockito.when(personageServiceMock.findByHouse(ArgumentMatchers.anyString()))
                .thenReturn(Collections.emptyList());

        ResponseEntity<List<PersonageDto>> personaDtos = personageController.findByHouse("123456-asfsgrt");

        Assertions.assertThat(personaDtos.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("save returns anime when successful")
    void save_ReturnsAnimes_WhenSuccessful() {
        ResponseEntity<Personage> personage = personageController.save(PersonagePostRequestBodyCreator.createPostRequestBody());

        Assertions.assertThat(personage.getBody()).isNotNull().isEqualTo(PersonageCreator.createValidPersonage());

        Assertions.assertThat(personage.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    @DisplayName("replace returns personage when successful")
    void replace_UpdatesPersonages_WhenSuccessful() {
        Assertions.assertThatCode(() -> personageController.replace(PersonagePutRequestBodyCreator.createPersonagePutRequestBody()))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = personageController.replace(PersonagePutRequestBodyCreator.createPersonagePutRequestBody());

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("delete returns personage when successful")
    void delete_RemovesPersonage_WhenSuccessful(){
        Assertions.assertThatCode(() -> personageController.delete(1L))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = personageController.delete(1L);

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}