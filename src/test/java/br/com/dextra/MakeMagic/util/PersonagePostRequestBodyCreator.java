package br.com.dextra.MakeMagic.util;

import br.com.dextra.MakeMagic.domain.requests.PersonagePostRequestBody;

public class PersonagePostRequestBodyCreator {

    public static PersonagePostRequestBody createPostRequestBody() {
        return PersonagePostRequestBody.builder()
                .name(PersonageCreator.createPersonageTobeSaved().getName())
                .role(PersonageCreator.createPersonageTobeSaved().getRole())
                .school(PersonageCreator.createPersonageTobeSaved().getSchool())
                .house(PersonageCreator.createPersonageTobeSaved().getHouse())
                .patronus(PersonageCreator.createPersonageTobeSaved().getPatronus())
                .build();
    }
}
