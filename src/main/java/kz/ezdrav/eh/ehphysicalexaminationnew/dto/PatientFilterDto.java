package kz.ezdrav.eh.ehphysicalexaminationnew.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientFilterDto {
    private String fio;
    private String iin;
}
