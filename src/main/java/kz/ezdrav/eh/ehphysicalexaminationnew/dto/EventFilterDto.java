package kz.ezdrav.eh.ehphysicalexaminationnew.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventFilterDto {
    private String name;
    private Long enterprise;
    private LocalDateTime from;
    private LocalDateTime to;
}
