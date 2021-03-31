package br.com.dextra.MakeMagic.repository;

import br.com.dextra.MakeMagic.domain.entity.Personage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonageRepository extends JpaRepository<Personage, Long> {

    List<Personage> findByHouse(String house);
}
