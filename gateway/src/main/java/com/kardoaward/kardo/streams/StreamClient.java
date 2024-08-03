package com.kardoaward.kardo.streams;

import com.kardoaward.kardo.client.BaseClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.Map;

@Service
public class StreamClient extends BaseClient {
    private static final String API_PREFIX = "/streams";

    @Autowired
    public StreamClient(@Value("${kardo-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }

    public ResponseEntity getAllStreams(Integer from, Integer size) {
            Map<String, Object> parameters = Map.of(
                    "from", from,
                    "size", size
            );
            return get("", parameters);
    }

    public ResponseEntity getStreamById(Long streamId) {
        return get("/" + streamId);
    }
}