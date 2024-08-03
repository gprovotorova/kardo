package com.kardoaward.kardo.event;

import com.kardoaward.kardo.client.BaseClient;
import com.kardoaward.kardo.direction.DirectionDto;
import com.kardoaward.kardo.enums.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

@Service
public class EventClient extends BaseClient {
    private static final String API_PREFIX = "/events";

    @Autowired
    public EventClient(@Value("${kardo-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }

    public ResponseEntity getEventsWithFilters(String text,
                                               EventType eventType,
                                               LocalDateTime date,
                                               Set<DirectionDto> direction,
                                               String sort,
                                               Integer from,
                                               Integer size) {
        Map<String, Object> parameters = Map.of(
                "text", text,
                "eventType", eventType,
                "date", date,
                "direction", direction,
                "sort", sort,
                "from", from,
                "size", size
        );
        return get("", parameters);
    }

    public ResponseEntity getEventById(Long eventId) {
        return get("/" + eventId);
    }

    public ResponseEntity getCommentsByEventId(Long eventId,
                                               Integer from,
                                               Integer size) {
        Map<String, Object> parameters = Map.of(
                "from", from,
                "size", size
        );
        return get("/" + eventId + "/comments?from={from}&size={size}", parameters);
    }
}
