package kz.ezdrav.eh.ehphysicalexaminationnew.dto;

import kz.ezdrav.eh.ehphysicalexaminationnew.model.Event;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsolidatedReportDto {
    private Long id;
    private String superVisorMed;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime dateSubmit;
    private Boolean confirmed;
    private String event;
}
