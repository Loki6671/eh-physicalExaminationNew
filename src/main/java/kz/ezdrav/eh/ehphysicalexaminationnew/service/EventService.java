package kz.ezdrav.eh.ehphysicalexaminationnew.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.ezdrav.eh.ehphysicalexaminationnew.dto.*;
import kz.ezdrav.eh.ehphysicalexaminationnew.mapper.EventMapper;
import kz.ezdrav.eh.ehphysicalexaminationnew.model.Danger;
import kz.ezdrav.eh.ehphysicalexaminationnew.model.Enterprise;
import kz.ezdrav.eh.ehphysicalexaminationnew.model.Event;
import kz.ezdrav.eh.ehphysicalexaminationnew.repository.DangerRepository;
import kz.ezdrav.eh.ehphysicalexaminationnew.repository.EnterpriseRepository;
import kz.ezdrav.eh.ehphysicalexaminationnew.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.cms.SignerInformationStore;
import org.bouncycastle.util.Store;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final EnterpriseRepository enterpriseRepository;
    private final EventMapper eventMapper;
    private final DangerRepository dangerRepository;


    public ResponseEntity<List<EventDto>> getEvent( Long id){
        Optional<Enterprise> byId = enterpriseRepository.findById(id);
        if(byId.isPresent()){
            Enterprise enterprise = byId.get();
            List<Event> byEnterprise = eventRepository.findByEnterprise(enterprise);
            List<EventDto> collect = byEnterprise.stream().map(eventMapper::toDto).collect(Collectors.toList());
            return ResponseEntity.ok(collect);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<List<EventDto>> getEventFilter(EventFilterDto eventFilterDto) {
        List<Event> all = eventRepository.findAll(toSpecification(eventFilterDto));
        List<EventDto> collect = all.stream().map(eventMapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(collect);
    }
    private Specification<Event> toSpecification(EventFilterDto filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getName() != null) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + filter.getName() + "%"));
            }
            if (filter.getEnterprise() != null) {
                predicates.add(criteriaBuilder.equal(root.get("enterprise").get("name"), filter.getEnterprise()));
            }
            if (filter.getFrom() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("startDate"), filter.getFrom()));
            }
            if (filter.getTo() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("endDate"), filter.getTo()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public ResponseEntity<String> save(EventSaveDto saveDto) {
        try {
            Event event = eventMapper.toEntity(saveDto, enterpriseRepository,dangerRepository);
            eventRepository.save(event);
            return ResponseEntity.ok("Success!");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
//TODO signValidate
//    public ResponseEntity<List<String>> extractSignerInfo(String base64Cms) {
//        try {
//            // Декодирование CMS из Base64
//            byte[] cmsData = Base64.decode(base64Cms);
//
//            // Создание объекта CMSSignedData
//            CMSSignedData signedData = new CMSSignedData(cmsData);
//
//            // Получение информации о подписантах
//            SignerInformationStore signers = signedData.getSignerInfos();
//
//            byte[] signedContent = (byte[]) signedData.getSignedContent().getContent();
//            // Преобразование в строку и десериализация JSON
//            String signedContentString = new String(signedContent);
//            ObjectMapper objectMapper = new ObjectMapper();
//            EventDataSignDto eventData = objectMapper.readValue(signedContentString, EventDataSignDto.class);
//
//
//            // Получение сертификатов
//            Store certs = signedData.getCertificates();
//
//            List<String> result= new ArrayList<>();
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//            Collection<SignerInformation> signer = signers.getSigners().;
//            Collection<X509CertificateHolder> certCollection = certs.getMatches(signer.getSID());
//                X509CertificateHolder certificateHolder = certCollection.iterator().next();
//
//                // Получение срока действия сертификата
//                Date notBefore = certificateHolder.getNotBefore();
//                Date notAfter = certificateHolder.getNotAfter();
//                String validityPeriod = "Срок действия: с " + sdf.format(notBefore) + " по " + sdf.format(notAfter);
//                X500Name x500name = new X500Name(certificateHolder.getSubject().toString());
//
//                String bin = x500name.getRDNs(BCStyle.CN)[0].getFirst().getValue().toString();
//
//                // Добавление информации о подписанте и сроке действия сертификата
//                result.add(certificateHolder.getSubject().toString() + "; " + validityPeriod);
//
//
//            return ResponseEntity.ok(result);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.internalServerError().body(null);
//        }
//    }

}
