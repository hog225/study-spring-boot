package com.yg.domain.pubapi.service;

import com.yg.external.publicapis.api.PublicApi;
import com.yg.external.publicapis.dto.EntryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PublicApiService {
    private final PublicApi publicApi;

    public EntryResponse.Entry getPublicApiEntry(Integer index) {
        EntryResponse rsp = publicApi.getEntries();
        EntryResponse.Entry entry = rsp.getEntries().get(index);
        return entry;
    }
}
