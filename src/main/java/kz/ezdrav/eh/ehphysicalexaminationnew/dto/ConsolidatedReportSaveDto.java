package kz.ezdrav.eh.ehphysicalexaminationnew.dto;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsolidatedReportSaveDto {
    private String superVisorMed;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime dateSubmit;
    private Boolean confirmed;
    private Long event;
}
