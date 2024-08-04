package com.kardoaward.kardo.admin;

import com.kardoaward.kardo.client.BaseClient;
import com.kardoaward.kardo.competition.CompetitionDto;
import com.kardoaward.kardo.event.dto.EventDto;
import com.kardoaward.kardo.partners.dto.PartnerDto;
import com.kardoaward.kardo.streams.dto.StreamDto;
import com.kardoaward.kardo.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Service
public class AdminClient extends BaseClient {
    private static final String API_PREFIX = "/admin";

    @Autowired
    public AdminClient(@Value("${kardo-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }

    public ResponseEntity createEvent(EventDto eventDto) {
        return post("/events/", eventDto);
    }

    public ResponseEntity updateEvent(Long eventId, EventDto eventDto) {
        return patch("/events/" + eventId, eventDto);
    }

    public ResponseEntity deleteEvent(Long eventId) {
        return delete("/events/" + eventId);
    }


    public ResponseEntity createPartner(PartnerDto partnerDto) {
        return post("/partners", partnerDto);
    }

    public ResponseEntity deletePartner(Long partnerId) {
        return delete("/partners/" + partnerId);
    }

    public ResponseEntity updatePartner(Long partnerId, PartnerDto partnerDto) {
        return patch("/partners/" + partnerId, partnerDto);
    }

    public ResponseEntity createStream(StreamDto streamDto) {
        return post("/streams", streamDto);
    }

    public ResponseEntity deleteStream(Long streamId) {
        return delete("/streams/" + streamId);
    }

    public ResponseEntity updateStream(Long streamId, StreamDto streamDto) {
        return patch("/streams/" + streamId, streamDto);
    }

    public ResponseEntity createCompetition(CompetitionDto competitionDto) {
        return post("/competitions", competitionDto);
    }

    public ResponseEntity updateCompetition(Long competitionId,
                                            CompetitionDto competitionDto) {
        return patch("/competitions/" + competitionId, competitionDto);
    }

    public ResponseEntity deleteCompetition(Long competitionId) {
        return delete("/competitions/" + competitionId);
    }

    public ResponseEntity createUser(UserDto userDto) {
        return post("", userDto);
    }

    public ResponseEntity deleteUser(Long userId) {
        return delete("/" + userId);
    }
}
