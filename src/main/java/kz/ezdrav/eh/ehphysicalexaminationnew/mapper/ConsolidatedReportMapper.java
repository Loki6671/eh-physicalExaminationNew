package kz.ezdrav.eh.ehphysicalexaminationnew.mapper;

import kz.ezdrav.eh.ehphysicalexaminationnew.dto.ConsolidatedReportDto;
import kz.ezdrav.eh.ehphysicalexaminationnew.dto.ConsolidatedReportSaveDto;
import kz.ezdrav.eh.ehphysicalexaminationnew.model.ConsolidatedReport;
import kz.ezdrav.eh.ehphysicalexaminationnew.model.Event;
import kz.ezdrav.eh.ehphysicalexaminationnew.repository.EventRepository;
import org.mapstruct.*;

import javax.persistence.EntityNotFoundException;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ConsolidatedReportMapper {


    @Mapping(source = "event.name", target = "event")
    ConsolidatedReportDto toDto(ConsolidatedReport consolidatedReport);

    @Mappings({
            @Mapping(source = "event", target = "event", qualifiedByName = "mapEvent")
            // Другие поля, которые нужно маппить, можно опустить, так как их имена совпадают
    })
    ConsolidatedReport toEntity(ConsolidatedReportSaveDto dto, @Context EventRepository eventRepository);

    @Named("mapEvent")
    default Event mapEvent(Long id, @Context EventRepository eventRepository) {
        if (id == null) {
            return null;
        }
        return eventRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Event not found"));
    }
}
