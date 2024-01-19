package kz.ezdrav.eh.ehphysicalexaminationnew.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatusEnterpriseDto {
    private Boolean patients;
    private Boolean event;
    private Boolean consolidateReport;
}
