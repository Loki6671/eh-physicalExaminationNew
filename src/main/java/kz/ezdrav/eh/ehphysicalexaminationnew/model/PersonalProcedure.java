package kz.ezdrav.eh.ehphysicalexaminationnew.model;

import lombok.*;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
public class PersonalProcedure {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator",  allocationSize = 1)
    private Long id;
    @ManyToOne
    private MedicalProcedure medicalProcedure;
    @ManyToOne
    private Patient patient;
    private String description;
    @ManyToOne
    private ResultProcedure result;
    private LocalDateTime date;
}
