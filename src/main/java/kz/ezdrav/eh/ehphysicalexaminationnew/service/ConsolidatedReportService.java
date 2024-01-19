package kz.ezdrav.eh.ehphysicalexaminationnew.service;

import kz.ezdrav.eh.ehphysicalexaminationnew.dto.ConsolidatedReportDto;
import kz.ezdrav.eh.ehphysicalexaminationnew.dto.ConsolidatedReportSaveDto;
import kz.ezdrav.eh.ehphysicalexaminationnew.mapper.ConsolidatedReportMapper;
import kz.ezdrav.eh.ehphysicalexaminationnew.model.ConsolidatedReport;
import kz.ezdrav.eh.ehphysicalexaminationnew.model.Enterprise;
import kz.ezdrav.eh.ehphysicalexaminationnew.model.Event;
import kz.ezdrav.eh.ehphysicalexaminationnew.repository.ConsolidatedReportRepository;
import kz.ezdrav.eh.ehphysicalexaminationnew.repository.EnterpriseRepository;
import kz.ezdrav.eh.ehphysicalexaminationnew.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsolidatedReportService {

    private  final ConsolidatedReportRepository consolidatedReportRepository;
    private final EventService eventService;
    private final ConsolidatedReportMapper consolidatedReportMapper;
    private final EventRepository eventRepository;
    private final EnterpriseRepository enterpriseRepository;


    public List<ConsolidatedReport> getCRbyEnterprise(Enterprise enterprise){
        return consolidatedReportRepository.findByEnterpriseId(enterprise.getId());
    }

    public ResponseEntity<List<ConsolidatedReportDto>> getConsolidatedReports(Long id) {
        try {
            List<ConsolidatedReport> cRbyEnterprise = getCRbyEnterprise(enterpriseRepository.findById(id).get());
            List<ConsolidatedReportDto> collect = cRbyEnterprise.stream().map(consolidatedReportMapper::toDto).collect(Collectors.toList());
            return ResponseEntity.ok( collect);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<String> save(ConsolidatedReportSaveDto consolidatedReportDto) {
        ConsolidatedReport entity = consolidatedReportMapper.toEntity(consolidatedReportDto, eventRepository);
        consolidatedReportRepository.save(entity);
        return ResponseEntity.ok("Success!");
    }

    public ResponseEntity<String> edit(Long id, ConsolidatedReportSaveDto consolidatedReportDto) {
        try {
            ConsolidatedReport existingReport = consolidatedReportRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("ConsolidatedReport not found with id " + id));

            ConsolidatedReport updatedReport = consolidatedReportMapper.toEntity(consolidatedReportDto,eventRepository );
            updatedReport.setId(id);
            consolidatedReportRepository.save(updatedReport);

            return ResponseEntity.ok("Success!");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
