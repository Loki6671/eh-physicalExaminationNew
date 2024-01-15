package kz.ezdrav.eh.ehphysicalexaminationnew.web.rest;

import kz.ezdrav.eh.ehphysicalexaminationnew.dto.EnterpriseDto;
import kz.ezdrav.eh.ehphysicalexaminationnew.dto.EnterpriseSaveDto;
import kz.ezdrav.eh.ehphysicalexaminationnew.repository.EnterpriseRepository;
import kz.ezdrav.eh.ehphysicalexaminationnew.service.EnterpriseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enterprise")
@RequiredArgsConstructor
public class EnterpriseController {

    private final EnterpriseService enterpriseService;


    @GetMapping({"/v1/{id}", "/internal/v1/{id}"})
    public ResponseEntity<List<EnterpriseDto>> getEnterprises(@PathVariable Long id){
        return enterpriseService.getEnterprises(id);
    }

    @PostMapping({"/v1", "/internal/v1"})
    public ResponseEntity<String> saveEnterprise( @RequestBody EnterpriseSaveDto saveDto){
        return enterpriseService.save(saveDto);
    }






}
