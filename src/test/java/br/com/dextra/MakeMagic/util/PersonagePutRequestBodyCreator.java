package br.com.dextra.MakeMagic.util;

import br.com.dextra.MakeMagic.domain.requests.PersonagePutRequestBody;

public class PersonagePutRequestBodyCreator {

    public static PersonagePutRequestBody createPersonagePutRequestBody() {
        return PersonagePutRequestBody.builder()
                .id(PersonageCreator.createValidPersonage().getId())
                .name(PersonageCreator.createValidPersonage().getName())
                .role(PersonageCreator.createValidPersonage().getRole())
                .school(PersonageCreator.createValidPersonage().getSchool())
                .house(PersonageCreator.createValidPersonage().getHouse())
                .patronus(PersonageCreator.createValidPersonage().getPatronus())
                .build();
    }
}
