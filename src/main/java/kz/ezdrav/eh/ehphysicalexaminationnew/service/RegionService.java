package kz.ezdrav.eh.ehphysicalexaminationnew.service;

import kz.ezdrav.eh.ehphysicalexaminationnew.dto.RegionDto;
import kz.ezdrav.eh.ehphysicalexaminationnew.mapper.RegionMapper;
import kz.ezdrav.eh.ehphysicalexaminationnew.model.Region;
import kz.ezdrav.eh.ehphysicalexaminationnew.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class RegionService {

    private final RegionRepository repository;
    private final RegionMapper regionMapper;


    public ResponseEntity<List<RegionDto>> getRegions(){
        List<Region> all = repository.findAll();
        List<RegionDto> result = all.stream().map(regionMapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok( result);
    }

}
