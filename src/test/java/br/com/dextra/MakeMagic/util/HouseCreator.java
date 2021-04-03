package br.com.dextra.MakeMagic.util;

import br.com.dextra.MakeMagic.domain.entity.House;

import java.util.List;

public class HouseCreator {

    public static House creatValidHouse() {
        return House.builder()
                .id("1760529f-6d51-4cb1-bcb1-25087fce5bde")
                .name("Gryffindor")
                .headOfHouse("Minerva McGonagall")
                .mascot("lion")
                .houseGhost("Nearly Headless Nick")
                .founder("Goderic Gryffindor")
                .colors(List.of(ColorCreator.creatValidColor()))
                .values(List.of(ValueCreator.creatValidValue()))
                .build();
    }
}
