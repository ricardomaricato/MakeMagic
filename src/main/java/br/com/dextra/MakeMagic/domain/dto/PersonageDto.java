package br.com.dextra.MakeMagic.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "PersonageDto")
public class PersonageDto {

    @ApiModelProperty(value = "id", example = "1", position = 0)
    private Long id;

    @ApiModelProperty(value = "name", example = "Harry Potter", position = 1)
    private String name;

    @ApiModelProperty(value = "role", example = "student", position = 2)
    private String role;

    @ApiModelProperty(value = "school", example = "Hogwarts School of Witchcraft and Wizardry", position = 3)
    private String school;

    @ApiModelProperty(value = "house", example = "1760529f-6d51-4cb1-bcb1-25087fce5bde", position = 4)
    private String house;

    @ApiModelProperty(value = "patronus", example = "stag", position = 5)
    private String patronus;
}
