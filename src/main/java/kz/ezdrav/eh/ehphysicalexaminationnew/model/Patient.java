package kz.ezdrav.eh.ehphysicalexaminationnew.model;

import lombok.*;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator",  allocationSize = 1)
    private Long id;
    private Long name;
    private String surname;
    private String patronymic;
    private String iin;
    private Boolean gender;
    private String job;
    private Integer experienceByJob;
    private Integer fullExperience;
    private LocalDateTime lastPhys;
    private Boolean dangerStatus;
    private Boolean sickList;
    private Boolean trip;
    private Boolean vacation;
    private Boolean dismissal;
    private Boolean reject;
    @ManyToOne
    private Enterprise enterprise;
}
