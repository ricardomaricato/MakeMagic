package br.com.dextra.MakeMagic.domain.mapper;

import br.com.dextra.MakeMagic.domain.dto.PersonageDto;
import br.com.dextra.MakeMagic.domain.entity.Personage;
import br.com.dextra.MakeMagic.domain.requests.PersonagePostRequestBody;
import br.com.dextra.MakeMagic.domain.requests.PersonagePutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PersonageMapper {

    PersonageMapper INSTANCE = Mappers.getMapper(PersonageMapper.class);

    Personage toPersonage(PersonagePostRequestBody personagePostRequestBody);
    Personage toPersonage(PersonagePutRequestBody personagePutRequestBody);
    PersonageDto toPersonageDto(Personage personage);
}
