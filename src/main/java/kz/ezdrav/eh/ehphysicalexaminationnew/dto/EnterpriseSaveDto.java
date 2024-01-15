package kz.ezdrav.eh.ehphysicalexaminationnew.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnterpriseSaveDto {
    private String name;
    private Long region;
    private String address;
}
