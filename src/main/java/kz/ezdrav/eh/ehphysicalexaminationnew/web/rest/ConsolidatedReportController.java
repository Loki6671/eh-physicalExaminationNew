package kz.ezdrav.eh.ehphysicalexaminationnew.web.rest;

import kz.ezdrav.eh.ehphysicalexaminationnew.dto.ConsolidatedReportDto;
import kz.ezdrav.eh.ehphysicalexaminationnew.dto.ConsolidatedReportSaveDto;
import kz.ezdrav.eh.ehphysicalexaminationnew.service.ConsolidatedReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pe")
@RequiredArgsConstructor
public class ConsolidatedReportController {
    private final ConsolidatedReportService consolidatedReportService;

    @GetMapping("/v1/{id}/cr")
    public ResponseEntity<List<ConsolidatedReportDto>> getConsolidatedReports(@PathVariable Long id){
        return consolidatedReportService.getConsolidatedReports(id);
    }

    @PostMapping("/v1/cr")
    public ResponseEntity<String> save(@RequestBody ConsolidatedReportSaveDto consolidatedReportDto){
        return consolidatedReportService.save(consolidatedReportDto);
    }
    @PutMapping("/v1/cr/{id}")
    public ResponseEntity<String> edit(@RequestBody ConsolidatedReportSaveDto consolidatedReportDto, @PathVariable Long id){
        return consolidatedReportService.edit(id,consolidatedReportDto);
    }



}
