package kz.ezdrav.eh.ehphysicalexaminationnew.repository;

import kz.ezdrav.eh.ehphysicalexaminationnew.model.Enterprise;
import kz.ezdrav.eh.ehphysicalexaminationnew.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long>, JpaSpecificationExecutor<Event> {
    List<Event> findByEnterprise(Enterprise enterprise);

}
