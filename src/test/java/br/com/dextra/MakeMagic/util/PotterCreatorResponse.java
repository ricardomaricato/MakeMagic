package br.com.dextra.MakeMagic.util;

import br.com.dextra.MakeMagic.domain.entity.PotterResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class PotterCreatorResponse {

    public static ResponseEntity<PotterResponse> createValidPotterResponse() {
        PotterResponse potterResponse = PotterResponse.builder()
                .houses(List.of(HouseCreator.creatValidHouse()))
                .build();
        return ResponseEntity.ok(potterResponse);
    }
}
