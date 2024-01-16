package kz.ezdrav.eh.ehphysicalexaminationnew.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {
    private Long id;
    private String fio;
    private Boolean gender;
    private String job;
    private Integer experienceByJob;
    private Integer fullExperience;
    private String medPlace;
    private LocalDateTime lastPE;
    private List<String> dangers;
}
