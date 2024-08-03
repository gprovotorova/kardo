package com.kardoaward.kardo.admin.service;

import com.kardoaward.kardo.competition.dto.CompetitionDto;
import com.kardoaward.kardo.event.dto.EventDto;
import com.kardoaward.kardo.partners.dto.PartnerDto;
import com.kardoaward.kardo.streams.dto.StreamDto;

public interface AdminService {

    EventDto createEvent(EventDto eventDto);

    void deleteEvent(Long eventId);

    EventDto updateEvent(Long eventId, EventDto eventDto);

    PartnerDto createPartner(PartnerDto partnerDto);

    void deletePartner(Long partnerId);

    PartnerDto updatePartner(Long partnerId, PartnerDto partnerDto);

    CompetitionDto createCompetition(CompetitionDto competitionDto);

    void deleteCompetition(Long competitionId);

    CompetitionDto updateCompetition(Long competitionId, CompetitionDto competitionDto);

    StreamDto createStream(StreamDto streamDto);

    void deleteStream(Long streamId);

    StreamDto updateStream(Long streamId, StreamDto streamDto);
}
