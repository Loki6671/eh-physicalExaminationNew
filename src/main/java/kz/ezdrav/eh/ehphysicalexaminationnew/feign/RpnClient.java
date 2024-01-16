package kz.ezdrav.eh.ehphysicalexaminationnew.feign;

import feign.FeignException;
import kz.ezdrav.eh.person.common.model.dto.Person;
import kz.ezdrav.eh.person.common.service.RpnInterface;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.Optional;

@FeignClient(name = "eh-rpn", path = "/api/rpn", url = "${feign.url.eh-rpn}")
public interface RpnClient extends RpnInterface {

    default Optional<Person> findPersonByIin(String iin) {
        try {
            return Optional.of(getPersonInfoByIin(iin));
        } catch (FeignException.NotFound en) {
            en.printStackTrace();
            return Optional.empty();
        }
    }
}