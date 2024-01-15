package kz.ezdrav.eh.ehphysicalexaminationnew.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventSaveDto {
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long enterprise;
    private List<Long> dangers;
}
