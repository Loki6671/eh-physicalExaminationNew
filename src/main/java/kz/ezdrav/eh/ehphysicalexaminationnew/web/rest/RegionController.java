package kz.ezdrav.eh.ehphysicalexaminationnew.web.rest;

import kz.ezdrav.eh.ehphysicalexaminationnew.dto.RegionDto;
import kz.ezdrav.eh.ehphysicalexaminationnew.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/region")
@RequiredArgsConstructor
public class RegionController {

    private final RegionService regionService;

    @GetMapping("/v1")
    public ResponseEntity<List<RegionDto>> getRegions(){
        return regionService.getRegions();
    }
}
