package kz.ezdrav.eh.ehphysicalexaminationnew.repository;

import kz.ezdrav.eh.ehphysicalexaminationnew.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Long > {
}
