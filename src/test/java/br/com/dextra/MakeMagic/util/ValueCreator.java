package br.com.dextra.MakeMagic.util;

import br.com.dextra.MakeMagic.domain.entity.Value;

public class ValueCreator {

    public static Value creatValidValue() {
        return Value.builder()
                .name("courage")
                .build();
    }
}
