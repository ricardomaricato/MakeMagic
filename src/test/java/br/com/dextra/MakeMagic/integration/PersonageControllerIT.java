package br.com.dextra.MakeMagic.integration;

import br.com.dextra.MakeMagic.domain.dto.PersonageDto;
import br.com.dextra.MakeMagic.domain.entity.Personage;
import br.com.dextra.MakeMagic.domain.requests.PersonagePostRequestBody;
import br.com.dextra.MakeMagic.repository.PersonageRepository;
import br.com.dextra.MakeMagic.util.PersonageCreator;
import br.com.dextra.MakeMagic.util.PersonagePostRequestBodyCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class PersonageControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    @Autowired
    private PersonageRepository personageRepository;

    @Test
    @DisplayName("listAll returns list of personage when successful")
    void listAll_ReturnsListOfPersonages_WhenSuccessful() {
        Personage savedPersonage = personageRepository.save(PersonageCreator.createPersonageTobeSaved());

        String expectedName = savedPersonage.getName();

        List<Personage> personages = testRestTemplate.exchange("/api/personages", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Personage>>() {
                }).getBody();

        Assertions.assertThat(personages)
                .isNotNull()
                .isNotEmpty();

        Assertions.assertThat(personages.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findById returns personage when successful")
    void findById_ReturnsPersonage_WhenSuccessful() {
        Personage savedPersonage = personageRepository.save(PersonageCreator.createPersonageTobeSaved());

        Long expectedId = savedPersonage.getId();

        Personage personage = testRestTemplate.getForObject("/api/personages/{id}", Personage.class, expectedId);

        Assertions.assertThat(personage).isNotNull();

        Assertions.assertThat(personage.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findByHouse returns a list of personage when successful")
    void findByHouse_ReturnsListOfPersonage_WhenSuccessful() {
        Personage savedPersonage = personageRepository.save(PersonageCreator.createPersonageTobeSaved());

        String expectedHouse = savedPersonage.getHouse();

        String url = String.format("/api/personages/find?house=%s", expectedHouse);

        List<Personage> personages = testRestTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Personage>>() {
                }).getBody();

        Assertions.assertThat(personages)
                .isNotNull()
                .isNotEmpty();

        Assertions.assertThat(personages.get(0).getHouse()).isEqualTo(expectedHouse);
    }

    @Test
    @DisplayName("findByHouse returns an HttpStatus.NO_CONTENT when personage is not found")
    void findByHouse_Returns_HttpStatus_NO_CONTENT_WhenPersonageIsNotFound() {
        ResponseEntity<PersonageDto> personages = testRestTemplate.exchange("/api/personages/find?house=dbz",
                HttpMethod.GET, null, new ParameterizedTypeReference<>() {} );

        Assertions.assertThat(personages.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("save returns personage when successful")
    void save_ReturnsPersonage_WhenSuccessful() {
        PersonagePostRequestBody personagePostRequestBody = PersonagePostRequestBodyCreator.createPostRequestBody();

        ResponseEntity<Personage> animeResponseEntity = testRestTemplate.postForEntity("/api/personages",
                personagePostRequestBody, Personage.class);

        Assertions.assertThat(animeResponseEntity).isNotNull();
        Assertions.assertThat(animeResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(animeResponseEntity.getBody()).isNotNull();
        Assertions.assertThat(animeResponseEntity.getBody().getId()).isNotNull();
    }

    @Test
    @DisplayName("replace updates personage when successful")
    void replace_UpdatesPersonage_WhenSuccessful(){
        Personage savedPersonage = personageRepository.save(PersonageCreator.createPersonageTobeSaved());

        savedPersonage.setName("new name");

        ResponseEntity<Void> animeResponseEntity = testRestTemplate.exchange("/api/personages",
                HttpMethod.PUT,new HttpEntity<>(savedPersonage), Void.class);

        Assertions.assertThat(animeResponseEntity).isNotNull();

        Assertions.assertThat(animeResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("delete removes personage when successful")
    void delete_RemovesPersonage_WhenSuccessful(){
        Personage savedPersonage = personageRepository.save(PersonageCreator.createPersonageTobeSaved());

        ResponseEntity<Void> animeResponseEntity = testRestTemplate.exchange("/api/personages/{id}",
                HttpMethod.DELETE,null, Void.class, savedPersonage.getId());

        Assertions.assertThat(animeResponseEntity).isNotNull();

        Assertions.assertThat(animeResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}
