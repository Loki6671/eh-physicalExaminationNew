package kz.ezdrav.eh.ehphysicalexaminationnew.mapper;

import kz.ezdrav.eh.ehphysicalexaminationnew.dto.EnterpriseDto;
import kz.ezdrav.eh.ehphysicalexaminationnew.dto.EnterpriseSaveDto;
import kz.ezdrav.eh.ehphysicalexaminationnew.model.Enterprise;
import kz.ezdrav.eh.ehphysicalexaminationnew.model.Region;
import kz.ezdrav.eh.ehphysicalexaminationnew.repository.RegionRepository;
import org.mapstruct.*;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface EnterpriseMapper {

    EnterpriseDto toDto(Enterprise enterprise);
    @Mapping(target = "region", source = "region")
    Enterprise toEntity(EnterpriseSaveDto dto, @Context RegionRepository regionRepository);

    default Region mapRegion(Long id, @Context RegionRepository regionRepository) {
        if (id == null) {
            return null;
        }
        // Здесь добавьте свой способ получения региона по ID, например:
        return regionRepository.findById(id).orElseThrow();
    }
}