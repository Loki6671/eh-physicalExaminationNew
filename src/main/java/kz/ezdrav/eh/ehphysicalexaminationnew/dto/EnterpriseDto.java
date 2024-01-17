package kz.ezdrav.eh.ehphysicalexaminationnew.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnterpriseDto {
    private String id;
    private String name;
    private String address;
}
