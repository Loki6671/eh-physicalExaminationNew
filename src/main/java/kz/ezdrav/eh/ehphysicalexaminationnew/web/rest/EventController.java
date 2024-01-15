package kz.ezdrav.eh.ehphysicalexaminationnew.web.rest;

import kz.ezdrav.eh.ehphysicalexaminationnew.dto.*;
import kz.ezdrav.eh.ehphysicalexaminationnew.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pe")
public class EventController {
    private final EventService eventService;

    @GetMapping({"/v1/event/{id}", "/internal/v1/event/{id}"})
    public ResponseEntity<List<EventDto>> getEvents(@PathVariable Long id){
        return eventService.getEvent(id);
    }

    @GetMapping({"/v1/event", "/internal/v1/event"})
    public ResponseEntity<List<EventDto>> getEventsByFilter(EventFilterDto eventFilterDto){
        return eventService.getEventFilter(eventFilterDto);
    }

    @PostMapping({"/v1/event", "/internal/v1/event"})
    public ResponseEntity<String> saveEvent(@RequestBody EventSaveDto saveDto){
        return eventService.save(saveDto);
    }


}
