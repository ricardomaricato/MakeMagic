package br.com.dextra.MakeMagic.domain.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PotterResponse {

    @ApiModelProperty(value = "houses")
    private List<House> houses;
}
