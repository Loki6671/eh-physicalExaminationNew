package kz.ezdrav.eh.ehphysicalexaminationnew.repository;

import kz.ezdrav.eh.ehphysicalexaminationnew.model.ConsolidatedReport;
import kz.ezdrav.eh.ehphysicalexaminationnew.model.Contraindication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsolidatedReportRepository extends JpaRepository<ConsolidatedReport, Long> {
}
