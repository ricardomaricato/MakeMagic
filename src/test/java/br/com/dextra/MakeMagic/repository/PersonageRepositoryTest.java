package br.com.dextra.MakeMagic.repository;

import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Tests for Personage Repository")
class PersonageRepositoryTest {

    @Autowired
    private PersonageRepository personageRepository;


}