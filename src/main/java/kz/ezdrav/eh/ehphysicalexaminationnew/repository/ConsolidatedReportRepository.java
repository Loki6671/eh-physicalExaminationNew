package kz.ezdrav.eh.ehphysicalexaminationnew.repository;

import kz.ezdrav.eh.ehphysicalexaminationnew.model.ConsolidatedReport;
import kz.ezdrav.eh.ehphysicalexaminationnew.model.Contraindication;
import kz.ezdrav.eh.ehphysicalexaminationnew.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ConsolidatedReportRepository extends JpaRepository<ConsolidatedReport, Long> {
    @Query("SELECT cr FROM ConsolidatedReport cr JOIN cr.event e WHERE e.enterprise.id = :enterpriseId")
    List<ConsolidatedReport> findByEnterpriseId(@Param("enterpriseId") Long enterpriseId);
    Optional<Event> findByEvent(Event event);
}
