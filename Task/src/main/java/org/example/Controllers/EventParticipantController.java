package org.example.Controllers;

import org.example.Models.Event;
import org.example.Models.Participant;
import org.example.Repository.EventRepository;
import org.example.Repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasRole('USER')")
@RequestMapping("/participants")
public class EventParticipantController {

    private final EventRepository eventRepository;

    private final ParticipantRepository participantRepository;

    @Autowired
    public EventParticipantController(EventRepository eventRepository, ParticipantRepository participantRepository) {
        this.eventRepository = eventRepository;
        this.participantRepository = participantRepository;
    }

    @PostMapping
    public String addParticipant(@RequestBody Participant participant) {
        if (eventRepository.findById(participant.getEventId()).get() != null) {
            if (participant.getAge() >= 18 && participant.isPcrTestPassed()) {
                participant.setEventId(participant.getEventId());
                if (participant.getBalance() >= eventRepository.getById(participant.getEventId()).getCoast()) {
                    participant.setBalance(participant.getBalance() -
                    eventRepository.getById(participant.getEventId()).getCoast());
                }
                else return "Недостаточно средств на балансе";
                participantRepository.save(participant);
                return "Вы успешно записались на мероприятие";
            } else {
                return "Вы не зарегестрированы, проверьте заполненные поля";
            }
        } else {
            return "Вы не записались";
        }
    }

    @GetMapping("/actual_events")
    public List<Event> getAllEvents() {return eventRepository.findAll();}
}

