package br.com.dextra.MakeMagic.domain.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class House {

    @ApiModelProperty(value = "id", example = "1760529f-6d51-4cb1-bcb1-25087fce5bde", position = 0)
    private String id;

    @ApiModelProperty(value = "name", example = "Gryffindor", position = 1)
    private String name;

    @ApiModelProperty(value = "headOfHouse", example = "Minerva McGonagall", position = 2)
    private String headOfHouse;

    @ApiModelProperty(value = "values", example = "courage, bravery, nerve", position = 3)
    private List<Value> values;

    @ApiModelProperty(value = "color", example = "scarlet, gold", position = 4)
    private List<Color> colors;

    @ApiModelProperty(value = "mascot", example = "lion", position = 5)
    private String mascot;

    @ApiModelProperty(value = "houseGhost", example = "Nearly Headless Nick", position = 6)
    private String houseGhost;

    @ApiModelProperty(value = "founder", example = "Goderic Gryffindor", position = 7)
    private String founder;
}
