package br.com.dextra.MakeMagic.config;

import br.com.dextra.MakeMagic.client.PotterApiClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PotterApiConfig {

    @Bean
    public PotterApiClient potterApiClient() {
        return new PotterApiClient();
    }
}
