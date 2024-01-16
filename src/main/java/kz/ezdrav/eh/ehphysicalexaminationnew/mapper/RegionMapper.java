package kz.ezdrav.eh.ehphysicalexaminationnew.mapper;

import kz.ezdrav.eh.ehphysicalexaminationnew.dto.RegionDto;
import kz.ezdrav.eh.ehphysicalexaminationnew.model.Region;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface RegionMapper {

    RegionDto toDto(Region region);
}
