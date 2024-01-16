package kz.ezdrav.eh.ehphysicalexaminationnew.web.rest;

import kz.ezdrav.eh.ehphysicalexaminationnew.dto.*;
import kz.ezdrav.eh.ehphysicalexaminationnew.model.Patient;
import kz.ezdrav.eh.ehphysicalexaminationnew.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/patient")
public class PatientController {

    private final PatientService patientService;



    @GetMapping({"/v1/{id}", "/internal/v1/{id}"})
    public ResponseEntity<List<PatientDto>> getEvents(@PathVariable Long id){
        return patientService.getPatient(id);
    }

    @GetMapping({"/v1", "/internal/v1"})
    public ResponseEntity<List<PatientDto>> getEventsByFilter(PatientFilterDto patientFilterDto){
        return patientService.getPatientFilter(patientFilterDto);
    }

    @PostMapping({"/v1", "/internal/v1"})
    public ResponseEntity<String> saveEvent(@RequestBody PatientSaveDto saveDto){
        return patientService.save(saveDto);
    }
}
