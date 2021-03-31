package br.com.dextra.MakeMagic.repository;

import br.com.dextra.MakeMagic.domain.entity.Personage;
import br.com.dextra.MakeMagic.util.PersonageCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for Personage Repository")
class PersonageRepositoryTest {

    @Autowired
    private PersonageRepository personageRepository;

    @Test
    @DisplayName("Save persists personage when Successful")
    void save_PersistPersonage_WhenSuccessful() {
        Personage createPersonageToBeSaved = PersonageCreator.createPersonageTobeSaved();

        Personage personageSaved = this.personageRepository.save(createPersonageToBeSaved);

        Assertions.assertThat(personageSaved).isNotNull();

        Assertions.assertThat(personageSaved.getId()).isNotNull();

        Assertions.assertThat(personageSaved.getName()).isEqualTo(createPersonageToBeSaved.getName());

        Assertions.assertThat(personageSaved.getHouse()).isEqualTo(createPersonageToBeSaved.getHouse());
    }

    @Test
    @DisplayName("Save updates personage when Successful")
    void save_UpdatesPersonage_WhenSuccessful() {
        Personage createPersonageToBeSaved = PersonageCreator.createPersonageTobeSaved();

        Personage personageSaved = this.personageRepository.save(createPersonageToBeSaved);

        personageSaved.setName("Hermione Granger");

        Personage personageUpdated = this.personageRepository.save(personageSaved);

        Assertions.assertThat(personageUpdated).isNotNull();

        Assertions.assertThat(personageUpdated.getId()).isNotNull();

        Assertions.assertThat(personageUpdated.getName()).isEqualTo(createPersonageToBeSaved.getName());

        Assertions.assertThat(personageUpdated.getHouse()).isEqualTo(createPersonageToBeSaved.getHouse());
    }

    @Test
    @DisplayName("Delete removes personage when Successful")
    void delete_RemovesPersonage_WhenSuccessful() {
        Personage createPersonageToBeSaved = PersonageCreator.createPersonageTobeSaved();

        Personage personageSaved = this.personageRepository.save(createPersonageToBeSaved);

        this.personageRepository.delete(personageSaved);

        Optional<Personage> personageOptional = this.personageRepository.findById(personageSaved.getId());

        Assertions.assertThat(personageOptional).isEmpty();
    }

    @Test
    @DisplayName("Find By House returns list of personage when Successful")
    void findByHouse_ReturnsListOfPersonage_WhenSuccessful() {
        Personage createPersonageToBeSaved = PersonageCreator.createPersonageTobeSaved();

        Personage personageSaved = this.personageRepository.save(createPersonageToBeSaved);

        String house = personageSaved.getHouse();

        List<Personage> personages = this.personageRepository.findByHouse(house);

        Assertions.assertThat(personages)
                .isNotEmpty()
                .contains(personageSaved);
    }

    @Test
    @DisplayName("Find By House returns empty list when no house is found")
    void findByHouse_ReturnsEmptyList_WhenPersonageIsNotFound() {
        List<Personage> personages = this.personageRepository.findByHouse("klji34651-344svsr");

        Assertions.assertThat(personages).isEmpty();
    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when name is empty")
    void save_ThrowsConstraintViolationException_WhenNameIsEmpty() {
        Personage personage = new Personage();

        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.personageRepository.save(personage))
                .withMessageContaining("The personage name cannot be empty");
    }
}