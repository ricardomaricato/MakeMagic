package br.com.dextra.MakeMagic.client;

import br.com.dextra.MakeMagic.domain.entity.PotterResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Log4j2
public class PotterApiClient {

    public ResponseEntity<PotterResponse> findHouses() {
        HttpHeaders header = new HttpHeaders();
        header.set("apiKey", "3b70652d-daf5-4899-8e23-529c03c33848");

        HttpEntity entity = new HttpEntity(header);

        ResponseEntity<PotterResponse> exchange = new RestTemplate()
                .exchange("http://us-central1-rh-challenges.cloudfunctions.net/potterApi/houses",
                        HttpMethod.GET, entity, new ParameterizedTypeReference<>() {});
        return exchange;
    }

}
