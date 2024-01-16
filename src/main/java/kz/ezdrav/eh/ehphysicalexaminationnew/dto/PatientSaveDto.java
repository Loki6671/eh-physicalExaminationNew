package kz.ezdrav.eh.ehphysicalexaminationnew.dto;

import kz.ezdrav.eh.ehphysicalexaminationnew.model.Danger;
import kz.ezdrav.eh.ehphysicalexaminationnew.model.Enterprise;
import lombok.*;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Set;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientSaveDto {
    private Long id;
    private String iin;
    private String job;
    private Integer experienceByJob;
    private Integer fullExperience;
    private LocalDateTime lastPhys;
    private Boolean dangerStatus;
    private Boolean sickList;
    private Boolean trip;
    private Boolean vacation;
    private Boolean dismissal;
    private Boolean reject;
    private Long enterprise;
    private Set<Long> dangers;
    private String medPlace;
}
