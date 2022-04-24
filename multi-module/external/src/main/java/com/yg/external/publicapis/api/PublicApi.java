package com.yg.external.publicapis.api;

import com.yg.external.publicapis.constant.PublicUri;
import com.yg.external.publicapis.dto.EntryResponse;
import com.yg.external.publicapis.property.PublicProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.http.client.methods.HttpHead;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Log4j2
@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(PublicProperty.class)
public class PublicApi {
    private final RestTemplate restTemplate;
    private final PublicProperty publicProperty;

    private UriComponentsBuilder createPublicApiUri(PublicUri publicUri) {
        return UriComponentsBuilder.newInstance()
                .scheme(publicProperty.getProto())
                .host(publicProperty.getUrl())
                .path(publicUri.getValue());
    }

    public EntryResponse getEntries() {
        UriComponents uriComponents = createPublicApiUri(PublicUri.ENTRIES)
                .build()
                .encode();

        log.info("URI = {} prop = {} {}", uriComponents.toUri().toString(), publicProperty.getProto(), publicProperty.getUrl());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<EntryResponse> response = restTemplate.exchange(uriComponents.toUri(), HttpMethod.GET
                , new HttpEntity<>(headers), EntryResponse.class);

        return response.getBody();
    }



}
