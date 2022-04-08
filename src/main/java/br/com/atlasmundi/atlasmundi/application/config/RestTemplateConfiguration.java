package br.com.atlasmundi.atlasmundi.application.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class RestTemplateConfiguration {

    @Primary
    @Bean
    public RestTemplate restTemplate(MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter) {

        final DefaultUriBuilderFactory builderFactory = new DefaultUriBuilderFactory();
        builderFactory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

        return new RestTemplateBuilder()
                .messageConverters(mappingJackson2HttpMessageConverter)
                .uriTemplateHandler(builderFactory)
                .build();
    }

    @Bean
    public RestTemplate restTemplateSecondary(MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter) {
        return new RestTemplateBuilder()
                .build();
    }
}
