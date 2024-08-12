package com.kardoaward.kardo.participant.mapper;

import com.kardoaward.kardo.competition.model.Competition;
import com.kardoaward.kardo.direction.model.Direction;
import com.kardoaward.kardo.event.model.Event;
import com.kardoaward.kardo.participant.model.CompetitionParticipant;
import com.kardoaward.kardo.participant.model.EvaluatingParticipant;
import com.kardoaward.kardo.participant.model.EventParticipant;
import com.kardoaward.kardo.user.model.User;

import java.util.OptionalDouble;
import java.util.Set;

public class ParticipantMapper {

    public static CompetitionParticipant toCompetitionParticipant(User user, Competition competition,
                                                                  Set<Direction> directions) {
        return new CompetitionParticipant(
                user,
                competition,
                directions
        );
    }

    public static EventParticipant toEventParticipant(User user, Event event) {
        return new EventParticipant(
                user,
                event
        );
    }

    public static EvaluatingParticipant toEvaluatingParticipant(Competition competition,
                                                                CompetitionParticipant participant,
                                                                User expert,
                                                                OptionalDouble points) {
        return new EvaluatingParticipant(
                competition,
                competition.getStage(),
                participant,
                expert,
                points.getAsDouble()
        );
    }
}
