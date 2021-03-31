package br.com.dextra.MakeMagic.domain.requests;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "PersonagePostRequestBody")
public class PersonagePostRequestBody {

    @ApiModelProperty(value = "name", example = "Harry Potter", position = 1)
    @NotEmpty(message = "The personage name cannot be empty")
    private String name;

    @ApiModelProperty(value = "role", example = "student", position = 2)
    @NotEmpty(message = "The personage role cannot be empty")
    private String role;

    @ApiModelProperty(value = "school", example = "Hogwarts School of Witchcraft and Wizardry", position = 3)
    @NotEmpty(message = "The personage school cannot be empty")
    private String school;

    @ApiModelProperty(value = "house", example = "1760529f-6d51-4cb1-bcb1-25087fce5bde", position = 4)
    @NotEmpty(message = "The personage house cannot be empty")
    private String house;

    @ApiModelProperty(value = "patronus", example = "stag", position = 5)
    @NotEmpty(message = "The personage patronus cannot be empty")
    private String patronus;
}
