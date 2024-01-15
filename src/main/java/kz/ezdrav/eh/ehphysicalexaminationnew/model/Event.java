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
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator",  allocationSize = 1)
    private Long id;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String chairmanCommission;
    private String superVisorMed;
    private String owner;
    private String epid;
    private String committee;
    private Boolean confirmed;
    @ManyToMany
    private Set<Danger> dangers;
    @ManyToOne
    private Enterprise enterprise;
}
