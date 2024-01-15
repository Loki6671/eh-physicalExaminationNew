package kz.ezdrav.eh.ehphysicalexaminationnew.mapper;

import kz.ezdrav.eh.ehphysicalexaminationnew.dto.EventDto;
import kz.ezdrav.eh.ehphysicalexaminationnew.dto.EventSaveDto;
import kz.ezdrav.eh.ehphysicalexaminationnew.model.Danger;
import kz.ezdrav.eh.ehphysicalexaminationnew.model.Enterprise;
import kz.ezdrav.eh.ehphysicalexaminationnew.model.Event;
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
public interface EventMapper {
    @Mapping(source = "startDate", target = "date")
    @Mapping(source = "enterprise.name", target = "enterprise")
    EventDto toDto(Event event);

    @Mapping(target = "enterprise", source = "enterprise", qualifiedByName = "mapEnterprise")
    @Mapping(target = "dangers", source = "dangers", qualifiedByName = "mapDangers")
    Event toEntity(EventSaveDto dto, @Context EnterpriseRepository enterpriseRepository, @Context DangerRepository dangerRepository);

    @Named("mapEnterprise")
    default Enterprise mapEnterprise(Long id, @Context EnterpriseRepository enterpriseRepository) {
        if (id == null) {
            return null;
        }
        return enterpriseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Enterprise not found"));
    }

    @Named("mapDangers")
    default Set<Danger> mapDangers(List<Long> ids, @Context DangerRepository dangerRepository) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptySet();
        }
        return ids.stream()
                .map(id -> dangerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Danger not found")))
                .collect(Collectors.toSet());
    }


}
