package kz.ezdrav.eh.ehphysicalexaminationnew.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "contraindication") // Указываем конкретное имя таблицы
public class Contraindication {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator",  allocationSize = 1)
    private Long id;
    private String name;
    @ManyToMany
    private Set<MedicalProcedure> medicalProcedures;

}