package kz.ezdrav.eh.ehphysicalexaminationnew.service;

import kz.ezdrav.eh.ehphysicalexaminationnew.dto.EnterpriseDto;
import kz.ezdrav.eh.ehphysicalexaminationnew.dto.EnterpriseSaveDto;
import kz.ezdrav.eh.ehphysicalexaminationnew.mapper.EnterpriseMapper;
import kz.ezdrav.eh.ehphysicalexaminationnew.model.Enterprise;
import kz.ezdrav.eh.ehphysicalexaminationnew.model.Region;
import kz.ezdrav.eh.ehphysicalexaminationnew.repository.EnterpriseRepository;
import kz.ezdrav.eh.ehphysicalexaminationnew.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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


    public ResponseEntity<List<EnterpriseDto>> getEnterprises(Long region) {
        Optional<Region> byId = regionRepository.findById(region);
        if(byId.isPresent()) {
            List<Enterprise> byRegion = enterpriseRepository.findByRegion(byId.get());
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
}
