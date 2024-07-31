package com.kardoaward.kardo.participant.service;

import com.kardoaward.kardo.participant.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {

    private ParticipantRepository participantRepository;

}
