package br.com.dextra.MakeMagic.util;

import br.com.dextra.MakeMagic.domain.dto.PersonageDto;

public class PersonageCreatorDto {

    public static PersonageDto createValidPersonageDto() {
        return PersonageDto.builder()
                .id(1L)
                .name("Harry Potter")
                .role("student")
                .school("Hogwarts School of Witchcraft and Wizardry")
                .house("1760529f-6d51-4cb1-bcb1-25087fce5bde")
                .patronus("stag")
                .build();
    }
}
