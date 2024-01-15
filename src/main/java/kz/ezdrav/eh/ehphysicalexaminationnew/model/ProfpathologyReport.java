package kz.ezdrav.eh.ehphysicalexaminationnew.model;

import lombok.*;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
public class ProfpathologyReport {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator",  allocationSize = 1)
    private Long id;
    private String serialNumber;
    private String medicalHistory;
    private String primaryDiagnosis;

    private String comorbidDiagnosis;

    private String employmentRecommendations;

    private String generalRecommendations;
    private String chairmanCommission;
    @ManyToMany
    private List<СommissionMember> commissionMembers;
    private String epicConclusion;
    private LocalDateTime reexaminationPeriod;
    private LocalDateTime confirmedDate;
}
