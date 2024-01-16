package kz.ezdrav.eh.ehphysicalexaminationnew.repository;

import kz.ezdrav.eh.ehphysicalexaminationnew.model.Enterprise;
import kz.ezdrav.eh.ehphysicalexaminationnew.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient,Long > , JpaSpecificationExecutor<Patient> {
    List<Patient> findByEnterprise(Enterprise enterprise);
}
