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
public class EpidemiologyStats{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator",  allocationSize = 1)
    private Long id;
    private String sang;
    private String sangPath;
    private String hb;
    private String hbPath;
    private String radiation;
    private String radiationPath;
    private String noise;
    private String noisePath;
    private String temperature;
    private String temperaturePath;
    private String workPlace;
    private String workPlacePath;
    private String vision;
    private String workAndRest;
    private Boolean workPosition;
    private String workPositionDescription;
    private Boolean corner;
    private String cornerDescription;
    private Boolean phys;
    private String physDescription;
    private String siz;
    private String sizPath;
    private String profDisease;
    private LocalDateTime date;
}
