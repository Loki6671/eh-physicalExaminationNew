package kz.ezdrav.eh.ehphysicalexaminationnew.repository;

import kz.ezdrav.eh.ehphysicalexaminationnew.model.Enterprise;
import kz.ezdrav.eh.ehphysicalexaminationnew.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient,Long > , JpaSpecificationExecutor<Patient> {
    List<Patient> findByEnterprise(Enterprise enterprise);
    int countByEnterprise(Enterprise enterprise);
    int countByEnterpriseAndGender(Enterprise enterprise, Boolean gender);
    @Query("SELECT COUNT(p) FROM Patient p WHERE size(p.dangers) > 0")
    int countPatientsWithMultipleDangers();
    @Query("SELECT COUNT(p) FROM Patient p WHERE size(p.dangers) > 0 AND p.gender = false")
    int countFemalePatientsWithMultipleDangers();
}
