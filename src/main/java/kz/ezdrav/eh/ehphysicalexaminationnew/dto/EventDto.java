package kz.ezdrav.eh.ehphysicalexaminationnew.dto;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {
    private Long id;
    private String name;
    private String enterprise;
    private LocalDateTime date;
}
