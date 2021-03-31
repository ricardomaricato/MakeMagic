package br.com.dextra.MakeMagic.domain.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Value {

    @ApiModelProperty(value = "name", example = "courage")
    private String name;
}
