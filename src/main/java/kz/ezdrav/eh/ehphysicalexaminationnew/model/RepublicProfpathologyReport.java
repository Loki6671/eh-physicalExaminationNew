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
public class RepublicProfpathologyReport {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator",  allocationSize = 1)
    private Long id;
    private String serialNumber;
    private LocalDateTime date;
    private String employmentCharacteristics; // Экспертиза санитарно-гигиенических характеристик условий труда – для легкопереносимых болтных
    private String lifeAnamnesis; // Анамнез жизни
    private String diseaseAnamnesis; // Анамнез заболевания
    private String complaints; // Жалобы больного
    private String objectiveData; // Объективные данные
    private String researchResults; // Обследования
    private String consultations; // Консультации
    private String clinicalDiagnosis; // Социально-клиническое заключение. Основной диагноз
    private String accompanyingDiagnosis; // Сопутствующий диагноз
    private String employmentRecommendations; // Трудовые рекомендации
    private String generalRecommendations; // Рекомендации
    private String chairmanCommission;
    @ManyToMany
    private List<СommissionMember> commissionMembers;
    private String epicConclusion;

}
