package kz.ezdrav.eh.ehphysicalexaminationnew.service;

import kz.ezdrav.eh.ehphysicalexaminationnew.dto.EnterpriseDto;
import kz.ezdrav.eh.ehphysicalexaminationnew.dto.EnterpriseSaveDto;
import kz.ezdrav.eh.ehphysicalexaminationnew.dto.StatusEnterpriseDto;
import kz.ezdrav.eh.ehphysicalexaminationnew.enums.UrzDictionaryType;
import kz.ezdrav.eh.ehphysicalexaminationnew.feign.UrzClient;
import kz.ezdrav.eh.ehphysicalexaminationnew.mapper.EnterpriseMapper;
import kz.ezdrav.eh.ehphysicalexaminationnew.model.*;
import kz.ezdrav.eh.ehphysicalexaminationnew.repository.EnterpriseRepository;
import kz.ezdrav.eh.ehphysicalexaminationnew.repository.RegionRepository;
import kz.ezdrav.eh.security.common.token.EhUserPrincipal;
import kz.ezdrav.eh.urz.common.model.domain.dictionary.Dictionary;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EnterpriseService {
    private final EnterpriseRepository enterpriseRepository;
    private final RegionRepository regionRepository;
    private final EnterpriseMapper mapper;
    private final PatientService patientService;
    private final ConsolidatedReportService consolidatedReportService;
    private final EventService eventService;
    private final UrzClient urzClient;

    public ResponseEntity<List<EnterpriseDto>> getEnterprises(Long region) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof EhUserPrincipal ) {
//            String regionId = ((EhUserPrincipal) principal).getRegionId();
//            List<Dictionary> dictionary = urzClient.getDictionary(UrzDictionaryType.D_REGION);
            Region region1 = regionRepository.findById(20L).get();
            List<Enterprise> byRegion = enterpriseRepository.findByRegion(region1);
            List<EnterpriseDto> collect = byRegion.stream().map(mapper::toDto).collect(Collectors.toList());

            return ResponseEntity.ok( collect);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<String> save(EnterpriseSaveDto saveDto) {
       try {
            Enterprise entity = mapper.toEntity(saveDto, regionRepository);
            enterpriseRepository.save(entity);
            return ResponseEntity.ok("Success!");
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    public Enterprise findById(Long id) throws Exception {
        Optional<Enterprise> byId = enterpriseRepository.findById(id);
        if(byId.isPresent()){
            return byId.get();
        }else{
            throw new Exception("The enterprise by id not found");
        }
    }

    public ResponseEntity<StatusEnterpriseDto> getStatusEnterprise(Long id) {
        Optional<Enterprise> byId = enterpriseRepository.findById(id);
        if(byId.isPresent()){
            Enterprise enterprise = byId.get();
            List<Patient> patients = patientService.getPatientByEnterprise(enterprise);
            List<ConsolidatedReport> consolidatedReports = consolidatedReportService.getCRbyEnterprise(enterprise);
            List<Event> events = eventService.findByEnterprise(enterprise);
            return ResponseEntity.ok(StatusEnterpriseDto.builder()
                    .event(!events.isEmpty())
                    .patients(!patients.isEmpty())
                    .consolidateReport(!consolidatedReports.isEmpty())
                    .build());

        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
