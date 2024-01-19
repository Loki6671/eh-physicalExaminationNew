package kz.ezdrav.eh.ehphysicalexaminationnew.service;

import kz.ezdrav.eh.ehphysicalexaminationnew.dto.*;
import kz.ezdrav.eh.ehphysicalexaminationnew.feign.RpnClient;
import kz.ezdrav.eh.ehphysicalexaminationnew.mapper.PatientMapper;
import kz.ezdrav.eh.ehphysicalexaminationnew.model.Enterprise;
import kz.ezdrav.eh.ehphysicalexaminationnew.model.Event;
import kz.ezdrav.eh.ehphysicalexaminationnew.model.Patient;
import kz.ezdrav.eh.ehphysicalexaminationnew.repository.DangerRepository;
import kz.ezdrav.eh.ehphysicalexaminationnew.repository.EnterpriseRepository;
import kz.ezdrav.eh.ehphysicalexaminationnew.repository.EventRepository;
import kz.ezdrav.eh.ehphysicalexaminationnew.repository.PatientRepository;
import kz.ezdrav.eh.person.common.model.dto.Person;
import kz.ezdrav.eh.person.common.model.dto.RegistrationAddress;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final EnterpriseRepository enterpriseRepository;
    private final PatientMapper patientMapper;
    private final DangerRepository dangerRepository;
    private final RpnClient rpnClient;
    private final EventRepository eventRepository;


    public ResponseEntity<List<PatientDto>> getPatient(Long id) {
        Optional<Enterprise> byId = enterpriseRepository.findById(id);
        if(byId.isPresent()){
            Enterprise enterprise = byId.get();
            List<Patient> byEnterprise = patientRepository.findByEnterprise(enterprise);
            List<PatientDto> collect = byEnterprise.stream().map(patientMapper::toDto).collect(Collectors.toList());
            return ResponseEntity.ok(collect);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    public ResponseEntity<List<PatientDto>> getPatientFilter(PatientFilterDto patientFilterDto) {
        List<Patient> all = patientRepository.findAll(toSpecification(patientFilterDto));
        List<PatientDto> collect = all.stream().map(patientMapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(collect);
    }
    private Specification<Patient> toSpecification(PatientFilterDto filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getFio() != null) {
                predicates.add(criteriaBuilder.like(root.get("fio"), "%" + filter.getFio() + "%"));
            }
            if (filter.getIin() != null) {
                predicates.add(criteriaBuilder.equal(root.get("fio"), "%"+filter.getIin()+"%"));
            }


            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public ResponseEntity<String> save(PatientSaveDto saveDto) {
        String iin = saveDto.getIin();
        Optional<Person> personByIin = rpnClient.findPersonByIin(iin);
        if (personByIin.isPresent()) {

            Patient entity = patientMapper.toEntity(saveDto, enterpriseRepository, dangerRepository);
            Person person = personByIin.get();
            boolean gender=false;
            if(person.getGender().getCode().equals("300")){
                gender=true;
            }
            entity.setBirthDate( person.getBirthDate());
            entity.setGender(gender);
            RegistrationAddress registrationAddress = person.getRegistrationAddress();

            String address = Stream.of(registrationAddress.getCountry().getNameRu(), registrationAddress.getRegion().getNameRu(), registrationAddress.getCity(), registrationAddress.getStreet(), registrationAddress.getBuilding(), registrationAddress.getApartment()).filter((x) -> {
                return x != null && !x.isEmpty();
            }).collect(Collectors.joining(" "));
            entity.setAdress(address);
            patientRepository.save(entity);
            return ResponseEntity.ok("Success!");
        }else {
            return ResponseEntity.badRequest().body("Patient by inn not found");
        }
    }

    public List<Patient> getPatientByEnterprise(Enterprise enterprise){
        return patientRepository.findByEnterprise(enterprise);
    }

    public int getCountPatientsByEnterprise(Enterprise enterprise){
        return patientRepository.countByEnterprise(enterprise);
    }
    public int countByEnterpriseAndGender(Enterprise enterprise){
        return patientRepository.countByEnterpriseAndGender(enterprise,false);
    }
    public int countPatientsWithMultipleDangers(){
        int i = patientRepository.countPatientsWithMultipleDangers();
        return i;
    }
    public int countFemalePatientsWithMultipleDangers(){
        int i = patientRepository.countFemalePatientsWithMultipleDangers();
        return i;
    }
}
