package kz.ezdrav.eh.ehphysicalexaminationnew.feign;

import kz.ezdrav.eh.ehphysicalexaminationnew.enums.UrzDictionaryType;
import kz.ezdrav.eh.urz.common.model.domain.dictionary.Dictionary;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "eh-urz", path = "/api/urz", url = "${feign.url.eh-urz}")
public interface UrzClient {
    @GetMapping("/internal/v1/dictionary/{type}")
    List<Dictionary> getDictionary(@PathVariable("type") UrzDictionaryType type);
}
