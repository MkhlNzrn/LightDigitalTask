import org.example.Controllers.AdminEventController;
import org.example.Controllers.AdminRequestController;
import org.example.Models.ContractRequest;
import org.example.Models.Event;
import org.example.Models.Org;
import org.example.Repository.ContractRequestRepository;
import org.example.Repository.EventRepository;
import org.example.Repository.OrgRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ControllerTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private ContractRequestRepository contractRequestRepository;

    @Mock
    private OrgRepository orgRepository;

    @InjectMocks
    private AdminEventController adminEventController;

    @InjectMocks
    private AdminRequestController adminRequestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Tests for AdminEventController

    @Test
    void testFindAllEvents() {
        List<Event> events = new ArrayList<>();
        events.add(new Event());
        events.add(new Event());

        when(eventRepository.findAll()).thenReturn(events);

        List<Event> result = adminEventController.findAllEvents();

        assertEquals(2, result.size());
    }

    @Test
    void testFindEventById() {
        Long eventId = 1L;
        Event event = new Event();
        event.setId(eventId);

        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));

        Event result = adminEventController.findEventById(eventId);

        assertEquals(eventId, result.getId());
    }

    @Test
    void testAddOrgInfo() {
        Org org = new Org();
        String expectedResponse = "Вы успешно зарегистрировали данные об организации";

        String result = adminEventController.addOrgInfo(org);

        assertEquals(expectedResponse, result);
        verify(orgRepository, times(1)).save(org);
    }

    @Test
    void testUpdateEvent() {
        Long eventId = 1L;
        Event existingEvent = new Event();
        existingEvent.setId(eventId);

        Event updatedEvent = new Event();
        updatedEvent.setId(eventId);
        updatedEvent.setTitle("Updated Title");
        updatedEvent.setContractId(2L);

        when(eventRepository.findById(eventId)).thenReturn(Optional.of(existingEvent));
        when(eventRepository.save(existingEvent)).thenReturn(updatedEvent);

        Event result = adminEventController.updateEvent(eventId, updatedEvent);

        assertEquals("Updated Title", result.getTitle());
        assertEquals(2L, result.getContractId());
    }

    @Test
    void testDeleteEvent() {
        Long eventId = 1L;

        adminEventController.deleteEvent(eventId);

        verify(eventRepository, times(1)).deleteById(eventId);
    }

    // Tests for AdminRequestController

    @Test
    void testFindAllContractRequests() {
        List<ContractRequest> contractRequests = new ArrayList<>();
        contractRequests.add(new ContractRequest());
        contractRequests.add(new ContractRequest());

        when(contractRequestRepository.findAll()).thenReturn(contractRequests);

        List<ContractRequest> result = adminRequestController.findAllContractRequests();

        assertEquals(2, result.size());
    }

    @Test
    void testFindContractRequestById() {
        Long requestId = 1L;
        ContractRequest contractRequest = new ContractRequest();
        contractRequest.setId(requestId);

        when(contractRequestRepository.findById(requestId)).thenReturn(Optional.of(contractRequest));

        ContractRequest result = adminRequestController.findContractRequestById(requestId);

        assertEquals(requestId, result.getId());
    }

    @Test
    void testCreateContractRequest() {
        ContractRequest contractRequest = new ContractRequest();
        contractRequest.setStatus("pending");

        String expectedResponse = "Заявка успешно подана";

        when(contractRequestRepository.save(contractRequest)).thenReturn(contractRequest);

        String result = adminRequestController.createContractRequest(contractRequest);

        assertEquals(expectedResponse, result);
    }

    @Test
    void testUpdateContractRequest() {
        Long requestId = 1L;
        ContractRequest existingContractRequest = new ContractRequest();
        existingContractRequest.setId(requestId);

        ContractRequest updatedContractRequest = new ContractRequest();
        updatedContractRequest.setId(requestId);
        updatedContractRequest.setRequesterName("John Doe");
        updatedContractRequest.setRequestDescription("Updated description");
        updatedContractRequest.setStatus("approved");

        when(contractRequestRepository.findById(requestId)).thenReturn(Optional.of(existingContractRequest));
        when(contractRequestRepository.save(existingContractRequest)).thenReturn(updatedContractRequest);

        ContractRequest result = adminRequestController.updateContractRequest(requestId, updatedContractRequest);

        assertEquals("John Doe", result.getRequesterName());
        assertEquals("Updated description", result.getRequestDescription());
        assertEquals("approved", result.getStatus());
    }
}
