package br.com.dextra.MakeMagic.service;

import br.com.dextra.MakeMagic.domain.dto.PersonageDto;
import br.com.dextra.MakeMagic.domain.entity.Personage;
import br.com.dextra.MakeMagic.domain.mapper.PersonageMapper;
import br.com.dextra.MakeMagic.domain.requests.PersonagePostRequestBody;
import br.com.dextra.MakeMagic.domain.requests.PersonagePutRequestBody;
import br.com.dextra.MakeMagic.exception.ResourceNotFoundException;
import br.com.dextra.MakeMagic.repository.PersonageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonageService {

    private final PersonageRepository personageRepository;

    public List<PersonageDto> listAll() {
        return personageRepository.findAll().stream().map(PersonageMapper.INSTANCE::toPersonageDto)
                .collect(Collectors.toList());
    }

    public List<PersonageDto> findByHouse(String house) {
        return personageRepository.findByHouse(house).stream().map(PersonageMapper.INSTANCE::toPersonageDto)
                .collect(Collectors.toList());
    }

    public Personage findByIdOrThrowNotFoundException(Long id) {
        return personageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Personage Not Found"));
    }

    public PersonageDto findById(Long id) {
        Personage personage = findByIdOrThrowNotFoundException(id);
        return PersonageMapper.INSTANCE.toPersonageDto(personage);
    }

    public Personage save(PersonagePostRequestBody personagePostRequestBody) {
        return personageRepository.save(PersonageMapper.INSTANCE.toPersonage(personagePostRequestBody));
    }

    public void delete(Long id) {
        personageRepository.delete(findByIdOrThrowNotFoundException(id));
    }

    public void replace(PersonagePutRequestBody personagePutRequestBody) {
        Personage personageSaved = findByIdOrThrowNotFoundException(personagePutRequestBody.getId());
        Personage personage = PersonageMapper.INSTANCE.toPersonage(personagePutRequestBody);
        personage.setId(personageSaved.getId());
        personageRepository.save(personage);
    }
}
