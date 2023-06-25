package org.example.Controllers;

import org.example.Models.Event;
import org.example.Models.Org;
import org.example.Repository.ContractRequestRepository;
import org.example.Repository.EventRepository;
import org.example.Repository.OrgRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/events")
public class AdminEventController {

    private final EventRepository eventRepository;
    private final ContractRequestRepository requestRepository;
    private final OrgRepository orgRepository;


    public AdminEventController(EventRepository eventRepository, ContractRequestRepository requestRepository, OrgRepository orgRepository) {
        this.eventRepository = eventRepository;
        this.requestRepository = requestRepository;
        this.orgRepository = orgRepository;
    }
    @GetMapping
    public List<Event> findAllEvents() {
        return eventRepository.findAll();
    }
    @GetMapping("/{id}")
    public Event findEventById(@PathVariable Long id) {
        Optional<Event> event = eventRepository.findById(id);
        return event.orElse(null);
    }

    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        Event event1 = eventRepository.save(event);
        String temp = requestRepository.getById(event1.getContractId()).getStatus();
        if (!temp.equals("ok")) {
            deleteEvent(event1.getId());
            event1 = null;
        }
        return event1;
    }

    @PostMapping("/addOrgInfo")
    public String addOrgInfo(@RequestBody Org org) {
        orgRepository.save(org);
        return "Вы успешно зарегистрировали данные об организации";
    }

    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable Long id, @RequestBody Event eventDetails) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isPresent()) {
            Event updatedEvent = event.get();
            updatedEvent.setTitle(eventDetails.getTitle());
            updatedEvent.setContractId(eventDetails.getContractId());
            return eventRepository.save(updatedEvent);
        } else {
            return null;
        }
    }
    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventRepository.deleteById(id);
    }
}
