package kz.ezdrav.eh.ehphysicalexaminationnew.mapper;

import kz.ezdrav.eh.ehphysicalexaminationnew.dto.PatientDto;
import kz.ezdrav.eh.ehphysicalexaminationnew.dto.PatientSaveDto;
import kz.ezdrav.eh.ehphysicalexaminationnew.model.Danger;
import kz.ezdrav.eh.ehphysicalexaminationnew.model.Enterprise;
import kz.ezdrav.eh.ehphysicalexaminationnew.model.Patient;
import kz.ezdrav.eh.ehphysicalexaminationnew.repository.DangerRepository;
import kz.ezdrav.eh.ehphysicalexaminationnew.repository.EnterpriseRepository;
import org.mapstruct.*;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PatientMapper {

    @Mapping(source = "dangers", target = "dangers", qualifiedByName = "dangersToStringList")
    PatientDto toDto(Patient patient);
    @Mappings({
            @Mapping(source = "enterprise", target = "enterprise", qualifiedByName = "mapEnterprise"),
            @Mapping(source = "dangers", target = "dangers", qualifiedByName = "mapDangers")
    })
    Patient toEntity(PatientSaveDto dto, @Context EnterpriseRepository enterpriseRepository, @Context DangerRepository dangerRepository);


    @Named("mapEnterprise")
    default Enterprise mapEnterprise(Long id, @Context EnterpriseRepository enterpriseRepository) {
        if (id == null) {
            return null;
        }
        return enterpriseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Enterprise not found"));
    }

    @Named("mapDangers")
    default Set<Danger> mapDangers(Set<Long> ids, @Context DangerRepository dangerRepository) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptySet();
        }
        return ids.stream()
                .map(id -> dangerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Danger not found")))

                .collect(Collectors.toSet());
    }

    @Named("dangersToStringList")
    default List<String> mapDangersToStringList(Set<Danger> dangers) {
        if (dangers == null) {
            return null;
        }
        return dangers.stream()
                .map(Danger::getName) // Предполагается, что в классе Danger есть метод getName()
                .collect(Collectors.toList());
    }

}
