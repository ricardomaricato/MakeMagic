package br.com.dextra.MakeMagic.util;

import br.com.dextra.MakeMagic.domain.entity.Color;

public class ColorCreator {

    public static Color creatValidColor() {
        return Color.builder()
                .name("scarlet")
                .build();
    }
}
